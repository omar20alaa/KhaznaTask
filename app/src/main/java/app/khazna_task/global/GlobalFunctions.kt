package com.app.khazna_task.global

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import es.dmoral.toasty.Toasty

object GlobalFunctions {

    //============================= TODO showSuccessToast ====================================================================================================
    @JvmStatic
    fun showSuccessToast(context: Context?, message: String?) {
        Toasty.success(context!!, message!!, Toast.LENGTH_LONG).show()
    }

    //============================= TODO showSuccessToast ====================================================================================================
    @JvmStatic
    fun showFailToast(context: Context?, message: String?) {
        Toasty.error(context!!, message!!, Toast.LENGTH_LONG).show()
    }

    //============================= TODO showLog ====================================================================================================
    @JvmStatic
    fun showLog(message: String?) {
        Log.d(AppConstants.TAG, message)
    }

    //============================= TODO  isConnected ====================================================================================================
    @JvmStatic
    fun isConnected(context: Context): Boolean {
        var connected = false
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nInfo = cm.activeNetworkInfo
            connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
            return connected
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.message)
        }
        return connected
    }


}