package myhealth.ufscar.br.myhealth.tasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.exception.WrongPasswordException;
import myhealth.ufscar.br.myhealth.ui.MainActivity;
import myhealth.ufscar.br.myhealth.usecases.UserLogin;

public class UserLoginTask extends AsyncTask<String, Integer, User> {
    private final AlertDialog alertDialog;
    private int code = 0;


    public UserLoginTask(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog = builder.create();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog.setMessage("Autenticando...");
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

    }

    @Override
    protected User doInBackground(String... data) {
        try {
            return UserLogin.login(data[0], data[1]);
        }catch (WrongPasswordException e){
            code = 1;
        }catch (NonRegisteredUserException e){
            code = 2;
        }
        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        if (code == 1){
            alertDialog.setMessage("Senha incorreta.");
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setText("Tentar novamente");
        }else if(code == 2){
            alertDialog.setMessage("E-mail não cadastrado.");
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setText("Tentar novamente");
        }else{
            Intent intent = new Intent(alertDialog.getContext(), MainActivity.class);
            alertDialog.getContext().startActivity(intent);
            alertDialog.cancel();


        }
    }
}
