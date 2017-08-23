package com.example.admin.volleylibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * Created by Admin on 25-04-2017.
 */

public class InternetConnection {

    public boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivity.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivity.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivity != null) {

                NetworkInfo[] info = connectivity.getAllNetworkInfo();

                if (info != null) {

                    for (int i = 0; i < info.length; i++) {

                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {

                            return true;

                        }

                    }
                }
            }

        }

        return false;
    }

}
