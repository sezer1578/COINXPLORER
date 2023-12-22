package com.ozaltun.coinxplorer.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ozaltun.coinxplorer.domain.model.Coin
import com.ozaltun.coinxplorer.util.constant.Dimens.ExtraSmallPadding2
import com.ozaltun.coinxplorer.util.constant.Dimens.MediumPadding1

@Composable
fun CoinList(
    modifier: Modifier,
    coin: List<Coin>,
    onClick: (Coin) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = coin.size
        ) {
            coin[it].let { coin ->
                CoinCard(coin = coin, onClick = { onClick(coin) })
            }
        }
    }
}