package com.ozaltun.coinxplorer.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import com.ozaltun.coinxplorer.util.extension.getErrorCode
import com.ozaltun.coinxplorer.util.extension.toAppException
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireBaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
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
}