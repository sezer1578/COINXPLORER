package com.ozaltun.coinxplorer.domain.usecase.favorite.local_usecase

import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.repository.LocalCoinRepository
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteFavCoinUseCase @Inject constructor(
    private val localCoinRepository: LocalCoinRepository
) {
    operator fun invoke(coin: CoinDetail) {
        return localCoinRepository.deleteCoin(coin = coin)
    }
}