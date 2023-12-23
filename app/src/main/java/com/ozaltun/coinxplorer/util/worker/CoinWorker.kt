package com.ozaltun.coinxplorer.util.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ozaltun.coinxplorer.domain.repository.CoinRepository
import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import com.ozaltun.coinxplorer.domain.usecase.GetCoinUseCase
import com.ozaltun.coinxplorer.util.constant.Constant.DESCRIPTION
import com.ozaltun.coinxplorer.util.constant.Constant.TITLE
import com.ozaltun.coinxplorer.util.network.Resource
import com.ozaltun.coinxplorer.util.notification.NotificationUtils
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach

@HiltWorker
class CoinWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val authRepository: FireBaseRepository,
    private val getCoinUseCase: GetCoinUseCase
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return try {
            var isEqualState = true

            authRepository.getFavourites().collect { result ->
                when (result) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        if (result.data.isNotEmpty()) {
                            result.data.forEach { coinFav ->
                                getCoinUseCase.invoke().collect { resultCoins ->
                                    when (resultCoins) {
                                        is Resource.Error -> {

                                        }
                                        is Resource.Loading -> {

                                        }
                                        is Resource.Success -> {
                                            resultCoins.data.forEach { coin ->
                                                if (coinFav.name == coin.name && coinFav.currentPrice != coin.current_price) {
                                                    isEqualState = !isEqualState
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (isEqualState) NotificationUtils.showNotification(
                                applicationContext,
                                TITLE,
                                DESCRIPTION
                            )
                        }
                    }

                    is Resource.Error -> {
                        Result.failure()
                    }
                }
            }
            Result.success()
        } catch (exception: Exception) {
            Result.failure()
        }
    }

}