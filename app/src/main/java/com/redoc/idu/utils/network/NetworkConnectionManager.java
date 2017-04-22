package com.redoc.idu.utils.network;

import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Utilities of NetworkConnection manager.
 * Created by limen on 2017/4/21.
 */

public class NetworkConnectionManager {

    /**
     * Get wifi status.
     * @param context Application context.
     * @return Status of wifi.
     */
    public static int getWifiStatus(Context context) {
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        if(wifiManager != null){
            return wifiManager.getWifiState();
        }
        return WifiManager.WIFI_STATE_DISABLED;
    }

    /**
     * If wifi is available.
     * @param context Application context.
     * @return True if available, otherwise return false.
     */
    public static boolean isWifiAvailable(Context context) {
        return getWifiStatus(context) == WifiManager.WIFI_STATE_ENABLED;
    }
}
