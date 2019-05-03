package myhealth.ufscar.br.myhealth.tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.List;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.ui.MainActivity;
import myhealth.ufscar.br.myhealth.usecases.CollectedDataLoad;

public class CollectedDataLoadTask extends AsyncTask<Patient, Integer, List<Register>> {
    private final AlertDialog alertDialog;


    public CollectedDataLoadTask(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
    protected List<Register> doInBackground(Patient... patient) {
        return CollectedDataLoad.execute(patient[0]);
    }

    @Override
    protected void onPostExecute(List<Register> registers) {
        super.onPostExecute(registers);
        if (registers != null){
            SectionData.PATIENT_REGISTERS = registers;
        }
        Intent intent = new Intent(alertDialog.getContext(), MainActivity.class);
        alertDialog.getContext().startActivity(intent);
        alertDialog.cancel();
    }
}
