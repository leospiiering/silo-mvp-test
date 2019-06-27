package com.example.silos.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.silos.R;

public class GrainCardViewHolder extends RecyclerView.ViewHolder {
    public View card;
    public TextView name;
    public ImageButton delete;


    public GrainCardViewHolder(@NonNull View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.grain_card);
        name = itemView.findViewById(R.id.name_grain_card);
        delete = itemView.findViewById(R.id.delete_grain);

    }
}
