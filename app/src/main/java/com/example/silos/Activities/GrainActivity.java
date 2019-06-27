package com.example.silos.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.silos.DAO.GrainDAO;
import com.example.silos.Models.Grain;
import com.example.silos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GrainActivity extends AppCompatActivity {
    @BindView(R.id.name_grain_card)
    EditText nameGrain;
    private GrainDAO grainDAO;
    private Grain grain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grain_form);
        ButterKnife.bind(this);

        this.grainDAO = new GrainDAO(this);
        this.grain = new Grain();
    }

    @OnClick(R.id.save_grain)
    public void onSaveGrainClicked() {
        String name = nameGrain.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Digite um Nome!", Toast.LENGTH_SHORT).show();
            return;
        }

        grain.setName(name);
        grainDAO.create(grain);
        Toast.makeText(this, "Grão Adicionado!", Toast.LENGTH_SHORT).show();
        super.onBackPressed();


    }

    @OnClick(R.id.cancel_grain)
    public void onCancelGrainClicked() {
        super.onBackPressed();
    }
}
