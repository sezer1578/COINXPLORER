package com.ozaltun.coinxplorer.domain.usecase

import com.ozaltun.coinxplorer.util.worker.WorkerInterface
import javax.inject.Inject

class WorkerUseCase @Inject constructor(
    private val workerProvider: WorkerInterface
) {
    operator fun invoke() = workerProvider.onSuccess()
}