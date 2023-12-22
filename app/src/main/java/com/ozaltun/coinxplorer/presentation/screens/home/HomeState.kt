package com.ozaltun.coinxplorer.presentation.screens.home

import com.ozaltun.coinxplorer.domain.model.Coin

data class HomeState(
    val data:List<Coin> = emptyList(),
    val isLoading:Boolean = false,
    val error:String? = null
)
