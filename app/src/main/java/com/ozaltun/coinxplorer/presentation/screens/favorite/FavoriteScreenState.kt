package com.ozaltun.coinxplorer.presentation.screens.favorite

import com.ozaltun.coinxplorer.domain.model.CoinDetail

data class FavoriteScreenState(
    val data: List<CoinDetail> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
