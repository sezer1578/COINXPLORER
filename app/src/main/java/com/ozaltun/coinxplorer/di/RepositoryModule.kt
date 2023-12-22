package com.ozaltun.coinxplorer.di

import android.app.Application
import com.ozaltun.coinxplorer.data.remote.CoinxplorerApi
import com.ozaltun.coinxplorer.data.repository.CoinRepositoryImpl
import com.ozaltun.coinxplorer.data.repository.DataStoreManagerImpl
import com.ozaltun.coinxplorer.domain.repository.CoinRepository
import com.ozaltun.coinxplorer.domain.repository.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinxplorerApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(
        application: Application
    ): DataStoreManager = DataStoreManagerImpl(context = application)
}