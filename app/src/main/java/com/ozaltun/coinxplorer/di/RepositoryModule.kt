package com.ozaltun.coinxplorer.di

import com.ozaltun.coinxplorer.data.remote.CoinxplorerApi
import com.ozaltun.coinxplorer.data.repository.CoinRepositoryImpl
import com.ozaltun.coinxplorer.domain.repository.CoinRepository
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
}