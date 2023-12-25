package com.ozaltun.coinxplorer.presentation.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.domain.model.CoinSearch
import com.ozaltun.coinxplorer.presentation.components.CoinDialog
import com.ozaltun.coinxplorer.presentation.components.SearchBar
import com.ozaltun.coinxplorer.presentation.components.SearchCoinList
import com.ozaltun.coinxplorer.util.constant.Dimens
import com.ozaltun.coinxplorer.util.constant.Dimens.ExtraSmallPadding
import com.ozaltun.coinxplorer.util.constant.Dimens.ExtraSmallPadding2
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (CoinSearch) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    DisposableEffect(Unit) {
        keyboardController?.show()
        onDispose {
            keyboardController?.hide()
        }
    }
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
                    .padding(
                        top = ExtraSmallPadding2,
                        start = ExtraSmallPadding2,
                        end = ExtraSmallPadding2
                    )
                    .statusBarsPadding()
            ) {
                SearchBar(
                    text = state.searchQuery,
                    readOnly = false,
                    onValueChange = {
                        event(SearchEvent.UpdateSearchQuery(it))
                    },
                    onSearch = {
                        event(SearchEvent.SearchCoins)
                        keyboardController?.hide()
                    }
                )
                Spacer(modifier = Modifier.height(ExtraSmallPadding))
                SearchCoinList(
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