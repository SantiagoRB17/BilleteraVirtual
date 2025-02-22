package co.edu.uniquindio.poo;

import java.util.ArrayList;
import java.util.Random;

public class GestionBilletera {
    private final Banco banco;
    private final GestionUsuario gestionUsuarios;
    private ArrayList<Billetera> billeteras;
    private ArrayList<Transaccion>transacciones;

    public GestionBilletera(Banco banco, GestionUsuario gestionUsuario) {
        this.banco = banco;
        this.gestionUsuarios = gestionUsuario;
        this.billeteras = new ArrayList<>();
        this.transacciones = new ArrayList<>();
    }

    public Billetera crearBilletera(String id, String saldo){

    }

    public void agregarBilletera(Billetera billetera){

    }

    public void eliminarBilletera(Billetera billetera){

    }





}
