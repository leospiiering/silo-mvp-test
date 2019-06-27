package com.example.silos.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.silos.DAO.GrainDAO;
import com.example.silos.DAO.SiloDAO;
import com.example.silos.Models.Grain;
import com.example.silos.Models.Silo;
import com.example.silos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SiloIdActivity extends AppCompatActivity {
    @BindView(R.id.name_silo_id)
    TextView nameSilo;
    @BindView(R.id.capacidade_total_id)
    TextView capacityTotal;
    @BindView(R.id.grain_silo)
    TextView grainSilo;
    @BindView(R.id.capacity_atual)
    TextView capacityAtual;
    private long id;
    private Silo silo;
    private SiloDAO siloDAO;
    private GrainDAO grainDAO;
    private Grain grain;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silo_id);
        ButterKnife.bind(this);
        siloDAO = new SiloDAO(getBaseContext());
        grainDAO = new GrainDAO(getBaseContext());

        if (getIntent().getExtras() != null) {
            this.id = getIntent().getExtras().getLong("id");
            this.silo = siloDAO.get(this.id);
        }

        this.grain = grainDAO.get(silo.getGrainId());

        this.nameSilo.setText(this.silo.getName());
        this.capacityTotal.setText("Capacidade Total: " + this.silo.getCapacityTotal());
        this.grainSilo.setText("Grão Armazenado: " + this.grain.getName());
        this.capacityAtual.setText("Capacidade Atual: " + this.silo.getCapacityAtual());

    }

    @OnClick(R.id.cancel_id)
    public void onViewClicked() {
        super.onBackPressed();
    }

    @OnClick(R.id.delete_silo)
    public void onCancelIdClicked() {
        siloDAO.remove(siloDAO.get(this.id).getId());
        super.onBackPressed();
    }

    @OnClick(R.id.edit_silo)
    public void onEditSiloClicked() {
        Intent intent = new Intent(getBaseContext(), SiloActivity.class);
        intent.putExtra("id", siloDAO.get(this.id).getId());
        startActivity(intent);
    }


    @OnClick(R.id.refresh_capacity)
    public void onRefreshClicked() {
        AlertDialog.Builder decision = new AlertDialog.Builder(this);
        View decisionView = LayoutInflater.from(getBaseContext()).inflate(R.layout.decision_silo, null);

        ImageButton addCap = decisionView.findViewById(R.id.add_capacity_silo);
        ImageButton cutCap = decisionView.findViewById(R.id.cut_silo);

        decision.setView(decisionView);
        final AlertDialog  dialog = decision.create();
        dialog.show();


    }
}
