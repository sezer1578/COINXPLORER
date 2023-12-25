package com.ozaltun.coinxplorer.data.remote.dto.coin_dto

import com.ozaltun.coinxplorer.domain.model.Coin

data class CoinDTO(
    val ath: Double,
    val ath_change_percentage: Double,
    val ath_date: String,
    val atl: Double,
    val atl_change_percentage: Double,
    val atl_date: String,
    val circulating_supply: Double,
    val current_price: Double,
    val fully_diluted_valuation: Long,
    val high_24h: Double,
    val id: String,
    val image: String,
    val last_updated: String,
    val low_24h: Double,
    val market_cap: Long,
    val market_cap_change_percentage_24h: Double,
    val market_cap_rank: Int,
    val max_supply: Double,
    val name: String,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val roi: Any,
    val symbol: String,
    val total_supply: Double,
    val total_volume: Double
)

fun CoinDTO.toCoin(): Coin {
    return Coin(
        id = id,
        current_price = current_price,
        high_24h = high_24h,
        image = image,
        last_updated = last_updated,
        market_cap_rank = market_cap_rank,
        low_24h = low_24h,
        name = name,
        price_change_percentage_24h = price_change_percentage_24h,
        symbol = symbol,
        total_supply = total_supply,
        total_volume = total_volume
    )
}