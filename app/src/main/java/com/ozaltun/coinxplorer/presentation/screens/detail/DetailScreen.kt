package com.ozaltun.coinxplorer.presentation.screens.detail

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import timber.log.Timber

@Composable
fun DetailScreen(viewModel: DetailScreenViewModel = hiltViewModel()) {
    val state = viewModel.state
    LaunchedEffect(key1 = Unit) {
        viewModel.getCoinById(id = "bitcoin")
    }
    Scaffold(modifier = Modifier.fillMaxWidth()) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues = it)
        ) {
            Timber.tag("Sezer").d(state.data?.description.toString())
        }
    }
}