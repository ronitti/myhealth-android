package myhealth.ufscar.br.myhealth.tasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.Address;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.repository.PostalCodeClient;
import myhealth.ufscar.br.myhealth.repository.PostalCodeService;
import myhealth.ufscar.br.myhealth.repository.query.PostalCodeLoadResponse;
import myhealth.ufscar.br.myhealth.ui.fragments.AddressFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostalCodeTask extends AsyncTask<String, Integer, Address> {

    private Activity activity;
    private final AlertDialog alertDialog;

    private EditText txtPostcode;
    private EditText txtThoroughfare;
    private EditText txtNumber;
    private EditText txtComplement;
    private EditText txtNeighborhood;
    private EditText txtCity;
    private EditText txtState;



    public PostalCodeTask(Activity activity) {
        this.activity = activity;

        txtPostcode = activity.findViewById(R.id.txt_postcode);
        txtThoroughfare = activity.findViewById(R.id.txt_thoroughfare);
        txtNumber = activity.findViewById(R.id.txt_number);
        txtComplement = activity.findViewById(R.id.txt_complement);
        txtNeighborhood = activity.findViewById(R.id.txt_neighborhood);
        txtCity = activity.findViewById(R.id.txt_city);
        txtState = activity.findViewById(R.id.txt_state);

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
        alertDialog.setMessage("Buscando...");
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    @Override
    protected Address doInBackground(String... strings) {
        Address addr = null;
        String postalCode = strings[0];
        PostalCodeService service = PostalCodeClient.getPostalCodeServiceInstance();
        try {
            Response<PostalCodeLoadResponse> response = service.getAddresByPostalCode(postalCode).execute();
            PostalCodeLoadResponse cep = response.body();
            Log.i("PostalCodeTask", cep.toString());

            if (cep != null) {
                addr = new Address();
                addr.setPostcode(cep.getCep());
                addr.setThoroughfare(cep.getLogradouro());
                addr.setNeighborhood(cep.getBairro());
                addr.setState(cep.getUf());
                addr.setCity(cep.getLocalidade());
                addr.setComplement(cep.getComplemento());
            }

        } catch (IOException e ) {
            Log.e("PostalCodeTask", e.getMessage());
        }


        return addr;
    }


    @Override
    protected void onPostExecute(Address address) {
        super.onPostExecute(address);
        if (address != null && address.getPostcode() != null) {
            Log.i("AddressLoad", address.toString());

            txtThoroughfare.setText(address.getThoroughfare());
            txtNeighborhood.setText(address.getNeighborhood());
            txtCity.setText(address.getCity());
            txtComplement.setText(address.getComplement());
            txtState.setText(address.getState());
            alertDialog.dismiss();

            SectionData.PATIENT.getAddress().setThoroughfare(address.getThoroughfare());
            SectionData.PATIENT.getAddress().setNeighborhood(address.getNeighborhood());
            SectionData.PATIENT.getAddress().setCity(address.getCity());
            SectionData.PATIENT.getAddress().setComplement(address.getComplement());
            SectionData.PATIENT.getAddress().setState(address.getState());



        } else {
            txtThoroughfare.setText("");
            txtNeighborhood.setText("");
            txtCity.setText("");
            txtComplement.setText("");
            txtState.setText("");
            alertDialog.setMessage("The postal code is invalid");
            alertDialog.show();
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);

            txtPostcode.requestFocus();
        }


    }




}
