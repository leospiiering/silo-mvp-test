package com.example.silos.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.silos.R;

public class SiloCardViewHolder extends RecyclerView.ViewHolder {
    public View card;
    public TextView name;
    public TextView capacity;


    public SiloCardViewHolder(@NonNull View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.silo_card);
        name = itemView.findViewById(R.id.name_silo_card);
        capacity = itemView.findViewById(R.id.capacity_card);
    }
}
