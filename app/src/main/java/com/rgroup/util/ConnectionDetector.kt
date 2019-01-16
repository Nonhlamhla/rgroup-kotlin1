package com.teacherapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by upasna.mishra on 11/24/2017.
 */
object ConnectionDetector{

     @SuppressLint("MissingPermission")
     @JvmStatic fun isConnected(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo
        return !(info == null || !info.isConnected)

    }
}