package com.ozaltun.coinxplorer.presentation.login

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.presentation.components.CoinTopBar
import com.ozaltun.coinxplorer.presentation.login.component.LoginCard
import com.ozaltun.coinxplorer.presentation.ui.theme.COINXPLORERTheme

@Composable
fun LoginScreen() {
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Scaffold(Modifier.fillMaxSize(),
        topBar = {
            CoinTopBar(
                title = { Text(text = "Giriş Yap") }
            )
        }) {
        BoxWithConstraints(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            LazyRow {
                item {
                    //SignIn
                    LoginCard(
                        title = stringResource(id = R.string.sign_in),
                        buttonText = stringResource(id = R.string.sign_in),
                        text = email.value,
                        onValueChange = { password.value = it },
                        onClick = {}
                    )
                    //SignUp
                    LoginCard(
                        title = stringResource(id = R.string.sign_up),
                        buttonText = stringResource(id = R.string.sign_up),
                        text = email.value,
                        onValueChange = { password.value = it },
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    COINXPLORERTheme {
        LoginScreen()
    }
}