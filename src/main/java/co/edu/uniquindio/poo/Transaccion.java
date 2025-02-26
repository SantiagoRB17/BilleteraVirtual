package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {
    private UUID id;
    private LocalDateTime fecha;
    private Categoria categoria;
    private BilleteraVirtual origen;
    private BilleteraVirtual destino;
    private float monto;

    public Transaccion(Categoria categoria, BilleteraVirtual origen, BilleteraVirtual destino, float monto) {
        this.id = id; //Se debe crear de forma automatica
        this.fecha = fecha; //Se deben crear automaticamente
        this.categoria = categoria;
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public LocalDateTime getFecha() {

        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {

        this.fecha = fecha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BilleteraVirtual getDestino() {
        return destino;
    }

    public void setDestino(BilleteraVirtual destino) {
        this.destino = destino;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public BilleteraVirtual getOrigen() {
        return origen;
    }

    public void setOrigen(BilleteraVirtual origen) {
        this.origen = origen;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", categoria=" + categoria +
                ", origen=" + origen +
                ", destino=" + destino +
                ", monto=" + monto +
                '}';
    }
}



