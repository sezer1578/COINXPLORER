package com.ozaltun.coinxplorer.data.remote


import com.ozaltun.coinxplorer.BuildConfig.GET_COINS
import com.ozaltun.coinxplorer.BuildConfig.GET_COIN_BY_ID
import com.ozaltun.coinxplorer.BuildConfig.SEARCH_COIN
import com.ozaltun.coinxplorer.data.remote.dto.coin_detail_dto.CoinDetailDTO
import com.ozaltun.coinxplorer.data.remote.dto.coin_dto.CoinDTO
import com.ozaltun.coinxplorer.data.remote.dto.coin_search_dto.CoinSearchDTO
import com.ozaltun.coinxplorer.data.remote.dto.coin_search_dto.CoinSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinxplorerApi {
    @GET(GET_COINS)
    suspend fun getCoins(): List<CoinDTO>

    @GET(GET_COIN_BY_ID)
    suspend fun getCoinById(@Path("id") id: String): CoinDetailDTO

    @GET(SEARCH_COIN)
    suspend fun coinSearch(@Query("query") query: String): List<CoinSearchDTO>
}