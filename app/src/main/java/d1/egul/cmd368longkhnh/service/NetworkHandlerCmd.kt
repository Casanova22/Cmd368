package d1.egul.cmd368longkhnh.service

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

class NetworkHandlerCmd {

    fun connectionError(activity: Activity): Boolean {
        val conMan = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInformation = conMan.activeNetworkInfo
        return internetInformation != null
    }
}