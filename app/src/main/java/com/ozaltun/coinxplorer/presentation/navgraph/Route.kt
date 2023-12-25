package com.ozaltun.coinxplorer.presentation.navgraph

sealed class Route(
    val route: String
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen : Route(route = "searchScreen")
    object FavoriteScreen : Route(route = "favoriteScreen")
    object DetailsScreen : Route(route = "detailsScreen")
    object ProfileScreen : Route(route = "profileScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object CoinNavigation : Route(route = "coinNavigation")
    object CoinNavigatorScreen:Route(route = "coinNavigator")
}