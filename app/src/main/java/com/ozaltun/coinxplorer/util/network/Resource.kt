package com.ozaltun.coinxplorer.util.network

import com.ozaltun.coinxplorer.util.extension.AppException

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val exception: AppException, val errorCode: String? = null) :
        Resource<Nothing>

    data class Loading(val isLoading: Boolean) : Resource<Nothing>
}