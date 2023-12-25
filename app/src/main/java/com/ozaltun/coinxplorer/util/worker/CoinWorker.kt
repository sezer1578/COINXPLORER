package com.ozaltun.coinxplorer.util.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ozaltun.coinxplorer.domain.repository.CoinRepository
import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import com.ozaltun.coinxplorer.domain.usecase.GetCoinByIdUseCase
import com.ozaltun.coinxplorer.domain.usecase.GetCoinUseCase
import com.ozaltun.coinxplorer.util.constant.Constant.DESCRIPTION
import com.ozaltun.coinxplorer.util.constant.Constant.TITLE
import com.ozaltun.coinxplorer.util.network.Resource
import com.ozaltun.coinxplorer.util.notification.NotificationUtils
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class CoinWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getFavFirebaseUseCase: FireBaseRepository,
    private val getCoinByIdUseCase: GetCoinByIdUseCase
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Timber.tag("Sezer").d("doWork before try")
        return try {
            Timber.tag("Sezer").d("doWork")
            var isEqualState = true

            getFavFirebaseUseCase.getFavourites().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        Timber.tag("Sezer").d("Resource.Success ${result.data}")
                        if (result.data.isNotEmpty()) {
                            result.data.forEach { coinFav ->
                                Timber.tag("Sezer").d(coinFav.name)
                                getCoinByIdUseCase.invoke(id = coinFav.id).collect { resultCoins ->
                                    when (resultCoins) {
                                        is Resource.Success -> {
                                            val oldPrice = coinFav.currentPrice
                                            val newPrice = resultCoins.data.currentPrice
                                            //resultCoins.data.forEach { coin ->
                                                if (oldPrice != newPrice) {
                                                    isEqualState = !isEqualState
                                                    Timber.tag("Sezer").d(isEqualState.toString())
                                                } else {
                                                    Timber.tag("Sezer").d("if else equals")
                                                }
                                            //}
                                        }
                                        is Resource.Error -> {
                                            Timber.tag("Sezer").d("Resource.Error ${resultCoins.exception}")
                                        }
                                        else -> {
                                            Timber.tag("Sezer").d("getCoinUseCase ELSE")
                                        }
                                    }
                                }
                            }
                            if (isEqualState) {
                                NotificationUtils.showNotification(
                                    applicationContext,
                                    TITLE,
                                    DESCRIPTION
                                )
                            } else {
                                Timber.tag("Sezer").d("if else notification")
                            }
                        }else{
                            Timber.tag("Sezer").d("Result is Empty")
                        }
                    }

                    else -> {
                        Timber.tag("Sezer").d("result Else ")
                    }
                }
            }
            Timber.tag("Sezer").d("Before Success")
            Result.success()
        } catch (exception: Exception) {
            Timber.tag("Sezer").d("catch: ${exception.printStackTrace()}")
            Timber.tag("Sezer").d("catch: ${exception.message}")
            Result.failure()
        }
    }
}