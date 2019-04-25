package net.virtualmon.data.net

import android.content.Context
import android.net.ConnectivityManager
import net.virtualmon.data.ApplicationContext
import javax.inject.Inject

/**
 * Created by AlbertM on 21/04/19.
 */
class ConnectionChecker @Inject constructor(@ApplicationContext val context: Context) {

    fun thereIsConnectivity(): Boolean {
        val systemService = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

        val activeNetwork = systemService?.activeNetworkInfo

        if(activeNetwork == null || !activeNetwork.isConnectedOrConnecting)
        {
            return false
        }
        return true
    }


}