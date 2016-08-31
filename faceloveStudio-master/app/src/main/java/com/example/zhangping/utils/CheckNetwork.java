package com.example.zhangping.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Zhangping on 15/12/20.
 */
public class CheckNetwork {

    public static boolean isNetworkConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null)
        {
            NetworkInfo networkInfo[] = cm.getAllNetworkInfo();
            if (networkInfo != null)
            {
                for (NetworkInfo info :networkInfo
                     ) {
                    if (info.isConnected())
                    {
                        return true;
                    }
                }
            }
        }
    return false;
    }

    public static boolean isWifiConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null)
        {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni != null && ni.getType() == ConnectivityManager.TYPE_WIFI)
            {
                return  true;
            }
        }
        return false;
    }
}
