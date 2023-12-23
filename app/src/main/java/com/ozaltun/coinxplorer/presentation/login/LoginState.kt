package com.ozaltun.coinxplorer.presentation.login

import com.google.firebase.auth.AuthResult

data class LoginState(
    val data: AuthResult? = null,
    val success: Boolean? = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
