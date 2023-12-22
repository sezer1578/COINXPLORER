package com.ozaltun.coinxplorer.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozaltun.coinxplorer.domain.usecase.SearchCoinUseCase
import com.ozaltun.coinxplorer.util.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchCoinUseCase: SearchCoinUseCase
) : ViewModel() {
    var state by mutableStateOf(SearchState())
    var dialogState by mutableStateOf(false)

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                state = state.copy(searchQuery = event.searchQuery) //event.searchQuery)
            }

            is SearchEvent.SearchCoins -> {
                searchCoin()
            }
        }
    }



     fun searchCoin() {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch(Dispatchers.IO) {
                searchCoinUseCase.invoke(query = state.searchQuery).collect { result ->
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
    }
}