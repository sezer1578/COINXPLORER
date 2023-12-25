package com.ozaltun.coinxplorer.domain.usecase

import com.ozaltun.coinxplorer.domain.repository.CoinRepository
import javax.inject.Inject
import kotlin.time.Duration

class GetCurrentPriceById @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(period: Duration, coinId: String) =
        coinRepository.currentPriceById(period, coinId)
}