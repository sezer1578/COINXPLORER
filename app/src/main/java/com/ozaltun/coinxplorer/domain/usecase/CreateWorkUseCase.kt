package com.ozaltun.coinxplorer.domain.usecase

import com.ozaltun.coinxplorer.util.worker.WorkerInterface
import javax.inject.Inject

class CreateWorkUseCase @Inject constructor(
    private val workProivder:WorkerInterface
) {
    operator fun invoke() = workProivder.createWork()
}