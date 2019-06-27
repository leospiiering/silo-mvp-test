package com.example.silos.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.silos.Models.Grain;
import com.example.silos.R;

import java.util.ArrayList;

public class SpinnerGrainAdapter extends ArrayAdapter<Grain> {
    public SpinnerGrainAdapter(Context context, ArrayList<Grain> grains) {
        super(context, 0, grains);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        return itemView(position, view, parent);
    }


    private View itemView(int position, View view, ViewGroup parent){
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_grain_item, parent, false);
        }

        TextView nameCategory = view.findViewById(R.id.spinner_grain_name);
        Grain item = getItem(position);

        if(item != null){
            nameCategory.setText(item.getName());
        }
        return view;

    }

    @Override
    public View getDropDownView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        return itemView(position, view, parent);
    }
}
