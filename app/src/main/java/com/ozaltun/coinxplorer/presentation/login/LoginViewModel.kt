package com.ozaltun.coinxplorer.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel()
{
    var splashCondition by mutableStateOf(true)
        private set
    init {
        viewModelScope.launch {
            delay(300)
            splashCondition = false
        }
    }
}