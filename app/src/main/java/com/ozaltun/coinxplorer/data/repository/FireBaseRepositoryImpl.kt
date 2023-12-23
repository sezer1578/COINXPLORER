package com.ozaltun.coinxplorer.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ozaltun.coinxplorer.data.remote.dto.Description
import com.ozaltun.coinxplorer.data.remote.dto.Image
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.model.CoinDetailFav
import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import com.ozaltun.coinxplorer.util.extension.getErrorCode
import com.ozaltun.coinxplorer.util.extension.toAppException
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class FireBaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : FireBaseRepository {
    override fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            emit(Resource.Success(firebaseAuth.signInWithEmailAndPassword(email, password).await()))
            emit(Resource.Loading(isLoading = false))
        } catch (error: Throwable) {
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            emit(Resource.Error(exception = exception, errorCode = errorCode))
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            emit(
                Resource.Success(
                    data = firebaseAuth.createUserWithEmailAndPassword(
                        email,
                        password
                    ).await()
                )
            )
            emit(Resource.Loading(isLoading = false))
        } catch (error: Throwable) {
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            emit(Resource.Error(exception = exception, errorCode = errorCode))
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override fun getFirebaseUserUid(): Flow<String> = flow {
        firebaseAuth.currentUser?.uid?.let {
            emit(it)
        }
    }

    override fun isCurrentUserExist(): Flow<Boolean> = flow {
        emit(firebaseAuth.currentUser != null)
    }

    override fun addToFavourites(coin: CoinDetail): Flow<Resource<Task<Void>>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            getFirebaseUserUid().collect {
                val ref =
                    firebaseFirestore.collection("favorites").document(it).collection("coins")
                        .document(coin.name.orEmpty())
                        .set(coin)

                ref.await()
                emit(Resource.Success(data = ref))
                emit(Resource.Loading(isLoading = false))
            }
        } catch (error: Throwable) {
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            emit(Resource.Error(exception = exception, errorCode = errorCode))
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun getFavourites(): Flow<Resource<List<CoinDetail>>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            getFirebaseUserUid().collect { userId ->
                val snapshot =
                    firebaseFirestore.collection("favorites").document(userId)
                        .collection("coins")
                        .get().await()

                val data = snapshot.documents.mapNotNull { document ->
                    val coinId = document.id
                    val lastUpdated = document.getString("last_updated") ?: ""
                    val name = document.getString("name") ?: ""
                    val sentimentVotesDown =
                        document.getDouble("sentiment_votes_down_percentage") ?: 0.0
                    val sentimentVotesUp =
                        document.getDouble("sentiment_votes_up_percentage") ?: 0.0
                    val symbol = document.getString("symbol") ?: ""
                    val hashingAlgorithm = document.getString("hashing_algorithm") ?: ""
                    val currentPrice = document.getDouble("currentPrice") ?: 0.0
                    val priceChange24h = document.getDouble("price_change_24h") ?: 0.0

                    val description = document.get("description") as? Map<*, *>
                    val descriptionEN = (description?.get("en") as? String) ?: ""

                    val image = document.get("image") as? Map<*, *>
                    val largeImage = (image?.get("large") as? String) ?: ""
                    val smallImage = (image?.get("small") as? String) ?: ""
                    val thumbImage = (image?.get("thumb") as? String) ?: ""

                    CoinDetail(
                        coinId,
                        Description(descriptionEN),
                        Image(largeImage, smallImage, thumbImage),
                        lastUpdated,
                        name,
                        sentimentVotesDown,
                        sentimentVotesUp,
                        symbol,
                        hashingAlgorithm,
                        currentPrice,
                        priceChange24h
                    )
                }

                emit(Resource.Success(data = data))
                emit(Resource.Loading(isLoading = false))
            }
        } catch (error: Throwable) {
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            emit(Resource.Error(exception = exception, errorCode = errorCode))
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun deleteFromFavourites(coin: CoinDetail): Flow<Resource<Task<Void>>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            getFirebaseUserUid().collect {
                val ref = firebaseFirestore.collection("favorites").document(it)
                    .collection("coins").document(coin.name.orEmpty()).delete()
                ref.await()
                emit(Resource.Success(data = ref))
                emit(Resource.Loading(isLoading = false))
            }
        } catch (error: Throwable) {
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            emit(Resource.Error(exception = exception, errorCode = errorCode))
            emit(Resource.Loading(isLoading = false))
        }
    }
}