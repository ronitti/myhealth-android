package myhealth.ufscar.br.myhealth.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class InternetConnectUtil {

    public static Boolean isConnected(Context ctx){
        Context CONTEXT = ctx;
        ConnectivityManager conectivtyManager = (ConnectivityManager) CONTEXT.getSystemService(CONTEXT.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }

    }
}
