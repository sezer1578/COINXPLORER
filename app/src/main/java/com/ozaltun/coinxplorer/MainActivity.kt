package com.ozaltun.coinxplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ozaltun.coinxplorer.presentation.screens.detail.DetailScreen
import com.ozaltun.coinxplorer.presentation.screens.home.HomeScreen
import com.ozaltun.coinxplorer.presentation.screens.search.SearchScreen
import com.ozaltun.coinxplorer.presentation.ui.theme.COINXPLORERTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            COINXPLORERTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SearchScreen()
                }
            }
        }
    }
}