package com.ozaltun.coinxplorer.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import com.ozaltun.coinxplorer.domain.usecase.GetCoinUseCase
import com.ozaltun.coinxplorer.domain.usecase.WorkerUseCase
import com.ozaltun.coinxplorer.domain.usecase.firebase.GetCurrentUserUseCase
import com.ozaltun.coinxplorer.domain.usecase.firebase.SignOutUseCase
import com.ozaltun.coinxplorer.util.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val workerUseCase: WorkerUseCase
) : ViewModel() {
    var state by mutableStateOf(HomeState(isLoading = true))
    var dialogState by mutableStateOf(false)
    val workInfoLiveData: LiveData<List<WorkInfo>> = workerUseCase()
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
                        getCurrentUser()
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

    private fun getCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentUserUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = result.isLoading
                        )
                    }

                    is Resource.Success -> {
                        state = state.copy(
                            currentUser = result.data
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