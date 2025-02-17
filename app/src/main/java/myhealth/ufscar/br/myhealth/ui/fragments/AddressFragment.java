package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.Address;
import myhealth.ufscar.br.myhealth.tasks.PostalCodeTask;
import myhealth.ufscar.br.myhealth.utils.EditTextUtils;
import myhealth.ufscar.br.myhealth.utils.MaskEditUtil;

public class AddressFragment extends Fragment {
    private EditText txtPostcode;
    private EditText txtThoroughfare;
    private EditText txtNumber;
    private EditText txtComplement;
    private EditText txtNeighborhood;
    private EditText txtCity;
    private EditText txtState;

    public AddressFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        initializeComponents(view);
        return view;
    }
    private void initializeComponents(View view){
        SectionData.PATIENT.setAddress(new Address());
        txtPostcode = view.findViewById(R.id.txt_postcode);
        txtThoroughfare = view.findViewById(R.id.txt_thoroughfare);
        txtNumber = view.findViewById(R.id.txt_number);
        txtComplement = view.findViewById(R.id.txt_complement);
        txtNeighborhood = view.findViewById(R.id.txt_neighborhood);
        txtCity = view.findViewById(R.id.txt_city);
        txtState = view.findViewById(R.id.txt_state);


        initFieldsListeners();
    }

    public void initFieldsListeners() {


        txtPostcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int len = txtPostcode.getText().toString().length();
                if (len >= 9) {
                    Log.i("AddressFragment", "Postal code complete");
                    PostalCodeTask task = new PostalCodeTask(getActivity());
                    task.execute(txtPostcode.getText().toString());
                    txtThoroughfare.requestFocus();
                }
            }
        });

        txtPostcode.addTextChangedListener(MaskEditUtil.mask(txtPostcode, MaskEditUtil.FORMAT_CEP));

        txtPostcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.getAddress().setPostcode(txtPostcode.getText().toString());
            }
        });
        txtThoroughfare.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.getAddress().setThoroughfare(txtThoroughfare.getText().toString());
            }
        });

        txtNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.getAddress().setNumber(Integer.parseInt(txtNumber.getText().toString()));
            }
        });

        txtComplement.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.getAddress().setComplement(txtComplement.getText().toString());
            }
        });

        txtNeighborhood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.getAddress().setNeighborhood(txtNeighborhood.getText().toString());
            }
        });
        txtCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.getAddress().setCity(txtCity.getText().toString());
            }
        });
        txtState.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.getAddress().setState(txtState.getText().toString());
            }
        });
    }
}
