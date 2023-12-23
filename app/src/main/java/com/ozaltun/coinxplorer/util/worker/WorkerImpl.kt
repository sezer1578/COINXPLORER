package com.ozaltun.coinxplorer.util.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ozaltun.coinxplorer.util.constant.Constant.SYNC_DATA
import com.ozaltun.coinxplorer.util.constant.Constant.SYNC_DATA_WORK_NAME
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerImpl @Inject constructor(context: Context) : WorkerInterface {

    private val workManager = WorkManager.getInstance(context)

    private val workConstraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresBatteryNotLow(true)
        .build()

    override fun createWork() {

        val workRequest = PeriodicWorkRequestBuilder<CoinWorker>(
            15, TimeUnit.MINUTES,
            15, TimeUnit.MINUTES
        ).setConstraints(workConstraints).setInitialDelay(15, TimeUnit.MINUTES).addTag(SYNC_DATA)
            .build()

        workManager.enqueueUniquePeriodicWork(
            SYNC_DATA_WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }

    override fun onSuccess() = workManager.getWorkInfosByTagLiveData(SYNC_DATA)
}