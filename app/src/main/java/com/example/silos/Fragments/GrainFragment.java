package com.example.silos.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.silos.Activities.GrainActivity;
import com.example.silos.Adapters.GrainAdapterItens;
import com.example.silos.DAO.GrainDAO;
import com.example.silos.Models.Grain;
import com.example.silos.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GrainFragment extends Fragment {
    private GrainAdapterItens grainAdapterItens;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.fragment_grain, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.grainAdapterItens = new GrainAdapterItens(getContext(), new ArrayList<Grain>());
        RecicleViewGrain();
    }


    public static GrainFragment newInstance() {
        return new GrainFragment();
    }

    @OnClick(R.id.add_grain)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), GrainActivity.class));
    }

    public void RecicleViewGrain(){
        RecyclerView recycleViewGrains = getActivity().findViewById(R.id.recycleview_grain);
        LinearLayoutManager linearLayoutManagerGrains = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recycleViewGrains.setLayoutManager(linearLayoutManagerGrains);
        recycleViewGrains.setAdapter(grainAdapterItens);
    }

    public void getAllGrains(){
        GrainDAO grainDAO = new GrainDAO(getContext());
        this.grainAdapterItens.attItens(grainDAO.list());
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllGrains();
    }

}
