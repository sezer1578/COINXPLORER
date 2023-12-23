package com.ozaltun.coinxplorer.domain.usecase

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo
import com.ozaltun.coinxplorer.util.worker.WorkerInterface
import javax.inject.Inject

class WorkerUseCase @Inject constructor(
    private val workerProvider: WorkerInterface
) {
    operator fun invoke(): LiveData<List<WorkInfo>> {
        return workerProvider.onSuccess()
    }
}