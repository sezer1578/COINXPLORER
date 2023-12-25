package com.ozaltun.coinxplorer.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ozaltun.coinxplorer.domain.model.CoinSearch
import com.ozaltun.coinxplorer.util.constant.Dimens

@Composable
fun SearchCoinList(
    coin: List<CoinSearch>,
    onClick: (CoinSearch) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
        contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding2)
    ) {
        items(
            count = coin.size
        ) {
            coin[it].let { coin ->
                SearchCoinCard(coin = coin, onClick = { onClick(coin) })
            }
        }
    }
}