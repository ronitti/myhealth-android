package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.collect.Glycemic;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.database.RegisterDAO;
import myhealth.ufscar.br.myhealth.tasks.CollectDataTask;

public class CollectGlycemicFragment extends CustonFragment {

    private EditText txtGlycemic;
    private EditText txtObservation;

    private Button btnSave;
    RegisterDAO dao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dao = new RegisterDAO(getContext());
        View view = inflater.inflate(R.layout.fragment_collect_glycemic, container, false);


        inicializeComponents(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void inicializeComponents(final View view) {
        txtGlycemic= view.findViewById(R.id.txt_glycemic);
        txtObservation = view.findViewById(R.id.txt_observation);




        initFieldListeners();
    }


    private void initFieldListeners() {

    }


    @Override
    public boolean save() {
        Glycemic c = new Glycemic();
        c.setTimestamp(new Date());
        c.setNcd(NCD.DIABETES);
        c.setGlycemicRate(Integer.parseInt(txtGlycemic.getText().toString()));
        c.setObservation(txtObservation.getText().toString());

        //new CollectDataTask(getActivity()).execute(new Pair<Patient, Register>(SectionData.PATIENT, c));
        //SectionData.PATIENT_REGISTERS.add(c);

        return dao.save(c);
    }
}
