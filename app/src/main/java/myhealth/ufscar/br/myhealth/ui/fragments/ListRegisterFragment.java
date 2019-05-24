package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.database.RegisterDAO;
import myhealth.ufscar.br.myhealth.ui.adapters.RegisterAdapter;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;


public class ListRegisterFragment extends Fragment {

    private RecyclerView recyclerView;
    RegisterAdapter adapter;
    private List<Pair<Integer, Register>> registerList;
    private RegisterDAO dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_register, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_content);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        populateList();
        adapter = new RegisterAdapter(getActivity(), registerList);
        DividerItemDecoration dvi = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());
        recyclerView.addItemDecoration(dvi);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }


    private void populateList() {
        if (dao == null) {
            dao = new RegisterDAO(getActivity());
        }

        //Getting from server
        /*
        if (SectionData.PATIENT_REGISTERS != null){
            for(Register register: SectionData.PATIENT_REGISTERS)
                dao.save(register);
        }
        SectionData.PATIENT_REGISTERS = new ArrayList<>(dao.listRegisters(SectionData.PATIENT.getId()));


        registerList = new ArrayList<>(SectionData.PATIENT_REGISTERS);
        */
        registerList = dao.listRegisters(SectionData.PATIENT.getId());
    }


    public void updateList() {
        registerList.clear();
        registerList = dao.listRegisters(SectionData.PATIENT.getId());
        //registerList = new ArrayList<>(SectionData.PATIENT_REGISTERS);
        adapter.setList(registerList);
        adapter.notifyDataSetChanged();
    }




}
