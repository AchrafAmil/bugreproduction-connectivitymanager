package com.fabernovel.experiments.bugreproduction.connectivitymanager

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val apiCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                Log.i("BugReproduction", "network unavailable")
            }

            override fun onAvailable(network: Network) {
                Log.i("BugReproduction", "network available")
            }

            override fun onLost(network: Network) {
                Log.i("BugReproduction", "network lost")
            }
        }

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, apiCallback)
    }
}
