package model.entities;
import java.util.Objects;

public class Motorista {
    private Integer id;
    private String nome;
    private String placa;
    private Double km_rodado;

    public Motorista(Integer id, String nome, String placa, Double km_rodado) {
        this.id = id;
        this.nome = nome;
        this.placa = placa;
        this.km_rodado = km_rodado;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
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
        Motorista motorista = (Motorista) o;
        return Objects.equals(id, motorista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
