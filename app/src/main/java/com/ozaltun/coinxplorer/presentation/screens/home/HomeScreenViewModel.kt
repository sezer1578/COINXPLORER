package com.ozaltun.coinxplorer.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozaltun.coinxplorer.domain.usecase.GetCoinUseCase
import com.ozaltun.coinxplorer.domain.usecase.firebase.SignOutUseCase
import com.ozaltun.coinxplorer.util.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {
    var state by mutableStateOf(HomeState(isLoading = true))
    var dialogState by mutableStateOf(false)
    fun getCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            getCoinUseCase.invoke().collect { result ->
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
    fun signOut() = signOutUseCase.invoke()
}