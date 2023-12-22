package com.ozaltun.coinxplorer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ozaltun.coinxplorer.data.remote.dto.Description
import com.ozaltun.coinxplorer.data.remote.dto.Image
import com.ozaltun.coinxplorer.data.remote.dto.coin_detail_dto.MarketData

@Entity(tableName = "coin")
data class CoinDetail(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val description: Description,
    val image: Image,
    val last_updated: String,
    val name: String,
    val sentiment_votes_down_percentage: Double,
    val sentiment_votes_up_percentage: Double,
    val symbol: String,
    val hashing_algorithm: String,
    val currentPrice: Double,
    val price_change_24h: Double
)
