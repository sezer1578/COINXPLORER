package com.ozaltun.coinxplorer.domain.model

import com.ozaltun.coinxplorer.data.remote.dto.Description
import com.ozaltun.coinxplorer.data.remote.dto.Image

data class CoinDetailFav(
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
