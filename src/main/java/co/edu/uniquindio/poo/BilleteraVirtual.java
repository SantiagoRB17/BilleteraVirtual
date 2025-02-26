package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BilleteraVirtual {
    private Usuario usuario;
    private float saldo;
    private final String codigoUnico;
    private ArrayList<Transaccion> transacciones;

    public BilleteraVirtual(Usuario usuario, float saldo) {
        this.usuario = usuario;
        this.saldo = saldo;
        this.codigoUnico = "";
    }


    public void realizarTransaccion(Categoria categoria, float monto, BilleteraVirtual destinatario) throws Exception {
        validarMontoPositivo(monto);
        validarCategoria(categoria);
        validarFondosSuficientes(monto);
        validarDestinatario(destinatario);

        Transaccion transaccion = crearTransaccion(categoria, monto, this, destinatario);
        actualizarSaldo(monto);
        destinatario.recibirFondos(monto);
        agregarTransaccion(transaccion);
        destinatario.agregarTransaccion(transaccion);
    }


    private void validarMontoPositivo(float monto) throws Exception {
        if (monto <= 0) {
            throw new Exception("El monto debe ser positivo.");
        }
    }


    private void validarCategoria(Categoria categoria) throws Exception {
        if (categoria == null) {
            throw new Exception("La categoría no puede ser nula.");
        }
    }


    private void validarFondosSuficientes(float monto) throws Exception {
        if (monto > saldo) {
            throw new Exception("Fondos insuficientes.");
        }
    }


    private void validarDestinatario(BilleteraVirtual destinatario) throws Exception {
        if (destinatario == null) {
            throw new Exception("La billetera destinatario no puede ser nula.");
        }
        if (destinatario == this) {
            throw new Exception("La billetera de origen y destino no pueden ser la misma.");
        }
    }

    private Transaccion crearTransaccion(Categoria categoria, float monto, BilleteraVirtual origen, BilleteraVirtual destino) {
        return new Transaccion(categoria, origen, destino, monto);
    }


    private void actualizarSaldo(float monto) {
        saldo -= monto;
    }

    private void consultarSaldo() {
        System.out.println("Su saldo es de: " + saldo);
    }

    private void consultarTransacciones() {
        System.out.println("Consultando transacciones...");
        for (Transaccion transaccion : transacciones) {
            System.out.println(transaccion);
        }
    }


    private void recibirFondos(float monto) {
        saldo += monto;
    }

    private void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
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


    public String getCodigoUnico() {
        return codigoUnico;
    }


    @Override
    public String toString() {
        return "Billetera{" + "usuario=" + usuario + ", saldo=" + saldo + '}';
    }
}
