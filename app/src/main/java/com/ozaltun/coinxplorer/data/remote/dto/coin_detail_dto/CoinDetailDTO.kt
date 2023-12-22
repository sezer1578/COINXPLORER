package com.ozaltun.coinxplorer.data.remote.dto.coin_detail_dto

import com.ozaltun.coinxplorer.data.remote.dto.Description
import com.ozaltun.coinxplorer.data.remote.dto.Image
import com.ozaltun.coinxplorer.domain.model.CoinDetail

data class CoinDetailDTO(
    val additional_notices: List<Any>,
    val asset_platform_id: Any,
    val block_time_in_minutes: Int,
    val categories: List<String>,
    val coingecko_rank: Int,
    val coingecko_score: Double,
    val community_score: Double,
    val country_origin: String,
    val description: Description,
    val developer_score: Double,
    val genesis_date: String,
    val hashing_algorithm: String,
    val id: String,
    val image: Image,
    val last_updated: String,
    val liquidity_score: Double,
    val market_cap_rank: Int,
    val name: String,
    val public_interest_score: Double,
    val public_notice: Any,
    val sentiment_votes_down_percentage: Double,
    val sentiment_votes_up_percentage: Double,
    val status_updates: List<Any>,
    val symbol: String,
    val market_data:MarketData
)

fun CoinDetailDTO.toCoinDetail(): CoinDetail {
    return CoinDetail(
        id = id,
        description = description,
        image = image,
        last_updated = last_updated,
        name = name,
        sentiment_votes_down_percentage = sentiment_votes_down_percentage,
        sentiment_votes_up_percentage = sentiment_votes_up_percentage,
        symbol = symbol,
        hashing_algorithm = hashing_algorithm,
        currentPrice = market_data.current_price.usd
    )
}
