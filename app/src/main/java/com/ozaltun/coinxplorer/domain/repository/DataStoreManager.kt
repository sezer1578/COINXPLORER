package com.ozaltun.coinxplorer.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreManager {
    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}