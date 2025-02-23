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

    public Transaccion(UUID id, LocalDateTime fecha, Categoria categoria, BilleteraVirtual origen, BilleteraVirtual destino, float monto) {
        this.id = id;
        this.fecha = fecha;
        this.categoria = categoria;
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
    }
}



