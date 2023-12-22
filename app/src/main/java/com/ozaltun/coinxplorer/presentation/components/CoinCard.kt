package com.ozaltun.coinxplorer.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.TrendingFlat
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.domain.model.Coin
import com.ozaltun.coinxplorer.domain.model.CoinDetail
import com.ozaltun.coinxplorer.domain.model.CoinSearch
import com.ozaltun.coinxplorer.presentation.ui.theme.Shapes
import com.ozaltun.coinxplorer.util.constant.Dimens.ExtraSmallPadding2
import com.ozaltun.coinxplorer.util.constant.Dimens.FontSize
import com.ozaltun.coinxplorer.util.constant.Dimens.FontSizeSmall
import com.ozaltun.coinxplorer.util.constant.Dimens.ImageSize
import timber.log.Timber

@Composable
fun CoinCard(
    coin: Coin,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(ExtraSmallPadding2)
            .clickable { onClick?.invoke() },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_background)
        ),
        shape = Shapes.medium,
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier.padding(
                ExtraSmallPadding2
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(ImageSize)
                    .clip(MaterialTheme.shapes.medium)
                    .padding(ExtraSmallPadding2),
                model = ImageRequest.Builder(context).data(coin.image).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoinText(
                    text = coin.name, style = TextStyle(
                        fontSize = FontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
                CoinText(
                    text = "$${coin.current_price}", style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = FontSizeSmall
                    )
                )
            }
            val coinPricePercentage = coin.price_change_percentage_24h
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End
            ){
                Icon(
                    imageVector = when {
                        coinPricePercentage > 0 -> Icons.Default.TrendingUp
                        coinPricePercentage.toInt() == 0 -> Icons.Default.TrendingFlat
                        else -> Icons.Default.TrendingDown
                    },
                    contentDescription = null,
                    tint = when {
                        coinPricePercentage > 0 -> colorResource(id = R.color.green)
                        coinPricePercentage.toInt() == 0 -> colorResource(id = R.color.primary)
                        else -> colorResource(id = R.color.red)
                    }
                )
                CoinText(
                    text = " % $coinPricePercentage",
                    color = when {
                        coinPricePercentage > 0 -> colorResource(id = R.color.green)
                        coinPricePercentage.toInt() == 0 -> colorResource(id = R.color.primary)
                        else -> colorResource(id = R.color.red)
                    }
                )
            }
        }
    }
}

@Composable
fun CoinDetailCard(
    coin: CoinDetail,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(ExtraSmallPadding2)
            .clickable { onClick?.invoke() },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_background)
        ),
        shape = Shapes.medium,
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier.padding(
                ExtraSmallPadding2
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(ImageSize)
                    .clip(MaterialTheme.shapes.medium)
                    .padding(ExtraSmallPadding2),
                model = ImageRequest.Builder(context).data(coin.image.large).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoinText(
                    text = coin.name, style = TextStyle(
                        fontSize = FontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
                CoinText(
                    text = "$${coin.currentPrice}", style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = FontSizeSmall
                    )
                )
            }
            val coinPricePercentage = coin.price_change_24h
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End
            ){
                Icon(
                    imageVector = when {
                        coinPricePercentage > 0 -> Icons.Default.TrendingUp
                        coinPricePercentage.toInt() == 0 -> Icons.Default.TrendingFlat
                        else -> Icons.Default.TrendingDown
                    },
                    contentDescription = null,
                    tint = when {
                        coinPricePercentage > 0 -> colorResource(id = R.color.green)
                        coinPricePercentage.toInt() == 0 -> colorResource(id = R.color.primary)
                        else -> colorResource(id = R.color.red)
                    }
                )
                CoinText(
                    text = " % $coinPricePercentage",
                    color = when {
                        coinPricePercentage > 0 -> colorResource(id = R.color.green)
                        coinPricePercentage.toInt() == 0 -> colorResource(id = R.color.primary)
                        else -> colorResource(id = R.color.red)
                    }
                )
            }
        }
    }
}