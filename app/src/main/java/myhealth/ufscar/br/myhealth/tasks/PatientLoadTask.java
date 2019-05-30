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
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.database.PatientDAO;
import myhealth.ufscar.br.myhealth.database.RegisterDAO;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.ui.MainActivity;
import myhealth.ufscar.br.myhealth.usecases.CollectedDataLoad;
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
        PatientDAO dao = new PatientDAO(context);
        Patient patient = null;


        if (dao.isStored(user[0].getId())) {
            Log.i(getClass().getSimpleName(), "doInBackground - Patient is stored" + user[0].getId());
            patient = dao.getPatientById(user[0].getId());
        } else {

            try {
                patient = PatientLoad.load(user[0]);
                patient.setId(user[0].getId());
                patient.setEmail(user[0].getEmail());
                patient.setPassword(user[0].getPassword());

                dao.save(patient);

                Log.i("PatientLoadTask", "Patient was stored in local with id " + patient.getId());
                Log.i("PatientLoadTask", "Loading registers on server ");
                List<Register> registers =CollectedDataLoad.execute(patient);
                if (registers != null && registers.size() > 0) {
                    RegisterDAO registerDAO = new RegisterDAO(context);
                    for (Register register: registers) {
                        register.setId_patient(patient.getId());
                        registerDAO.saveInLocal(register);
                    }
                }
                Log.i("PatientLoadTask", "Registers saved");





            } catch (NonRegisteredUserException e) {
                alertDialog.setMessage("Sorry! Try again later.");
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            }
        }
        return patient;
    }

    @Override
    protected void onPostExecute(Patient patient) {
        super.onPostExecute(patient);
        if(patient != null){
            SectionData.PATIENT = patient;
            Intent intent = new Intent(alertDialog.getContext(), MainActivity.class);
            alertDialog.getContext().startActivity(intent);
            alertDialog.dismiss();
        }
    }
}
