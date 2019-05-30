package myhealth.ufscar.br.myhealth.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import myhealth.ufscar.br.myhealth.service.PatientMonitoringIntentService;
import myhealth.ufscar.br.myhealth.service.RegisterIntentService;

public class RegisterBroadcastReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(this.getClass().getName(), "Broadcast onReceive starting the service");

        Intent service_sync = new Intent(context, RegisterIntentService.class);
        Intent service_alert = new Intent(context, PatientMonitoringIntentService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(service_sync);
            context.startForegroundService(service_alert);
        } else {
            context.startService(service_sync);
            context.startService(service_alert);
        }


    }
}
