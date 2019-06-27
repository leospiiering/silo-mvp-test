package com.example.silos.Models;

public class Silo {
    private Long id;
    private Grain grain;
    private Long grainId;
    private String name;
    private String capacityTotal;
    private String capacityAtual;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId(){ return id; }

    public void setId(Long id){ this.id = id;}

    public Grain getGrain(){ return grain; }

    public void setGrain(Grain grain){ this.grain = grain; }

    public Long getGrainId(){ return grainId; }

    public void setGrainId(Long grainId) { this.grainId = grainId; }

    public String getCapacityTotal(){ return capacityTotal;}

    public void setCapacityTotal(String capacityTotal){ this.capacityTotal = capacityTotal; }

    public String getCapacityAtual(){ return capacityAtual;}

    public void setCapacityAtual(String capacityAtual){ this.capacityAtual = capacityAtual; }
}
