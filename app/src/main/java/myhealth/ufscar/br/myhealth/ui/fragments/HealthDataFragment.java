package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.Objects;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.ui.RegisterActivity;
import myhealth.ufscar.br.myhealth.ui.SignUpStep;
import myhealth.ufscar.br.myhealth.usecases.NCDRegister;

public class HealthDataFragment extends Fragment {
    private CheckBox chkHypertension;
    private CheckBox chkCoronaryDisease;
    private CheckBox chkDiabetes;
    private CheckBox chkObesity;

    public HealthDataFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health_data, container, false);
        initializeComponents(view);
        return view;
    }

    private void initializeComponents(View view){
        chkHypertension = view.findViewById(R.id.chk_hypertension);
        chkCoronaryDisease = view.findViewById(R.id.chk_coronary_disease);
        chkDiabetes = view.findViewById(R.id.chk_diabetes);
        chkObesity = view.findViewById(R.id.chk_obesity);
        initFieldsListeners();
    }


    public void initFieldsListeners() {
        chkHypertension.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NCDRegister.register(SectionData.PATIENT_MONITORING, NCD.HYPERTENSION, isChecked);
                ((RegisterActivity) Objects.requireNonNull(getActivity())).getActivatedSteps()[SignUpStep.MONITORING_HYPERTENSION_SETTINGS.getStep()] = isChecked;
            }
        });
        chkCoronaryDisease.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NCDRegister.register(SectionData.PATIENT_MONITORING, NCD.CORONARY_ARTERY_DISEASE, isChecked);
                ((RegisterActivity) Objects.requireNonNull(getActivity())).getActivatedSteps()[SignUpStep.MONITORING_CORONARY_SETTINGS.getStep()] = isChecked;
            }
        });
        chkDiabetes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NCDRegister.register(SectionData.PATIENT_MONITORING, NCD.DIABETES, isChecked);
                ((RegisterActivity) Objects.requireNonNull(getActivity())).getActivatedSteps()[SignUpStep.MONITORING_DIABETES_SETTINGS.getStep()] = isChecked;
            }
        });
        chkObesity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NCDRegister.register(SectionData.PATIENT_MONITORING, NCD.OBESITY, isChecked);
                ((RegisterActivity) Objects.requireNonNull(getActivity())).getActivatedSteps()[SignUpStep.MONITORING_OBESITY_SETTINGS.getStep()] = isChecked;
            }
        });
    }
}
