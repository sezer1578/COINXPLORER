package com.ozaltun.coinxplorer.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.ozaltun.coinxplorer.domain.usecase.firebase.SignInUseCase
import com.ozaltun.coinxplorer.domain.usecase.firebase.SignUpUseCase
import com.ozaltun.coinxplorer.presentation.screens.home.HomeState
import com.ozaltun.coinxplorer.util.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    var state by mutableStateOf(LoginState())
    var dialogState by mutableStateOf(false)

    var splashCondition by mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            delay(300)
            splashCondition = false
        }
    }

    fun signUp(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        signUpUseCase.invoke(email, password).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    state = state.copy(
                        isLoading = result.isLoading
                    )
                }

                is Resource.Success -> {
                    state = state.copy(
                        data = result.data,
                        success = true
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        error = result.exception.message.toString()
                    )
                    if (state.error != null) {
                        dialogState = true
                    }
                }
            }
        }
    }

    fun signIn(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        signInUseCase.invoke(email = email, password = password).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    state = state.copy(
                        isLoading = result.isLoading
                    )
                }

                is Resource.Success -> {
                    state = state.copy(
                        data = result.data,
                        success = true
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        error = result.exception.message.toString()
                    )
                    if (state.error != null) {
                        dialogState = true
                    }
                }
            }

        }

    }
}