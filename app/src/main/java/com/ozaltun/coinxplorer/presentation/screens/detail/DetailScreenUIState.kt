package com.ozaltun.coinxplorer.presentation.screens.detail

import com.ozaltun.coinxplorer.domain.model.CoinDetail


data class DetailScreenUIState(
    val data: CoinDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
