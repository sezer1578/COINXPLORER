package com.ozaltun.coinxplorer.presentation.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.presentation.MainActivity
import com.ozaltun.coinxplorer.presentation.components.CoinDialog
import com.ozaltun.coinxplorer.presentation.components.CoinTopBar
import com.ozaltun.coinxplorer.presentation.login.component.LoginCard
import com.ozaltun.coinxplorer.presentation.ui.theme.COINXPLORERTheme
import com.ozaltun.coinxplorer.util.constant.Dimens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val state = viewModel.state
    val context = LocalContext.current
    val emailSignIn = remember {
        mutableStateOf("")
    }
    val passwordSignIn = remember {
        mutableStateOf("")
    }
    val emailSignUp = remember {
        mutableStateOf("")
    }
    val passwordSignUp = remember {
        mutableStateOf("")
    }
    if (viewModel.dialogState) {
        CoinDialog(
            onDismissRequest = { viewModel.dialogState = false },
            textTitle = stringResource(id = R.string.alert),
            textSubTitle = "${state.error}",
            icon = Icons.Default.Warning
        )
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
                .padding(start = Dimens.MediumPadding2, end = Dimens.MediumPadding2),
            contentAlignment = Alignment.Center
        ) {
            LazyRow {
                item {
                    //SignIn
                    LoginCard(
                        title = stringResource(id = R.string.sign_in),
                        buttonText = stringResource(id = R.string.sign_in),
                        textMail = emailSignIn.value,
                        textPassword = passwordSignIn.value,
                        onValueChangeMail = { emailSignIn.value = it },
                        onValueChangePassword = { passwordSignIn.value = it },
                        onClick = {
                            viewModel.signIn(emailSignIn.value, passwordSignIn.value)
                            if (state.success == true){
                                val activity = context as? Activity
                                activity?.startActivity(Intent(context, MainActivity::class.java))
                                activity?.finish()
                            }else{
                                viewModel.dialogState = true
                            }
                        }
                    )
                    //SignUp
                    LoginCard(
                        title = stringResource(id = R.string.sign_up),
                        buttonText = stringResource(id = R.string.sign_up),
                        textMail = emailSignUp.value,
                        textPassword = passwordSignUp.value,
                        onValueChangeMail = { emailSignUp.value = it },
                        onValueChangePassword = { passwordSignUp.value = it },
                        onClick = {
                            viewModel.signUp(emailSignUp.value, passwordSignUp.value)
                        }
                    )
                }
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (state.isLoading) {
                CircularProgressIndicator(color = colorResource(id = R.color.primary))
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