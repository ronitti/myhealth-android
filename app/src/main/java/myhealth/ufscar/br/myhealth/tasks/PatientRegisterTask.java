package myhealth.ufscar.br.myhealth.tasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.ExistingSusNumberException;
import myhealth.ufscar.br.myhealth.usecases.PatientRegister;

public class PatientRegisterTask extends AsyncTask<Patient, User, Boolean> {

    private AlertDialog alertDialog;
    private int code = 0;
    private UserRegisterTask.OnTaskInteraction mListener;

    public PatientRegisterTask(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton(activity.getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog = builder.create();

        if (activity instanceof UserRegisterTask.OnTaskInteraction) {
            mListener = (UserRegisterTask.OnTaskInteraction) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog.setMessage(alertDialog.getContext().getString(R.string.dialog_lbl_registering));
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    @Override
    protected Boolean doInBackground(Patient... patients) {
        try {
            Log.i("PatientRegisterTask", patients[0].toString());
            return PatientRegister.register(patients[0]);
        }catch (ExistingSusNumberException e){
            code = 1;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if(code == 1){
            alertDialog.setMessage(alertDialog.getContext().getString(R.string.dialog_error_existing_sus));
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setText(alertDialog.getContext().getString(R.string.dialog_btn_try_again));
        }else{
            alertDialog.cancel();
        }
        if (mListener != null) {
            SectionData.PATIENT_REGISTERS = new ArrayList<>();
            mListener.onTaskFinsh(code == 0);
        }
    }
    public interface OnTaskInteraction{
        void onTaskFinsh(boolean result);
    }
}
