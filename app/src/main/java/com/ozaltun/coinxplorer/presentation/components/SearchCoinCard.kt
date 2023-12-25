package com.ozaltun.coinxplorer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.domain.model.CoinSearch
import com.ozaltun.coinxplorer.presentation.ui.theme.COINXPLORERTheme
import com.ozaltun.coinxplorer.presentation.ui.theme.Shapes
import com.ozaltun.coinxplorer.util.constant.Dimens

@Composable
fun SearchCoinCard(
    coin: CoinSearch,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.ExtraSmallPadding2)
            .clickable { onClick?.invoke() },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_background)
        ),
        shape = Shapes.medium,
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier.padding(
                Dimens.ExtraSmallPadding2
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(Dimens.ImageSize)
                    .clip(MaterialTheme.shapes.medium)
                    .padding(Dimens.ExtraSmallPadding2),
                model = ImageRequest.Builder(context).data(coin.thumb).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                CoinText(
                    text = coin.name, style = TextStyle(
                        fontSize = Dimens.FontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
                CoinText(
                    text = coin.symbol, style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = Dimens.FontSizeSmall
                    )
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End
            ){
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(colorResource(id = R.color.primary), shape = CircleShape)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CoinText(
                        text = coin.market_cap_rank.toString(),
                        color = colorResource(id = R.color.text),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = Dimens.FontSizeSmall
                        )
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun SearchCardPreview() {
    COINXPLORERTheme {
        SearchCoinCard(
            coin = CoinSearch(
                id = "bitcoin",
                large = "",
                market_cap_rank = 1,
                name = "Bitcoin",
                symbol = "BTC",
                thumb = "temp"
            )
        )
    }
}