package com.ozaltun.coinxplorer.presentation.screens.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.usecase.local_usecase.DeleteFavCoinUseCase
import com.ozaltun.coinxplorer.domain.usecase.local_usecase.GetFavCoinUseCase
import com.ozaltun.coinxplorer.presentation.screens.detail.DetailScreenState
import com.ozaltun.coinxplorer.util.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    private val getFavCoinUseCase: GetFavCoinUseCase,
    private val getDeleteFavCoinUseCase: DeleteFavCoinUseCase
) : ViewModel() {

    var state by mutableStateOf(FavoriteScreenState(isLoading = true))
    var dialogState by mutableStateOf(false)

    fun getAllFavCoin() {
        viewModelScope.launch(Dispatchers.Main) {
            getFavCoinUseCase.invoke().collect { result ->
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

    fun deleteCoin(coin: CoinDetail) {
        getDeleteFavCoinUseCase.invoke(coin)
        getAllFavCoin()
    }
}