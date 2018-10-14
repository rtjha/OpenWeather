package com.openweather

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo



/**
 * This class is for utility methods
 */
class Utils {

    companion object {
        /**
         * Check for internet conection
         */
        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }
}