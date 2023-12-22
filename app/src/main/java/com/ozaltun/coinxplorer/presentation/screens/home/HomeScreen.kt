package com.ozaltun.coinxplorer.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.domain.model.Coin
import com.ozaltun.coinxplorer.presentation.components.CoinDialog
import com.ozaltun.coinxplorer.presentation.components.CoinList
import com.ozaltun.coinxplorer.presentation.components.SearchBar
import com.ozaltun.coinxplorer.presentation.screens.search.SearchScreenViewModel
import com.ozaltun.coinxplorer.util.constant.Dimens.ExtraSmallPadding
import com.ozaltun.coinxplorer.util.constant.Dimens.MediumPadding1
import timber.log.Timber

@Composable
fun HomeScreen(
    state: HomeState,
    navigateToSearch: () -> Unit,
    navigateToDetails: (String) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    if (viewModel.dialogState) {
        CoinDialog(
            onDismissRequest = { viewModel.dialogState = false },
            textTitle = stringResource(id = R.string.alert),
            textSubTitle = "${state.error}",
            icon = Icons.Default.Warning
        )
    }
        Scaffold(modifier = Modifier.fillMaxSize()) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(paddingValues = it)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = ExtraSmallPadding)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_big),
                        contentDescription = "logo big",
                        modifier = Modifier
                            .width(200.dp)
                            .height(90.dp)
                            .padding(horizontal = MediumPadding1)
                    )
                    Spacer(modifier = Modifier.height(ExtraSmallPadding))
                    SearchBar(
                        modifier = Modifier
                            .padding(horizontal = MediumPadding1)
                            .fillMaxWidth(),
                        text = "",
                        readOnly = true,
                        onValueChange = {},
                        onSearch = {},
                        onClick = navigateToSearch
                    )
                    Spacer(modifier = Modifier.height(ExtraSmallPadding))
                    CoinList(
                        coin = state.data,
                        onClick = navigateToDetails
                    )
                }
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (state.isLoading) {
                    CircularProgressIndicator(color = colorResource(id = R.color.primary))
                }
            }
        }
}