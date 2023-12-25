package com.ozaltun.coinxplorer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ozaltun.coinxplorer.data.remote.dto.Description
import com.ozaltun.coinxplorer.data.remote.dto.Image

@Entity(tableName = "coin")
data class CoinDetail(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val description: Description? = null,
    val image: Image? = null,
    val last_updated: String? = null,
    val name: String? = null,
    val sentiment_votes_down_percentage: Double? = null,
    val sentiment_votes_up_percentage: Double? = null,
    val symbol: String? = null,
    val hashing_algorithm: String? = null,
    val currentPrice: Double? = null,
    val price_change_percentage_24h: Double? = null
)
