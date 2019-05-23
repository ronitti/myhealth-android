package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.tasks.AccessCodeTask;

public class AccessCodeFragment extends Fragment implements AccessCodeTask.OnTaskInteraction, SwipeRefreshLayout.OnRefreshListener {

    private TextView txtAccessCode;
    private SwipeRefreshLayout swipeRefreshLayout;

    public AccessCodeFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_access_code, container, false);
        init(view);
        return view;
    }



    private void init(View view){
        swipeRefreshLayout = view.findViewById(R.id.layout_swipe_code);
        swipeRefreshLayout.setOnRefreshListener(this);
        txtAccessCode = view.findViewById(R.id.txt_access_code);
        getAccessCode();
    }

    @Override
    public void onTaskFinsh(String result) {
        txtAccessCode.setText(result);
    }

    @Override
    public void onRefresh() {
        getAccessCode();
    }

    private void getAccessCode(){
        new AccessCodeTask(this).execute(SectionData.PATIENT.getSusNumber());
        swipeRefreshLayout.setRefreshing(false);
    }
}
