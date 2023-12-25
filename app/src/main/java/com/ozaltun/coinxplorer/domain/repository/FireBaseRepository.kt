package com.ozaltun.coinxplorer.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow

interface FireBaseRepository {
    fun signInWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>
    fun signUpWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>

    fun signOut()

    fun getFirebaseUserUid(): Flow<String>

    fun isCurrentUserExist(): Flow<Boolean>

    fun addToFavourites(coin: CoinDetail): Flow<Resource<Task<Void>>>

    fun getFavourites(): Flow<Resource<List<CoinDetail>>>

    fun getCurrentUser(): Flow<Resource<FirebaseUser>>

    fun deleteFromFavourites(coin: CoinDetail): Flow<Resource<Task<Void>>>
}