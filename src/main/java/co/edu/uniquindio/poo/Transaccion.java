package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {
    private UUID id;
    private LocalDateTime fecha;
    private Categoria categoria;
    private GestionBilletera origen;
    private GestionBilletera destino;
    private float monto;

    public Transaccion(UUID id, LocalDateTime fecha, Categoria categoria, GestionBilletera origen, GestionBilletera destino, float monto) {
        this.id = id;
        this.fecha = fecha;
        this.categoria = categoria;
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
    }
}



