package com.ozaltun.coinxplorer.presentation.screens.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.presentation.components.CoinDetailList
import com.ozaltun.coinxplorer.presentation.components.CoinDialog
import com.ozaltun.coinxplorer.presentation.components.CoinTopBar
import com.ozaltun.coinxplorer.util.constant.Dimens
import timber.log.Timber

@Composable
fun FavoriteScreen(viewModel: FavoriteScreenViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = Unit) {
        //viewModel.getAllFavCoin()
        viewModel.getFavFromFirebase()
    }
    val state = viewModel.state
    if (viewModel.dialogState) {
        CoinDialog(
            onDismissRequest = { viewModel.dialogState = false },
            textTitle = stringResource(id = R.string.alert),
            textSubTitle = "${state.error}",
            icon = Icons.Default.Warning
        )
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CoinTopBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.fav_screen),
                        color = colorResource(id = R.color.text),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = Dimens.FontSize
                        )
                    )
                }
            )
        }) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues = it)
        ) {
            CoinDetailList(coin = state.data, onClick = {
                //viewModel.deleteCoin(coin = it)
                viewModel.deleteFavFirebase(coin = it)
            })
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (state.isLoading) {
                CircularProgressIndicator(color = colorResource(id = R.color.primary))
            }
        }
    }
}