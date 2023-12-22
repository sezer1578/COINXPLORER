package com.ozaltun.coinxplorer.presentation.screens.detail

import com.ozaltun.coinxplorer.domain.model.CoinDetail


data class DetailScreenState(
    val data: CoinDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
