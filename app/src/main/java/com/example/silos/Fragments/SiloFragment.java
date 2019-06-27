package com.example.silos.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.silos.Activities.SiloActivity;
import com.example.silos.Adapters.SiloAdapterItens;
import com.example.silos.DAO.SiloDAO;
import com.example.silos.Models.Silo;
import com.example.silos.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class SiloFragment extends Fragment {
    private SiloAdapterItens siloAdapterItens;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.fragment_silo, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    public static SiloFragment newInstance() {
        return new SiloFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.siloAdapterItens = new SiloAdapterItens(getContext(), new ArrayList<Silo>());
        RecicleViewSilos();
    }

    public void RecicleViewSilos() {
        RecyclerView recycleViewSilos = getActivity().findViewById(R.id.recycleview_silos);
        LinearLayoutManager linearLayoutManagerRecipes = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleViewSilos.setLayoutManager(linearLayoutManagerRecipes);
        recycleViewSilos.setAdapter(siloAdapterItens);
    }


    @OnClick(R.id.add_silo)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), SiloActivity.class));
    }

    public void getAllSilos(){
        SiloDAO siloDAO = new SiloDAO(getContext());
        this.siloAdapterItens.attItens(siloDAO.list());
    }

    public void onResume(){
        super.onResume();
        getAllSilos();
    }
}
