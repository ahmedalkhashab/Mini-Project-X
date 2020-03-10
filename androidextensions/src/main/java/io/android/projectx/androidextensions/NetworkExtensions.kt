package io.android.projectx.androidextensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

@SuppressLint("MissingPermission")
fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    cm?.getNetworkCapabilities(cm.activeNetwork)?.let {
        return it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }
    return false
}


/**
 * Returns connection type. 0: none; 1: mobile data; 2: wifi
 */
@SuppressLint("MissingPermission")
fun getConnectionType(context: Context): Int {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    cm?.getNetworkCapabilities(cm.activeNetwork)?.let {
        return when {
            it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> 2
            it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> 1
            else -> 0
        }
    }
    return 0
}