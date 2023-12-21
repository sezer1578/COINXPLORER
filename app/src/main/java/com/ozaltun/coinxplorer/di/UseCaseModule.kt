package com.ozaltun.coinxplorer.di

import com.ozaltun.coinxplorer.domain.repository.CoinRepository
import com.ozaltun.coinxplorer.domain.usecase.GetCoinByIdUseCase
import com.ozaltun.coinxplorer.domain.usecase.GetCoinUseCase
import com.ozaltun.coinxplorer.domain.usecase.SearchCoinUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetCoinByIdUseCase(repository: CoinRepository): GetCoinByIdUseCase{
        return GetCoinByIdUseCase(repository = repository)
    }
    @Provides
    @Singleton
    fun provideGetCoinUseCase(repository: CoinRepository): GetCoinUseCase {
        return GetCoinUseCase(repository = repository)
    }
    @Provides
    @Singleton
    fun provideSearchCoinUseCase(repository: CoinRepository): SearchCoinUseCase {
        return SearchCoinUseCase(repository = repository)
    }
}