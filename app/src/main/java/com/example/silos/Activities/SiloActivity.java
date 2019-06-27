package com.example.silos.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silos.Adapters.SpinnerGrainAdapter;
import com.example.silos.DAO.GrainDAO;
import com.example.silos.DAO.SiloDAO;
import com.example.silos.Models.Grain;
import com.example.silos.Models.Silo;
import com.example.silos.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SiloActivity extends AppCompatActivity {
    @BindView(R.id.title_form_silo)
    TextView titleSilo;
    @BindView(R.id.name_silo_ad)
    EditText nameSilo;
    @BindView(R.id.capacidade_silo_total)
    EditText capacidadeTotal;
    @BindView(R.id.spinner_grain)
    Spinner spinnerGrain;
    @BindView(R.id.capacity_atual_silo)
    EditText capacidadeAtual;
    private Silo silo;
    private SiloDAO siloDAO;
    private Grain grain;
    private GrainDAO grainDAO;
    private Grain selectedGrain;
    private ArrayList<Grain> grains;
    private SpinnerGrainAdapter spinnerGrainAdapter;
    private long id = -1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silo_form);
        ButterKnife.bind(this);
        this.silo = new Silo();
        this.siloDAO = new SiloDAO(this);
        this.grainDAO = new GrainDAO(this);
        grains = grainDAO.list();

        spinnerGrain = findViewById(R.id.spinner_grain);
        spinnerGrainAdapter = new SpinnerGrainAdapter(this, grains);
        spinnerGrain.setAdapter(spinnerGrainAdapter);
        spinnerGrain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGrain = (Grain) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (getIntent().getExtras() != null) {
            this.id = getIntent().getExtras().getLong("id");
        }

        if (this.id != -1) {
            Silo silo = siloDAO.get(this.id);
            if (silo == null) {
                return;
            }

            this.titleSilo.setText("Editar Silo");
            this.nameSilo.setText(silo.getName());
            this.capacidadeTotal.setText(silo.getCapacityTotal());
            this.capacidadeAtual.setText(silo.getCapacityAtual());
        }
    }


    @OnClick(R.id.cancel_silo)
    public void onCancelSiloClicked() {
        super.onBackPressed();
    }

    @OnClick(R.id.confirm_silo)
    public void onConfirmSiloClicked() {
        String name = nameSilo.getText().toString();
        String capacityTotal = capacidadeTotal.getText().toString();
        String capacityAtual = capacidadeAtual.getText().toString();
        Grain grain = selectedGrain;

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(capacityTotal)) {
            Toast.makeText(this, "Preencha TODOS os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        silo.setName(name);
        silo.setCapacityTotal(capacityTotal);
        silo.setCapacityAtual(capacityAtual);
        silo.setGrainId(grain.getId());
        silo.setGrain(grain);


        if (this.id == -1){
            Toast.makeText(this, "Silo Adicionado!", Toast.LENGTH_SHORT).show();
            siloDAO.create(silo);
        }else {
            Toast.makeText(this, "Silo Editado!", Toast.LENGTH_SHORT).show();
            silo.setId(this.id);
            siloDAO.update(silo);
        }
        super.onBackPressed();
    }
}
