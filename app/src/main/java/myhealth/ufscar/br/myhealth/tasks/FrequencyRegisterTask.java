package myhealth.ufscar.br.myhealth.tasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.usecases.FrequencyRegister;

public class FrequencyRegisterTask extends AsyncTask<PatientMonitoring, Integer, Boolean> {

    private AlertDialog alertDialog;
    private UserRegisterTask.OnTaskInteraction mListener;

    public FrequencyRegisterTask(Activity activity){
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
    protected Boolean doInBackground(PatientMonitoring... patientMonitorings) {
        return FrequencyRegister.execute(patientMonitorings[0]);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        alertDialog.cancel();
        if (mListener != null) {
            mListener.onTaskFinsh(result);
        }
    }
    public interface OnTaskInteraction{
        void onTaskFinsh(boolean result);
    }
}
