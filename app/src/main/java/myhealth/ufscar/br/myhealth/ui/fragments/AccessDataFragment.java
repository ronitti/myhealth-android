package myhealth.ufscar.br.myhealth.ui.fragments;

import android.content.Context;
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
import myhealth.ufscar.br.myhealth.ui.RegisterActivity;
import myhealth.ufscar.br.myhealth.utils.EditTextUtils;
import myhealth.ufscar.br.myhealth.utils.MaskEditUtil;
import myhealth.ufscar.br.myhealth.utils.SecurityUtils;
import myhealth.ufscar.br.myhealth.validator.CNSValidator;


public class AccessDataFragment extends Fragment {
    private EditText txtEmail;
    private TextInputLayout txtEmailLayout;
    private EditText txtSUSNumber;
    private EditText txtPassword;
    private EditText txtPasswordConfirm;

    RegisterActivity registerActivity;

    private boolean vEmail, vSusNumber, vPassword = false;

    public AccessDataFragment() {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerActivity = (RegisterActivity) context;
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
                        txtEmail.setError(null);
                        vEmail = true;
                    } else {
                        txtEmail.setError(getResources().getText(R.string.hint_error_invalid_email));
                        vEmail = false;
                    }
                    validateAll();
                }
            }
        });
        txtSUSNumber.addTextChangedListener(MaskEditUtil.mask(txtSUSNumber, MaskEditUtil.FORMAT_SUS_NUMBER));
        txtSUSNumber.addTextChangedListener(new CNSValidator(txtSUSNumber));
        txtSUSNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {

                    if (txtSUSNumber.getError() == null && txtSUSNumber.getText().length() > 15) {
                        SectionData.PATIENT.setSusNumber(EditTextUtils.unmask(txtSUSNumber.getText().toString()));
                        vSusNumber = true;
                    } else {
                        vSusNumber = false;
                        txtSUSNumber.setError(getResources().getText(R.string.hint_error_invalid_cns));
                    }
                    validateAll();
                }
            }
        });
        txtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    SectionData.PATIENT.setPassword(SecurityUtils.hashString(txtPassword.getText().toString()));
                    if (!txtPasswordConfirm.getText().toString().equals("")){
                        isEqualsPasswords();
                    }
                    validateAll();
                }
            }
        });
        txtPasswordConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    SectionData.PATIENT.setPassword(SecurityUtils.hashString(txtPasswordConfirm.getText().toString()));
                    isEqualsPasswords();
                    validateAll();
                }
            }
        });


    }

    public void isEqualsPasswords() {
        if (!txtPasswordConfirm.getText().toString().equals(txtPassword.getText().toString())) {
            txtPasswordConfirm.setError(getResources().getText(R.string.hint_error_invalid_password_confirmation));
            txtPassword.setError(getResources().getText(R.string.hint_error_invalid_password_confirmation));
            vPassword = false;
        } else  {
            vPassword = true;
            txtPasswordConfirm.setError(null);
            txtPassword.setError(null);
        }
    }


    public  void validateAll() {
        if(vEmail && vSusNumber && vPassword) {
            registerActivity.setBtnNext(true);
        } else {
            registerActivity.setBtnNext(false);
        }
    }



}
