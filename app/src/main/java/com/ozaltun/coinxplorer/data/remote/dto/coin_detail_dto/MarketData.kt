package com.ozaltun.coinxplorer.data.remote.dto.coin_detail_dto

data class MarketData(
    val current_price: CurrentPrice,
    val price_change_percentage_24h: Double
)
