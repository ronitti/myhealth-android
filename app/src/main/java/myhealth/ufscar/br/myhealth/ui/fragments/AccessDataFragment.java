package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.utils.EditTextUtils;
import myhealth.ufscar.br.myhealth.utils.SecurityUtils;


public class AccessDataFragment extends Fragment {
    private EditText txtEmail;
    private TextInputLayout txtEmailLayout;
    private EditText txtSUSNumber;
    private EditText txtPassword;
    private EditText txtPasswordConfirm;

    public AccessDataFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_access_data, container, false);
        initializeComponents(view);
        return view;
    }

    private void initializeComponents(View view){
        txtEmail = view.findViewById(R.id.txt_email);
        txtEmailLayout = view.findViewById(R.id.txt_email_layout);
        txtSUSNumber = view.findViewById(R.id.txt_sus_number);
        txtPassword = view.findViewById(R.id.txt_password);
        txtPasswordConfirm = view.findViewById(R.id.txt_password_confirm);
        initFieldsListeners();
    }

    public void initFieldsListeners() {
        txtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (!TextUtils.isEmpty(txtEmail.getText()) && Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText()).matches()){
                        SectionData.PATIENT.setEmail(txtEmail.getText().toString());
                        txtEmailLayout.setError(null);
                    }
                }
            }
        });
        txtSUSNumber.addTextChangedListener(EditTextUtils.mask(EditTextUtils.FORMAT_SUS_NUMBER));
        txtSUSNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.setSusNumber(EditTextUtils.unmask(txtSUSNumber.getText().toString()));
            }
        });
        txtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.setPassword(SecurityUtils.hashString(txtPassword.getText().toString()));
            }
        });
        txtPasswordConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    SectionData.PATIENT.setPassword(SecurityUtils.hashString(txtPasswordConfirm.getText().toString()));
            }
        });
    }
}
