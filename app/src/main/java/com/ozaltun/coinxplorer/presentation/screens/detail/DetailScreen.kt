package com.ozaltun.coinxplorer.presentation.screens.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.presentation.components.CoinDialog
import com.ozaltun.coinxplorer.presentation.components.CoinText
import com.ozaltun.coinxplorer.presentation.screens.detail.componets.DetailTopBar
import com.ozaltun.coinxplorer.presentation.ui.theme.COINXPLORERTheme
import com.ozaltun.coinxplorer.presentation.ui.theme.Shapes
import com.ozaltun.coinxplorer.util.constant.Dimens

@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = hiltViewModel(),
    id: String,
    navigateUp: () -> Unit
) {
    val state = viewModel.state
    val context = LocalContext.current
    var expanded by remember {
        mutableStateOf(false)
    }
    if (viewModel.dialogState) {
        CoinDialog(
            onDismissRequest = { viewModel.dialogState = false },
            textTitle = stringResource(id = R.string.alert),
            textSubTitle = "${state.error}",
            icon = Icons.Default.Warning
        )
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getCoinById(id = id)
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            DetailTopBar(onBookMarkClick = {

            }) {
                navigateUp.invoke()
            }
        }) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues = it)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    Dimens.ExtraSmallPadding2
                )
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(Dimens.ImageSize)
                                    .clip(MaterialTheme.shapes.medium)
                                    .padding(Dimens.ExtraSmallPadding2),
                                model = ImageRequest.Builder(context).data(state.data?.image?.large)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CoinText(
                                    text = stringResource(id = R.string.current_price),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = Dimens.FontSize
                                    )
                                )
                                CoinText(
                                    text = state.data?.currentPrice.toString(),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = Dimens.FontSizeSmall
                                    )
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CoinText(
                                    text = stringResource(id = R.string.hashing_algorithm),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = Dimens.FontSize
                                    )
                                )
                                CoinText(
                                    text = state.data?.hashing_algorithm.toString(),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = Dimens.FontSizeSmall
                                    )
                                )
                            }
                        }
                        CoinText(
                            text = stringResource(id = R.string.price_change_24h),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = Dimens.FontSize
                            )
                        )
                        val coinPricePercentage = state.data?.price_change_24h
                        if (coinPricePercentage != null) {
                            CoinText(
                                text = "% $coinPricePercentage",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = Dimens.FontSizeSmall
                                ),
                                color =  when {
                                    coinPricePercentage > 0 -> colorResource(id = R.color.green)
                                    coinPricePercentage.toInt() == 0 -> colorResource(id = R.color.primary)
                                    else -> colorResource(id = R.color.red)
                                }
                            )
                        }
                        if (!state.data?.description?.en.isNullOrEmpty()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.description),
                                    fontSize = Dimens.FontSizeSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else
                                        Icons.Filled.KeyboardArrowDown,
                                    contentDescription = "Down Arrow",
                                    modifier = Modifier
                                        .size(25.dp)
                                        .clickable { expanded = !expanded },
                                    tint = colorResource(id = R.color.primary)
                                )
                            }
                            AnimatedVisibility(visible = expanded) {
                                Card(
                                    modifier = Modifier.padding(12.dp),
                                    shape = Shapes.medium,
                                    elevation = CardDefaults.cardElevation(6.dp),
                                    colors = CardDefaults.cardColors(
                                        colorResource(id = R.color.primary)
                                    )
                                ) {
                                    Box(
                                        modifier = Modifier.padding(12.dp)
                                    ) {
                                        Text(
                                            text = "${state.data?.description?.en}",
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = Dimens.FontSize
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
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

@Preview
@Composable
fun DetailScreenPreview() {
    COINXPLORERTheme {
        DetailScreen(id = "bitcoin") {

        }
    }
}