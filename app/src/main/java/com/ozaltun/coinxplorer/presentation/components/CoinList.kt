package com.ozaltun.coinxplorer.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ozaltun.coinxplorer.domain.model.Coin
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.model.CoinSearch
import com.ozaltun.coinxplorer.util.constant.Dimens.ExtraSmallPadding2
import com.ozaltun.coinxplorer.util.constant.Dimens.MediumPadding1

@Composable
fun CoinList(
    coin: List<Coin>,
    onClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
            items(
                count = coin.size
            ) {
                coin[it].let { coin ->
                    CoinCard(coin = coin, onClick = { onClick(coin.id) })
                }
            }
    }
}
@Composable
fun CoinDetailList(
    coin: List<CoinDetail>,
    onClick: (CoinDetail) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = coin.size
        ) {
            coin[it].let { coin ->
                CoinDetailCard(coin = coin, onClick = { onClick(coin) })
            }
        }
    }
}