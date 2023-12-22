package com.ozaltun.coinxplorer.presentation.screens.detail

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
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.ozaltun.coinxplorer.util.constant.Dimens

@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = hiltViewModel(),
    id: String,
    navigateUp: () -> Unit
) {
    val state = viewModel.state
    val context = LocalContext.current
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
                        verticalArrangement = Arrangement.Center,
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