package com.ozaltun.coinxplorer.util.worker

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo

interface WorkerInterface {
    fun createWork()

    fun onSuccess(): LiveData<List<WorkInfo>>
}