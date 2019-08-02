package com.firehook.doittask.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import timber.log.Timber

/**
 * Created by Vladyslav Bondar on 30.07.2019
 * Skype: diginital
 */

class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (null != intent) {
            var wifiState: NetworkInfo.State? = null
            var mobileState: NetworkInfo.State? = null
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state
            mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state
            if (wifiState != null && mobileState != null
                && NetworkInfo.State.CONNECTED != wifiState
                && NetworkInfo.State.CONNECTED == mobileState
            ) {
                mobileNetwork(context)
            } else if (wifiState != null && mobileState != null
                && NetworkInfo.State.CONNECTED != wifiState
                && NetworkInfo.State.CONNECTED != mobileState
            ) {
                noNetwork(context)
            } else if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
                wifiNetwork(context)
            }
        }
    }

    private fun noNetwork(context: Context) {
        Timber.d("No network connection!")
    }

    private fun mobileNetwork(context: Context) {
        Timber.d("Mobile network connected!")
    }

    private fun wifiNetwork(context: Context) {
        Timber.d("WiFi network connected!")
    }

    fun isConnected(context: Context): Boolean {

        var isConnected = false

        val wifiState: NetworkInfo.State?
        val mobileState: NetworkInfo.State?
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state
        mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state
        if (wifiState != null && mobileState != null
            && NetworkInfo.State.CONNECTED != wifiState
            && NetworkInfo.State.CONNECTED == mobileState
        ) {
            // phone network connect success
            isConnected = true
        } else if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
            // wifi connect success
            isConnected = true
        } else if (wifiState != null && mobileState != null
            && NetworkInfo.State.CONNECTED != wifiState
            && NetworkInfo.State.CONNECTED != mobileState
        ) {
            isConnected = false
        }

        return isConnected
    }

}