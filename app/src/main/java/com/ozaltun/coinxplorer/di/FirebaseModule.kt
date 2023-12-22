package com.ozaltun.coinxplorer.di

import com.google.firebase.auth.FirebaseAuth
import com.ozaltun.coinxplorer.data.repository.FireBaseRepositoryImpl
import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseServices(
        firebaseAuth: FirebaseAuth,
    ): FireBaseRepository = FireBaseRepositoryImpl(firebaseAuth)
}