package com.ozaltun.coinxplorer.presentation.screens.home

import com.google.firebase.auth.FirebaseUser
import com.ozaltun.coinxplorer.domain.model.Coin

data class HomeState(
    val data: List<Coin> = emptyList(),
    val currentUser: FirebaseUser? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
