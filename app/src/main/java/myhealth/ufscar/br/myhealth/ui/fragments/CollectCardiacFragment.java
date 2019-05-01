package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import myhealth.ufscar.br.myhealth.R;

public class CollectCardiacFragment extends Fragment {

    private EditText txtSystolic;
    private EditText txtDiastolic;
    private EditText txtHeartBeats;
    private EditText txtWeight;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect_cardiac, container, false);
        inicializeComponents(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void inicializeComponents(View view) {
        txtSystolic= view.findViewById(R.id.txt_systolic);
        txtDiastolic = view.findViewById(R.id.txt_diastolic);
        txtHeartBeats = view.findViewById(R.id.txt_heart_beats);
        txtWeight = view.findViewById(R.id.txt_weight);
        initFieldListeners();
    }


    private void initFieldListeners() {

    }



}
