package com.ozaltun.coinxplorer.data.repository

import com.ozaltun.coinxplorer.data.remote.CoinxplorerApi
import com.ozaltun.coinxplorer.data.remote.dto.coin_detail_dto.toCoinDetail
import com.ozaltun.coinxplorer.data.remote.dto.coin_dto.toCoin
import com.ozaltun.coinxplorer.data.remote.dto.coin_search_dto.toCoinSearch
import com.ozaltun.coinxplorer.domain.model.Coin
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.model.CoinSearch
import com.ozaltun.coinxplorer.domain.repository.CoinRepository
import com.ozaltun.coinxplorer.util.extension.getErrorCode
import com.ozaltun.coinxplorer.util.extension.toAppException
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.time.Duration

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinxplorerApi,
    @Named("Default") private val coContextDefault: CoroutineDispatcher
) : CoinRepository {
    private var job: Job? = null
    override fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            emit(Resource.Success(data = api.getCoins().map {
                it.toCoin()
            }))
            emit(Resource.Loading(isLoading = false))
        } catch (error: Throwable) {
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            emit(Resource.Error(exception = exception, errorCode = errorCode))
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun getCoinById(id: String): Flow<Resource<CoinDetail>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            emit(Resource.Success(data = api.getCoinById(id = id).toCoinDetail()))
            emit(Resource.Loading(isLoading = false))
        } catch (error: Throwable) {
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            emit(Resource.Error(exception = exception, errorCode = errorCode))
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun coinSearch(query: String): Flow<Resource<List<CoinSearch>>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            emit(Resource.Success(data = api.coinSearch(query = query).coins.map {
                it.toCoinSearch()
            }))
            emit(Resource.Loading(isLoading = false))
        } catch (error: Throwable) {
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            emit(Resource.Error(exception = exception, errorCode = errorCode))
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun currentPriceById(period: Duration, coinId: String): Flow<Resource<Double>> =
        channelFlow {
            job?.cancel()
            job = CoroutineScope(coContextDefault).launch {
                while (true) {
                    send(Resource.Loading(isLoading = true))
                    val data = api.getCoinById(coinId).toCoinDetail()
                    data.currentPrice?.let {
                        send(Resource.Success(it))
                    }
                    delay(period)
                    send(Resource.Loading(isLoading = false))
                }
            }
            awaitClose {
                channel.close()
                job?.cancel()
            }
        }.catch { error ->
            val exception = error.toAppException()
            val errorCode = error.getErrorCode()
            emit(Resource.Error(exception = exception, errorCode = errorCode))
        }
}