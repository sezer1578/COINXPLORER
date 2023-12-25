package com.ozaltun.coinxplorer.domain.usecase.favorite.firebase

import com.google.android.gms.tasks.Task
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteFavFirebaseUseCase @Inject constructor(
    private val fireBaseRepository: FireBaseRepository
) {
    operator fun invoke(coinDetail: CoinDetail): Flow<Resource<Task<Void>>> {
        return fireBaseRepository.deleteFromFavourites(coinDetail)
    }
}