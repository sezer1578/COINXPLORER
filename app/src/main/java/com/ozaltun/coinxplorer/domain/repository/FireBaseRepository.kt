package com.ozaltun.coinxplorer.domain.repository

import com.google.firebase.auth.AuthResult
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow

interface FireBaseRepository {
    fun signInWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>
    fun signUpWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>

    fun signOut()

    fun getFirebaseUserUid(): Flow<String>

    fun isCurrentUserExist(): Flow<Boolean>
}