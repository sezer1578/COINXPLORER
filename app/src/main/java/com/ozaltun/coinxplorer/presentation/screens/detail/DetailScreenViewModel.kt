package com.ozaltun.coinxplorer.presentation.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.usecase.GetCoinByIdUseCase
import com.ozaltun.coinxplorer.domain.usecase.GetCurrentPriceById
import com.ozaltun.coinxplorer.domain.usecase.favorite.firebase.AddToFavFirebaseUseCase
import com.ozaltun.coinxplorer.domain.usecase.favorite.local_usecase.InsertFavCoinUseCase
import com.ozaltun.coinxplorer.util.constant.Constant.COIN_ID
import com.ozaltun.coinxplorer.util.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    private val insertFavCoinUseCase: InsertFavCoinUseCase,
    private val addToFavFirebaseUseCase: AddToFavFirebaseUseCase,
    private val getCurrentPriceById: GetCurrentPriceById,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    var state by mutableStateOf(DetailScreenState(isLoading = true))
    var dialogState by mutableStateOf(false)
    init {
        savedStateHandle.get<String>(COIN_ID)?.let {
            getCoinById(it)
        }
    }
    fun getCoinById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCoinByIdUseCase.invoke(id = id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = result.isLoading
                        )
                    }

                    is Resource.Success -> {
                        state = state.copy(
                            data = result.data
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            error = result.exception.message.toString()
                        )
                        if (state.error != null) {
                            dialogState = true
                        }
                    }
                }
            }
        }
    }

    fun insertFavCoin(coin: CoinDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFavCoinUseCase.invoke(coin = coin)
        }
    }
    fun getCurrentPriceById(period: Duration){
        viewModelScope.launch {
            savedStateHandle.get<String>(COIN_ID)?.let {
                getCurrentPriceById.invoke(period, it).collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = result.isLoading
                            )
                        }
                        is Resource.Success ->{
                            state = state.copy(
                                currentPrice = result.data
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                error = result.exception.message.toString()
                            )
                            if (state.error != null) {
                                dialogState = true
                            }
                        }
                    }
                }
            }
        }
    }
    fun addToFavFireStore(coin: CoinDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            addToFavFirebaseUseCase.invoke(coin = coin).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = result.isLoading
                        )
                    }

                    is Resource.Success -> {
                        state = state.copy(
                            dataFireStore = result.data
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            error = result.exception.message.toString()
                        )
                        if (state.error != null) {
                            dialogState = true
                        }
                    }
                }
            }
        }
    }


}