package com.ozaltun.coinxplorer.domain.usecase.app_entry

import com.ozaltun.coinxplorer.domain.repository.DataStoreManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val dataStoreManager: DataStoreManager
) {

    operator fun invoke(): Flow<Boolean> {
        return dataStoreManager.readAppEntry()
    }

}