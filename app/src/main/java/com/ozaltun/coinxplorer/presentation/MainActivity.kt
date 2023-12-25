package com.ozaltun.coinxplorer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.work.WorkInfo
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ozaltun.coinxplorer.presentation.navgraph.NavGraph
import com.ozaltun.coinxplorer.presentation.screens.detail.DetailScreen
import com.ozaltun.coinxplorer.presentation.screens.home.HomeScreen
import com.ozaltun.coinxplorer.presentation.screens.home.HomeScreenViewModel
import com.ozaltun.coinxplorer.presentation.screens.search.SearchScreen
import com.ozaltun.coinxplorer.presentation.ui.theme.COINXPLORERTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    val homeScreenViewModel by viewModels<HomeScreenViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onWorkManager(viewModel, homeScreenViewModel)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }
        setContent {
            COINXPLORERTheme {
                val isSystemDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemDarkMode
                    )
                }
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
    private fun onWorkManager(viewModel: MainViewModel,homeScreenViewModel: HomeScreenViewModel){
        viewModel.workInfo.observe(this@MainActivity) { listOfWorkInfo ->

            if (listOfWorkInfo == null || listOfWorkInfo.isEmpty()) {
                return@observe
            }

            val workInfo: WorkInfo = listOfWorkInfo[0]
            Timber.tag("Sezer").d("WorkInfoState: ${workInfo.state}")
            if (workInfo.state == WorkInfo.State.ENQUEUED) {
                homeScreenViewModel.getCoins()
                Timber.tag("Sezer").d("Work Info If")
            }else
            {
                Timber.tag("Sezer").d("Work Info ELSE")
            }
            Timber.tag("Sezer").d("workinfo $listOfWorkInfo")
        }
    }
}