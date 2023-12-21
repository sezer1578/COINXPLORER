package com.ozaltun.coinxplorer.domain.repository


import com.ozaltun.coinxplorer.data.remote.dto.coin_search_dto.CoinSearchResponse
import com.ozaltun.coinxplorer.domain.model.Coin
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.model.CoinSearch
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun getCoins(): Flow<Resource<List<Coin>>>
    fun getCoinById(id: String): Flow<Resource<CoinDetail>>
    fun coinSearch(query: String): Flow<Resource<List<CoinSearch>>>
}