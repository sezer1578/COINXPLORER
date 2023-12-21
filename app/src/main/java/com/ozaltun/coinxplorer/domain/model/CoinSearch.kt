package com.ozaltun.coinxplorer.domain.model

data class CoinSearch(
    val id: String,
    val large: String,
    val market_cap_rank: Int,
    val name: String,
    val symbol: String,
    val thumb: String
)
