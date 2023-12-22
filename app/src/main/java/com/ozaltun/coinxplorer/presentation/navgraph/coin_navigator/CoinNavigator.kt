package com.ozaltun.coinxplorer.presentation.navgraph.coin_navigator


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.ozaltun.coinxplorer.domain.model.Coin
import com.ozaltun.coinxplorer.presentation.components.EmptyScreen
import com.ozaltun.coinxplorer.presentation.navgraph.Route
import com.ozaltun.coinxplorer.presentation.navgraph.coin_navigator.component.BottomNavigationItem
import com.ozaltun.coinxplorer.presentation.navgraph.coin_navigator.component.CoinBottomNavigation
import com.ozaltun.coinxplorer.presentation.screens.detail.DetailScreen
import com.ozaltun.coinxplorer.presentation.screens.detail.DetailScreenViewModel
import com.ozaltun.coinxplorer.presentation.screens.favorite.FavoriteScreen
import com.ozaltun.coinxplorer.presentation.screens.home.HomeScreen
import com.ozaltun.coinxplorer.presentation.screens.home.HomeScreenViewModel
import com.ozaltun.coinxplorer.presentation.screens.profile.ProfileScreen
import com.ozaltun.coinxplorer.presentation.screens.search.SearchScreen
import com.ozaltun.coinxplorer.presentation.screens.search.SearchScreenViewModel
import timber.log.Timber

@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = Icons.Default.Home, text = "Home"),
            BottomNavigationItem(icon = Icons.Default.Search, text = "Search"),
            BottomNavigationItem(icon = Icons.Default.Favorite, text = "Favorite"),
            BottomNavigationItem(icon = Icons.Default.AccountCircle, text = "Profile"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.FavoriteScreen.route -> 2
        Route.ProfileScreen.route -> 3
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.FavoriteScreen.route ||
                backStackState?.destination?.route == Route.ProfileScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            CoinBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.FavoriteScreen.route
                        )

                        3 -> navigateToTab(
                            navController = navController,
                            route = Route.ProfileScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            //modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: HomeScreenViewModel = hiltViewModel()
                LaunchedEffect(key1 = Unit) {
                    viewModel.getCoins()
                }
                HomeScreen(
                    state = viewModel.state,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    }, navigateToDetails = {
                        navigateToDetails(
                            navController = navController,
                            coin = it
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchScreenViewModel = hiltViewModel()
                val state = viewModel.state
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetails(
                            navController = navController,
                            coin = it.id
                        )

                    }
                )
            }
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailScreenViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<String>("coin")
                    ?.let { coin ->
                        DetailScreen(
                            id = coin,
                            navigateUp = { navController.navigateUp() }
                        )
                    }

            }
            composable(route = Route.FavoriteScreen.route) {
                OnBackClickStateSaver(navController = navController)
                FavoriteScreen(
                )
            }
            composable(route = Route.ProfileScreen.route) {
                OnBackClickStateSaver(navController = navController)
                ProfileScreen()
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, coin: String) {
    //val coinJson = Gson().toJson(coin)
    navController.currentBackStackEntry?.savedStateHandle?.set("coin", coin)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}