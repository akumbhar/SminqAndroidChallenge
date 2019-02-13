package com.sminq.android.sminqassesment.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectivityUtils {
   companion object {
        fun isNetworkAvailable(mContext: Context): Boolean {
            val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }
    }
}