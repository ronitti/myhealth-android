package myhealth.ufscar.br.myhealth.tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.database.PatientDAO;
import myhealth.ufscar.br.myhealth.database.RegisterDAO;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.ui.MainActivity;
import myhealth.ufscar.br.myhealth.usecases.CollectedDataLoad;
import myhealth.ufscar.br.myhealth.usecases.GetMonitoringData;
import myhealth.ufscar.br.myhealth.usecases.PatientLoad;

public class PatientMonitoringLoadTask extends AsyncTask<Patient, Integer, PatientMonitoring> {
    private final AlertDialog alertDialog;
    private Context context;

    public PatientMonitoringLoadTask(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        this.context = context;
        builder.setPositiveButton(context.getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog = builder.create();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog.setMessage(alertDialog.getContext().getString(R.string.dialog_lbl_entering));
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

    }

    @Override
    protected PatientMonitoring doInBackground(Patient... patient) {
        PatientMonitoring pm  = GetMonitoringData.execute(patient[0]);

        //TODO implementação em base de dados local

        Log.i("PatientMonitoringLoad", "Registers saved");
        return pm;
    }

    @Override
    protected void onPostExecute(PatientMonitoring pm) {
        super.onPostExecute(pm);
        if(pm != null){
            SectionData.PATIENT_MONITORING = pm;
            Intent intent = new Intent(alertDialog.getContext(), MainActivity.class);
            alertDialog.getContext().startActivity(intent);
            alertDialog.dismiss();
        }
    }
}
