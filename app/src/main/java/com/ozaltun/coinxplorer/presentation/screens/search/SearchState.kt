package com.ozaltun.coinxplorer.presentation.screens.search

import com.ozaltun.coinxplorer.domain.model.CoinSearch

data class SearchState(
    val data: List<CoinSearch> = emptyList(),
    val searchQuery:String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
