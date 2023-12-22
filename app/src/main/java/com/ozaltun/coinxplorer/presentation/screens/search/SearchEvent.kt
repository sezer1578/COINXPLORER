package com.ozaltun.coinxplorer.presentation.screens.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()

    object SearchCoins : SearchEvent()
}