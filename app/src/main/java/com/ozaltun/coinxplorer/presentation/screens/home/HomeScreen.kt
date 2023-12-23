package com.ozaltun.coinxplorer.presentation.screens.home

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.work.WorkInfo
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.domain.model.Coin
import com.ozaltun.coinxplorer.presentation.MainActivity
import com.ozaltun.coinxplorer.presentation.components.CoinDialog
import com.ozaltun.coinxplorer.presentation.components.CoinList
import com.ozaltun.coinxplorer.presentation.components.CoinTopBar
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
    val lifecycleOwner = LocalLifecycleOwner.current
    val workInfo by viewModel.workInfoLiveData.observeAsState(initial = emptyList())
    DisposableEffect(Unit) {
        val workInfoLiveData = viewModel.workInfoLiveData
        val observer = Observer<List<WorkInfo>> { listOfWorkInfo ->
            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }

            val workInfo: WorkInfo = listOfWorkInfo[0]

            if (workInfo.state == WorkInfo.State.ENQUEUED) {
                viewModel.getCoins()
            }
        }
        workInfoLiveData.observe(lifecycleOwner, observer)
        onDispose {
            workInfoLiveData.removeObserver(observer)
        }
    }
    val context = LocalContext.current
    if (viewModel.dialogState) {
        CoinDialog(
            onDismissRequest = { viewModel.dialogState = false },
            textTitle = stringResource(id = R.string.alert),
            textSubTitle = "${state.error}",
            icon = Icons.Default.Warning
        )
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CoinTopBar(
                title = { Text(text = "${stringResource(id = R.string.welcome)} ${state.currentUser?.email}") },
                icon = {
                    Icon(
                        imageVector = Icons.Default.ExitToApp, contentDescription = null,
                        tint = colorResource(id = R.color.text),
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                viewModel.signOut()
                                val activity = context as? Activity
                                activity?.finish()
                            }
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = ExtraSmallPadding)
            ) {
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