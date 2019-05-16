package myhealth.ufscar.br.myhealth.tasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.exception.WrongPasswordException;
import myhealth.ufscar.br.myhealth.usecases.UserLogin;

public class UserLoginTask extends AsyncTask<String, Integer, User> {
    private static final int CODE_SUCCESS = 0;
    private static final int CODE_WRONG_PASSWORD = 1;
    private static final int CODE_NON_REGISTERED_USER = 2;
    private static final int CODE_NO_CONNECTION = 3;

    private int code = CODE_SUCCESS;

    private AlertDialog alertDialog;


    public UserLoginTask(Activity activity){
        createDialog(activity);
    }

    private void createDialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton(activity.getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
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
    protected User doInBackground(String... data) {
        try {
            return UserLogin.execute(data[0], data[1]);
        }catch (WrongPasswordException e){
            code = CODE_WRONG_PASSWORD;
        }catch (NoConnectionException e){
            code = CODE_NON_REGISTERED_USER;
        }catch (NonRegisteredUserException | IOException e) {
            code = CODE_NO_CONNECTION;
        }
        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setText(alertDialog.getContext().getString(R.string.dialog_btn_try_again));

        if (code == CODE_WRONG_PASSWORD){
            alertDialog.setMessage(alertDialog.getContext().getString(R.string.dialog_error_wrong_password));
        }else if(code == CODE_NON_REGISTERED_USER){
            alertDialog.setMessage(alertDialog.getContext().getString(R.string.dialog_error_email_not_registered));
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setText(alertDialog.getContext().getString(R.string.dialog_btn_try_again));
        }else if(code == CODE_NO_CONNECTION){
            alertDialog.setMessage(alertDialog.getContext().getString(R.string.dialog_error_no_connection));
        }else{
            alertDialog.dismiss();
            new PatientLoadTask(alertDialog.getContext()).execute(user);
        }
    }
}
