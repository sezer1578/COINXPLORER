package com.ozaltun.coinxplorer.util.constant

import android.content.Context
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.di.CoinxplorerApp

object Constant {
    private fun getContext(): Context {
        return CoinxplorerApp.getAppContext()
    }

    val CLIENT_ERROR = getContext().getString(R.string.error_unknown)
    val SERVER_ERROR = getContext().getString(R.string.error_connection_timeout)
    val NETWORK_ERROR = getContext().getString(R.string.error_connection_not_found)
    val HTTP_UNKNOWN_ERROR = getContext().getString(R.string.error_unknown)
    val UNKNOWN_ERROR = getContext().getString(R.string.error_unknown)
    val MANY_REQUEST_ERROR = getContext().getString(R.string.error_many_request)
    val SOCKET_TIMEOUT_ERROR = getContext().getString(R.string.error_connection_timeout)

    const val USER_SETTINGS = "user_settings"
    const val APP_ENTRY = "app_entry"

    const val TITLE = "COINXPLORER"
    val DESCRIPTION = getContext().getString(R.string.description_noti)
    const val CHANNEL_ID = "Coin"
    const val CHANNEL_NAME = "CoinChannel"
    const val SYNC_DATA_WORK_NAME = "syncDataWorkName"
    const val SYNC_DATA = "syncData"
}