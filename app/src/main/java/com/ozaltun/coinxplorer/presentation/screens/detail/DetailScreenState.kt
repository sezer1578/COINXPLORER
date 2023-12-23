package com.ozaltun.coinxplorer.presentation.screens.detail

import com.google.android.gms.tasks.Task
import com.ozaltun.coinxplorer.domain.model.CoinDetail


data class DetailScreenState(
    val data: CoinDetail? = null,
    val dataFireStore:Task<Void>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
