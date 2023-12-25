package com.ozaltun.coinxplorer.domain.repository


import com.ozaltun.coinxplorer.domain.model.Coin
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.model.CoinSearch
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

interface CoinRepository {
    fun getCoins(): Flow<Resource<List<Coin>>>
    fun getCoinById(id: String): Flow<Resource<CoinDetail>>
    fun coinSearch(query: String): Flow<Resource<List<CoinSearch>>>
    fun currentPriceById(period: Duration, coinId: String): Flow<Resource<Double>>
}