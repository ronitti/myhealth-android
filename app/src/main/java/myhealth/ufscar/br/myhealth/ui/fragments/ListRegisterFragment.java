package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;
import java.util.Random;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.data.collect.Cardiac;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.database.RegisterDAO;
import myhealth.ufscar.br.myhealth.ui.adapters.RegisterAdapter;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;


public class ListRegisterFragment extends Fragment {

    private RecyclerView recyclerView;
    RegisterAdapter adapter;
    private List<Register> registerList;
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
        registerList = dao.listRegisters();

    }


    public void updateList() {
        registerList.clear();
        registerList = dao.listRegisters();
        adapter.setList(registerList);
        adapter.notifyDataSetChanged();
    }




}
