package com.ramprasad.acronymsdemo.commons

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Ramprasad on 1/31/23.
 */
object Utility {
    fun isConnectedToNetwork(getApplicationContext: Context): Boolean {
        val connectionManager = getApplicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectionManager.activeNetwork != null && connectionManager.getNetworkCapabilities(connectionManager.activeNetwork) != null) {
            return true
        }
        return false
    }
}
