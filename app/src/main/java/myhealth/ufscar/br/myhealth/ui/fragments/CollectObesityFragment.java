package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.collect.Obesity;
import myhealth.ufscar.br.myhealth.database.RegisterDAO;

public class CollectObesityFragment extends CustomFragment {

    private EditText txtWeight;
    private EditText txtBodyFat;
    private EditText txtObservation;

    private Button btnSave;
    RegisterDAO dao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dao = new RegisterDAO(getContext());
        View view = inflater.inflate(R.layout.fragment_collect_obesity, container, false);


        inicializeComponents(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void inicializeComponents(final View view) {
        txtWeight= view.findViewById(R.id.txt_weight);
        txtBodyFat = view.findViewById(R.id.txt_bodyfat);
        txtObservation = view.findViewById(R.id.txt_observation);

        initFieldListeners();
    }


    private void initFieldListeners() {

    }


    @Override
    public boolean save() {
        Obesity c = new Obesity();
        c.setId_patient(SectionData.PATIENT.getId());
        c.setTimestamp(new Date());
        c.setNcd(NCD.OBESITY);
        c.setWeight(Float.parseFloat(txtWeight.getText().toString()));
        c.setBodyfat(Float.parseFloat(txtBodyFat.getText().toString()));
        c.setObservation(txtObservation.getText().toString());

        SectionData.PATIENT_REGISTERS.add(c);

        return dao.save(c);
    }
}
