package com.ozaltun.coinxplorer.presentation.screens.favorite

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import coil.size.Dimension
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.presentation.components.CoinDetailList
import com.ozaltun.coinxplorer.presentation.components.CoinList
import com.ozaltun.coinxplorer.presentation.components.CoinTopBar
import com.ozaltun.coinxplorer.util.constant.Dimens
import timber.log.Timber

@Composable
fun FavoriteScreen(viewModel: FavoriteScreenViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getAllFavCoin()
    }
    val state = viewModel.state
    viewModel.state.data.forEach {
        Timber.tag("Sezer").d("$it")
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
                        style = TextStyle(fontWeight = FontWeight.Bold,
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
                viewModel.deleteCoin(coin = it)
            })
        }
    }
}