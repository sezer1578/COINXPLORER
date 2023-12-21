package com.ozaltun.coinxplorer.data.remote.dto.coin_search_dto

import com.ozaltun.coinxplorer.domain.model.CoinSearch

data class CoinSearchDTO(
    val api_symbol: String,
    val id: String,
    val large: String,
    val market_cap_rank: Int,
    val name: String,
    val symbol: String,
    val thumb: String
)

fun CoinSearchDTO.toCoinSearch() : CoinSearch {
    return CoinSearch(
        id = id,
        large = large,
        market_cap_rank = market_cap_rank,
        name = name,
        symbol = symbol,
        thumb = thumb
    )
}
