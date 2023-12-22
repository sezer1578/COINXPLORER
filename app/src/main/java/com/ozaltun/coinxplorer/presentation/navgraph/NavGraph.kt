package com.ozaltun.coinxplorer.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ozaltun.coinxplorer.presentation.screens.home.HomeScreen
import com.ozaltun.coinxplorer.presentation.screens.home.HomeScreenViewModel
import com.ozaltun.coinxplorer.presentation.screens.onboarding.OnBoardingScreen
import com.ozaltun.coinxplorer.presentation.screens.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }
        navigation(
            route = Route.CoinNavigation.route,
            startDestination = Route.CoinNavigatorScreen.route
        ) {
            composable(
                route = Route.CoinNavigatorScreen.route
            ) {
                HomeScreen()
            }
        }
    }
}