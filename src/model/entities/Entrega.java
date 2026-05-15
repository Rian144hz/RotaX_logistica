package model.entities;

import java.util.Objects;

public class Entrega {
    private StatusEntrega status;
    private String placa;
    private Double km_rodado;

    public Entrega(StatusEntrega status, String placa, Double km_rodado) {
        this.status = status;
        this.placa = placa;
        this.km_rodado = km_rodado;
    }

    public model.entities.StatusEntrega getStatus() {
        return status;
    }

    public String getPlaca() {
        return placa;
    }

    public Double getKm_rodado() {
        return km_rodado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entrega entrega = (Entrega) o;
        return Objects.equals(placa, entrega.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(placa);
    }
}


