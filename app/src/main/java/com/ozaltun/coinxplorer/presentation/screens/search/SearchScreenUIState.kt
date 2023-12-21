package com.ozaltun.coinxplorer.presentation.screens.search

import com.ozaltun.coinxplorer.domain.model.CoinSearch

data class SearchScreenUIState(
    val data:List<CoinSearch> = emptyList(),
    val isLoading:Boolean = false,
    val error:String? = null
)
