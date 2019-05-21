package myhealth.ufscar.br.myhealth.tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.usecases.PatientLoad;

public class PatientLoadTask extends AsyncTask<User, Integer, Patient> {
    private final AlertDialog alertDialog;
    private Context context;

    public PatientLoadTask(Context context){
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
    protected Patient doInBackground(User... user) {
        Patient patient = null;
        try {
            patient = PatientLoad.load(user[0]);
        } catch (NonRegisteredUserException e) {
            alertDialog.setMessage("Sorry! Try again later.");
            alertDialog.show();
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }
        return patient;
    }

    @Override
    protected void onPostExecute(Patient patient) {
        super.onPostExecute(patient);
        if(patient != null){
            SectionData.PATIENT = patient;
            new CollectedDataLoadTask(alertDialog.getContext()).execute(SectionData.PATIENT);
        }
    }
}
