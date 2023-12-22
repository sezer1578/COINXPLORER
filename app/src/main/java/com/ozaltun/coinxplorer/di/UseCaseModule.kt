package com.ozaltun.coinxplorer.di

import com.ozaltun.coinxplorer.domain.repository.CoinRepository
import com.ozaltun.coinxplorer.domain.repository.DataStoreManager
import com.ozaltun.coinxplorer.domain.repository.LocalCoinRepository
import com.ozaltun.coinxplorer.domain.usecase.GetCoinByIdUseCase
import com.ozaltun.coinxplorer.domain.usecase.GetCoinUseCase
import com.ozaltun.coinxplorer.domain.usecase.SearchCoinUseCase
import com.ozaltun.coinxplorer.domain.usecase.app_entry.AppEntryUseCases
import com.ozaltun.coinxplorer.domain.usecase.app_entry.ReadAppEntry
import com.ozaltun.coinxplorer.domain.usecase.app_entry.SaveAppEntry
import com.ozaltun.coinxplorer.domain.usecase.local_usecase.DeleteFavCoinUseCase
import com.ozaltun.coinxplorer.domain.usecase.local_usecase.GetFavCoinUseCase
import com.ozaltun.coinxplorer.domain.usecase.local_usecase.InsertFavCoinUseCase
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
    @Provides
    @Singleton
    fun provideInsertFavCoinUseCase(repository: LocalCoinRepository): InsertFavCoinUseCase {
        return InsertFavCoinUseCase(localCoinRepository = repository)
    }
    @Provides
    @Singleton
    fun provideDeleteFavCoinUseCase(repository: LocalCoinRepository): DeleteFavCoinUseCase {
        return DeleteFavCoinUseCase(localCoinRepository = repository)
    }
    @Provides
    @Singleton
    fun provideGetFavCoinUseCase(repository: LocalCoinRepository): GetFavCoinUseCase {
        return GetFavCoinUseCase(localCoinRepository = repository)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        dataStoreManager: DataStoreManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(dataStoreManager),
        saveAppEntry = SaveAppEntry(dataStoreManager)
    )
}