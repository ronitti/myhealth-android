package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;

public class PersonalDataFragment extends Fragment {
    private EditText txtName;
    private EditText txtDateOfBirth;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private EditText txtMothersName;
    private EditText txtPlaceOfBirth;

    public PersonalDataFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_data, container, false);
        initializeComponents(view);
        return view;
    }
    private void initializeComponents(View view){
        txtName = view.findViewById(R.id.txt_name);
        txtDateOfBirth = view.findViewById(R.id.txt_date_of_birth);
        txtMothersName = view.findViewById(R.id.txt_mothers_name);
        txtPlaceOfBirth = view.findViewById(R.id.txt_place_of_birth);
        radioMale = view.findViewById(R.id.radio_male);
        radioFemale = view.findViewById(R.id.radio_female);
        initFieldListeners();
    }

    public void initFieldListeners() {
        txtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.setName(txtName.getText().toString());

            }
        });
        txtDateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.setDateOfBirth(txtDateOfBirth.getText().toString());

            }
        });
        txtMothersName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.setMothersName(txtMothersName.getText().toString());

            }
        });
        txtPlaceOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.setPlaceOfBirth(txtPlaceOfBirth.getText().toString());
            }
        });
        radioMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SectionData.PATIENT.setGender(isChecked ? 'M' : 'F');
            }
        });
    }
}
