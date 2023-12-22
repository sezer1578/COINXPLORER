package com.ozaltun.coinxplorer.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozaltun.coinxplorer.domain.model.CoinDetail

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin")
    fun getAllCoins(): List<CoinDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFav(coin: CoinDetail)

    @Delete
    fun deleteCoin(coin: CoinDetail)
}