package com.ozaltun.coinxplorer.domain.model

import androidx.room.PrimaryKey

data class Coin(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val current_price: Double,
    val high_24h: Double,
    val image: String,
    val last_updated: String,
    val low_24h: Double,
    val market_cap_rank: Int,
    val name: String,
    val price_change_percentage_24h: Double,
    val symbol: String,
    val total_supply: Double,
    val total_volume: Double
)

