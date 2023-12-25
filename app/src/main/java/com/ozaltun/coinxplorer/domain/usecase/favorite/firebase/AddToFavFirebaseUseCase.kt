package com.ozaltun.coinxplorer.domain.usecase.favorite.firebase

import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.model.CoinDetailFav
import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import javax.inject.Inject

class AddToFavFirebaseUseCase @Inject constructor(
    private val fireBaseRepository: FireBaseRepository
) {
    operator fun invoke(coin: CoinDetail) = fireBaseRepository.addToFavourites(coin = coin)
}