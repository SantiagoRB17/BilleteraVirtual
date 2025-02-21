package co.edu.uniquindio.poo;

import java.util.Random;

public class Billetera {
    private Usuario usuario;
    private double saldo;
    private String codigoUnico;

    public Billetera(Usuario usuario, double saldo) {
        this.usuario = usuario;
        this.saldo = saldo;
        this.codigoUnico = generarCodigoUnico();
    }

    public String generarCodigoUnico() {
        String codigoUnico = "";
        for(int i=0; i<10; i++){
            Random r = new Random();
            codigoUnico = codigoUnico + r.nextInt(9);
        }
        return codigoUnico;
    }

    @Override
    public String toString() {
        return "Billetera{" +
                "usuario=" + usuario +
                ", saldo=" + saldo +
                ", codigoUnico='" + codigoUnico + '\'' +
                '}';
    }
}
