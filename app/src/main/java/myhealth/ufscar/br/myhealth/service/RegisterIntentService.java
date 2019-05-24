package myhealth.ufscar.br.myhealth.service;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.List;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.database.PatientDAO;
import myhealth.ufscar.br.myhealth.database.RegisterDAO;
import myhealth.ufscar.br.myhealth.usecases.CollectData;
import myhealth.ufscar.br.myhealth.utils.InternetConnectUtil;

public class RegisterIntentService extends IntentService {
    public static final String ACTION = "myhealth.ufscar.br.myhealth.service.RegisterIntentService";


    public RegisterIntentService() {
        super("RegisterIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i(getClass().getSimpleName(), "onHandleIntent - Register service started");
        int sync = 0;
        if (InternetConnectUtil.isConnected(getBaseContext())) {
            Log.i(getClass().getName(), "onHandleIntent - Internet connected");
            PatientDAO patientDAO = new PatientDAO(getBaseContext());
            RegisterDAO registerDAO = new RegisterDAO(getBaseContext());
            List<Register> list = registerDAO.registerToSync();


            if (list.size() > 0) {
                for (Register register : list) {
                    Patient patient = patientDAO.getPatientById(register.getId_patient());
                    Log.i(getClass().getSimpleName(), "Register: " + register.toString());
                    Log.i(getClass().getSimpleName(), "Patient: " + patient.toString());
                    if (patient != null) {
                        if (CollectData.execute(patient, register) == true) {
                            Log.i(getClass().getSimpleName(), "Register synchronized: " + register.getId());
                            sync++;
                            registerDAO.setRegisterSync(register.getId(), patient.getId(), 1);
                        } else {
                            Log.i(getClass().getSimpleName(), "Could not sync: " + register.getId());
                        }
                    }
                }

            }
        } else {
            Log.i(getClass().getSimpleName(), "onHandleIntent - Internet not connected");
        }
        if (sync > 0) {
            Intent in = new Intent(ACTION);
            in.putExtra("resultCode", Activity.RESULT_OK);
            in.putExtra("resultValue", "myvalue test");
            LocalBroadcastManager.getInstance(this).sendBroadcast(in);
        }

    }





}
