package com.example.silos.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.silos.Activities.SiloIdActivity;
import com.example.silos.DAO.SiloDAO;
import com.example.silos.Models.Silo;
import com.example.silos.R;
import com.example.silos.ViewHolders.SiloCardViewHolder;

import java.util.ArrayList;

public class SiloAdapterItens extends RecyclerView.Adapter<SiloCardViewHolder> {
    private ArrayList<Silo> silos;
    private SiloDAO siloDAO;
    private Context context;

    public SiloAdapterItens(Context context, ArrayList<Silo> silos){
        this.context = context;
        this.siloDAO = new SiloDAO(context);
        this.silos = silos;
    }
    @NonNull
    @Override
    public SiloCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_silo, viewGroup, false);
        return new SiloCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiloCardViewHolder siloCardViewHolder, final int i) {
        siloCardViewHolder.name.setText(this.silos.get(i).getName());
        siloCardViewHolder.capacity.setText(this.silos.get(i).getCapacityTotal());

        siloCardViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SiloIdActivity.class);
                intent.putExtra("id", silos.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    public void attItens(ArrayList<Silo> silos){
        this.silos = silos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return silos.size();
    }
}
