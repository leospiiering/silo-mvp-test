package com.example.silos.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.silos.DAO.GrainDAO;
import com.example.silos.Models.Grain;
import com.example.silos.R;
import com.example.silos.ViewHolders.GrainCardViewHolder;

import java.util.ArrayList;

public class GrainAdapterItens extends RecyclerView.Adapter<GrainCardViewHolder> {
    private ArrayList<Grain> grains;
    private GrainDAO grainDAO;
    private Context context;

    public GrainAdapterItens(Context context, ArrayList<Grain> grains){
        this.context = context;
        this.grainDAO = new GrainDAO(context);
        this.grains = grains;
    }

    @NonNull
    @Override
    public GrainCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grain, viewGroup, false);
        return new GrainCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GrainCardViewHolder grainCardViewHolder, final int i) {
        grainCardViewHolder.name.setText(this.grains.get(i).getName());
        Log.e("teste", String.valueOf(grainCardViewHolder.name));

        grainCardViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grainDAO.remove(grains.get(i).getId());
                attItens(grainDAO.list());
            }
        });
    }

    public void attItens(ArrayList<Grain> grains){
        this.grains = grains;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        Log.e("Teste", String.valueOf(grains.size()));
        return grains.size();
    }
}
