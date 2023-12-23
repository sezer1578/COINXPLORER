package com.ozaltun.coinxplorer.presentation.screens.favorite

import com.google.android.gms.tasks.Task
import com.ozaltun.coinxplorer.domain.model.CoinDetail

data class FavoriteScreenState(
    val data: List<CoinDetail> = emptyList(),
    //val dataFireStore:List<CoinDetail> = emptyList(),
    val deleteFirebase: Task<Void>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
