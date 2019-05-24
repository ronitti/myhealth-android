package myhealth.ufscar.br.myhealth.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import myhealth.ufscar.br.myhealth.service.RegisterIntentService;

public class RegisterBroadcastReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(this.getClass().getName(), "Broadcast onReceive starting the service");

        Intent service = new Intent(context, RegisterIntentService.class);
        context.startService(service);
    }
}
