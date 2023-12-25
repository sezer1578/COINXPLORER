package com.ozaltun.coinxplorer.domain.usecase.app_entry

import com.ozaltun.coinxplorer.domain.repository.DataStoreManager

class SaveAppEntry(
    private val dataStoreManager: DataStoreManager
) {

    suspend operator fun invoke(){
        dataStoreManager.saveAppEntry()
    }

}