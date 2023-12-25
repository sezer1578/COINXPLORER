package com.ozaltun.coinxplorer.data.repository

import com.ozaltun.coinxplorer.data.local.CoinDao
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.repository.LocalCoinRepository
import com.ozaltun.coinxplorer.util.extension.getErrorCode
import com.ozaltun.coinxplorer.util.extension.toAppException
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class LocalCoinRepositoryImpl @Inject constructor(
    private val coinDao: CoinDao
) : LocalCoinRepository {
    override fun getAllCoin(): Flow<Resource<List<CoinDetail>>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            emit(Resource.Success(data = coinDao.getAllCoins()))
            emit(Resource.Loading(isLoading = false))
        } catch (error: Throwable) {
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            emit(Resource.Error(exception = exception, errorCode = errorCode))
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun insertCoin(coin: CoinDetail) {
        try {
            return coinDao.addToFav(coin = coin)
        } catch (error: Throwable) {
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            Timber.tag("Room Delete Error").d(exception)
        }
    }

    override fun deleteCoin(coin: CoinDetail) {
        try {
            return coinDao.deleteCoin(coin = coin)
        } catch (error: Throwable) {
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            Timber.tag("Room Delete Error").d(exception)
        }
    }
}