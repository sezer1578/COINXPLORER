package com.ozaltun.coinxplorer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.util.converter.Converters

@Database(entities = [CoinDetail::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CoinDB : RoomDatabase(){
    abstract fun myCoinDao(): CoinDao

}