package myhealth.ufscar.br.myhealth.tasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.ExistingEmailException;
import myhealth.ufscar.br.myhealth.exception.ExistingSusNumberException;
import myhealth.ufscar.br.myhealth.usecases.UserRegister;

public class UserRegisterTask extends AsyncTask<String, User, Boolean> {
    private AlertDialog alertDialog;
    private int code = 0;
    private OnTaskInteraction mListener;

    public UserRegisterTask(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton(activity.getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog = builder.create();

        if (activity instanceof OnTaskInteraction) {
            mListener = (OnTaskInteraction) activity;
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
    protected Boolean doInBackground(String... data) {
        try {
            return UserRegister.register(data[0], data[1]);
        }catch (ExistingEmailException e){
            code = 1;
        }catch (ExistingSusNumberException e){
            code = 2;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (code == 1){
            alertDialog.setMessage(alertDialog.getContext().getString(R.string.dialog_error_existing_email));
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setText(alertDialog.getContext().getString(R.string.dialog_btn_try_again));
        }else if(code == 2){
            alertDialog.setMessage(alertDialog.getContext().getString(R.string.dialog_error_existing_sus));
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setText(alertDialog.getContext().getString(R.string.dialog_btn_try_again));
        }else{
            alertDialog.cancel();
        }
        if (mListener != null) {
            mListener.onTaskFinsh(code == 0);
        }
    }

    public interface OnTaskInteraction{
        void onTaskFinsh(boolean result);
    }

}
