package co.edu.uniquindio.poo;

import java.util.ArrayList;
import java.util.Random;

public class Billetera {
    private Usuario usuario;
    private float saldo;
    private final float Costo =200;

    public Billetera(Usuario usuario, float saldo) {
        this.usuario = usuario;
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getCosto() {
        return Costo;
    }

    @Override
    public String toString() {
        return "Billetera{" +
                "usuario=" + usuario +
                ", saldo=" + saldo +
                ", Costo=" + Costo +
                '}';
    }
}
