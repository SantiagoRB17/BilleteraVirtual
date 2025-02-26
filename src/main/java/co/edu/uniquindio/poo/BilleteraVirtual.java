package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BilleteraVirtual {
    private Usuario usuario;
    private float saldo;
    private final String codigoUnico;
    private ArrayList<Transaccion> transacciones;

    /**
     * Constructor para crear una billetera virtual con un usuario y saldo inicial.
     * @param usuario Usuario propietario de la billetera.
     * @param saldo Saldo inicial de la billetera.
     */
    public BilleteraVirtual(Usuario usuario, float saldo) {
        this.usuario = usuario;
        this.saldo = saldo;
        this.codigoUnico = "";
    }

    /**
     * Realiza una transacción entre la billetera actual (origen) y una billetera destinatario.
     * @param categoria Categoría de la transacción.
     * @param monto Monto de la transacción.
     * @param destinatario Billetera que recibirá los fondos.
     * @throws Exception Si el monto no es válido, la categoría es nula, no hay fondos suficientes o el destinatario es inválido.
     */
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

    /**
     * Calcula el porcentaje de gastos e ingresos con respecto al total de transacciones.
     * @return Arreglo de floats donde [0] es porcentaje de gastos y [1] porcentaje de ingresos.
     */
    public float[] porcentajeGastosIngresos() {
        float gastos = 0f;
        float ingresos = 0f;

        for (Transaccion transaccion : transacciones) {
            if (transaccion.getDestino() == this) {
                ingresos += transaccion.getMonto();
            } else if (transaccion.getOrigen() == this) {
                gastos += transaccion.getMonto();
            }
        }

        float total = gastos + ingresos;
        if (total <= 0) {
            return new float[]{0f, 0f};
        }

        float porcentajeGastos = (gastos / total) * 100;
        float porcentajeIngresos = (ingresos / total) * 100;

        return new float[]{porcentajeGastos, porcentajeIngresos};
    }

    /**
     * Valida que el monto de la transacción sea positivo.
     * @param monto Monto a validar.
     * @throws Exception Si el monto es menor o igual a cero.
     */
    private void validarMontoPositivo(float monto) throws Exception {
        if (monto <= 0) {
            throw new Exception("El monto debe ser positivo.");
        }
    }

    /**
     * Valida que la categoría de la transacción no sea nula.
     * @param categoria Categoría a validar.
     * @throws Exception Si la categoría es nula.
     */
    private void validarCategoria(Categoria categoria) throws Exception {
        if (categoria == null) {
            throw new Exception("La categoría no puede ser nula.");
        }
    }

    /**
     * Verifica si hay fondos suficientes para realizar la transacción.
     * @param monto Monto a verificar.
     * @throws Exception Si el monto excede el saldo disponible.
     */
    private void validarFondosSuficientes(float monto) throws Exception {
        if (monto > saldo) {
            throw new Exception("Fondos insuficientes.");
        }
    }

    /**
     * Valida que la billetera destinatario sea válida y distinta a la de origen.
     * @param destinatario Billetera destinatario a validar.
     * @throws Exception Si el destinatario es nulo o es la misma billetera de origen.
     */
    private void validarDestinatario(BilleteraVirtual destinatario) throws Exception {
        if (destinatario == null) {
            throw new Exception("La billetera destinatario no puede ser nula.");
        }
        if (destinatario == this) {
            throw new Exception("La billetera de origen y destino no pueden ser la misma.");
        }
    }

    /**
     * Crea una transacción con la información proporcionada.
     * @param categoria Categoría de la transacción.
     * @param monto Monto de la transacción.
     * @param origen Billetera de origen.
     * @param destino Billetera de destino.
     * @return Objeto Transaccion creado.
     */
    private Transaccion crearTransaccion(Categoria categoria, float monto, BilleteraVirtual origen, BilleteraVirtual destino) {
        return new Transaccion(categoria, origen, destino, monto);
    }

    /**
     * Resta el monto de la transacción del saldo actual.
     * @param monto Monto a restar.
     */
    private void actualizarSaldo(float monto) {
        saldo -= monto;
    }

    /**
     * Consulta el saldo disponible en la billetera y lo imprime en consola.
     */
    private void consultarSaldo() {
        System.out.println("Su saldo es de: " + saldo);
    }

    /**
     * Muestra en consola todas las transacciones registradas en la billetera.
     */
    private void consultarTransacciones() {
        System.out.println("Consultando transacciones...");
        for (Transaccion transaccion : transacciones) {
            System.out.println(transaccion);
        }
    }

    /**
     * Recibe fondos y aumenta el saldo de la billetera.
     * @param monto Monto a agregar al saldo.
     */
    private void recibirFondos(float monto) {
        saldo += monto;
    }

    /**
     * Agrega una transacción a la lista de transacciones de la billetera.
     * @param transaccion Transacción a agregar.
     */
    private void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    // Getters y Setters

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


    public void setCodigoUnico(String codigoUnico) {
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    @Override
    public String toString() {
        return "BilleteraVirtual " +
                "usuario: " + usuario +
                ", saldo: " + saldo +
                ", codigoUnico: '" + codigoUnico + '\'' +
                ", transacciones: " + transacciones +
                '.';
    }

}
