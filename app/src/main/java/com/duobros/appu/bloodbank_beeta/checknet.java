package com.duobros.appu.bloodbank_beeta;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by appu on 5/8/17.
 */
public class checknet {
    Context cn123;
    checknet(Context cn){
        cn123=cn;
    }
    public boolean checknetw()
    {


            final ConnectivityManager connMgr = (ConnectivityManager)
                    cn123.getSystemService(Context.CONNECTIVITY_SERVICE);
            final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifi.isConnectedOrConnecting () || mobile.isConnectedOrConnecting ())
                return true;
            else
                return false;
                //Toast.makeText(this, "No Network ", Toast.LENGTH_LONG).show()
    }
}
