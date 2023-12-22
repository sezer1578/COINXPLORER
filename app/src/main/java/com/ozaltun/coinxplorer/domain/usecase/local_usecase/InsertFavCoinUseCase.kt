package com.ozaltun.coinxplorer.domain.usecase.local_usecase

import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.repository.LocalCoinRepository
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertFavCoinUseCase @Inject constructor(
    private val localCoinRepository: LocalCoinRepository
) {
    operator fun invoke(coin: CoinDetail) = localCoinRepository.insertCoin(coin = coin)

}