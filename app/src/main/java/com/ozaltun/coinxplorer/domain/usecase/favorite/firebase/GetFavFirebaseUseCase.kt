package com.ozaltun.coinxplorer.domain.usecase.favorite.firebase

import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavFirebaseUseCase @Inject constructor(
    private val fireBaseRepository: FireBaseRepository
) {
    operator fun invoke() : Flow<Resource<List<CoinDetail>>> {
        return fireBaseRepository.getFavourites()
    }
}