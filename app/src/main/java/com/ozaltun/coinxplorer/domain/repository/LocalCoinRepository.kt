package com.ozaltun.coinxplorer.domain.repository

import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow

interface LocalCoinRepository {
    fun getAllCoin(): Flow<Resource<List<CoinDetail>>>
    fun insertCoin(coin: CoinDetail)
    fun deleteCoin(coin: CoinDetail)
}