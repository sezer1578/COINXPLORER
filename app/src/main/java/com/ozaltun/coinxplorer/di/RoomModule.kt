package com.ozaltun.coinxplorer.di

import android.content.Context
import androidx.room.Room
import com.ozaltun.coinxplorer.data.local.CoinDB
import com.ozaltun.coinxplorer.data.local.CoinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideCoinDB(@ApplicationContext context: Context): CoinDB = Room.databaseBuilder(
        context,
        CoinDB::class.java,
        "coin.db"
    ).allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideCoinDao(coinDB: CoinDB): CoinDao = coinDB.myCoinDao()
}