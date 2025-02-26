package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BilleteraVirtual {
    private Usuario usuario;
    private boolean estado;
    private float saldo;
    private final String codigoUnico;
    private ArrayList<Transaccion> transacciones;
    private static final float COSTO = 200;



    /**
     * Constructor para crear una billetera virtual con un usuario y saldo inicial.
     * @param usuario Usuario propietario de la billetera.
     * @param saldo Saldo inicial de la billetera.
     */
    public BilleteraVirtual(Usuario usuario, float saldo) {
        this.usuario = usuario;
        this.estado = true;
        this.saldo = saldo;
        this.codigoUnico = ""; // Generar un código único
        this.transacciones = new ArrayList<>(); // Inicializar la lista de transacciones
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


    public void recargarSaldo(float monto, Categoria categoria) throws Exception {
        // Validar si el monto es positivo
        if (monto <= 0) {
            // Lanzar una excepción si el monto no es válido
            throw new Exception("El monto a recargar debe ser positivo.");
        }

        // Actualizar el saldo
        this.saldo += monto;

        BilleteraVirtual origen = this; // La billetera origen es la actual
        BilleteraVirtual destino = this; // La billetera destino es la misma
        Transaccion recarga = new Transaccion(categoria, origen, destino, monto);

        // Añadir la transacción a la lista de transacciones
        transacciones.add(recarga);

        // Imprimir el saldo actualizado
        System.out.println("Recarga exitosa. Nuevo saldo: " + this.saldo);
    }


    /**
     * Calcula el porcentaje de gastos e ingresos con respecto al total de transacciones.
     * @return Arreglo de floats donde [0] es porcentaje de gastos y [1] porcentaje de ingresos.
     */
    public float[] porcentajeGastosIngresos(int mes, int año) {
        float gastos = 0f;
        float ingresos = 0f;

        // Usamos un Map para almacenar los gastos por categoría
        Map<String, Float> gastosPorCategoria = new HashMap<>();

        // Iteramos sobre todas las transacciones registradas en esta billetera
        for (Transaccion transaccion : transacciones) {
            // Obtenemos el mes y año de la transacción
            LocalDateTime fechaTransaccion = transaccion.getFecha();
            int mesTransaccion = fechaTransaccion.getMonthValue();  // Mes de la transacción (1-12)
            int añoTransaccion = fechaTransaccion.getYear();        // Año de la transacción

            // Verificamos si la transacción pertenece al mes y año proporcionado
            if (mesTransaccion == mes && añoTransaccion == año) {
                // Si el destino es esta billetera, es un ingreso
                if (transaccion.getDestino() == this) {
                    ingresos += transaccion.getMonto();
                }
                // Si el origen es esta billetera, es un gasto
                else if (transaccion.getOrigen() == this) {
                    gastos += transaccion.getMonto();

                    // Obtener la categoría de la transacción y agregarla al Map
                    String categoria = transaccion.getCategoria().toString();  // Usamos el método toString() para obtener el nombre de la categoría
                    // Acumulamos el gasto en la categoría correspondiente
                    gastosPorCategoria.put(categoria, gastosPorCategoria.getOrDefault(categoria, 0f) + transaccion.getMonto());
                }
            }
        }




        // Calculamos el total de ingresos y gastos
        float total = gastos + ingresos;
        if (total <= 0) {
            // Si no hay ingresos ni gastos, devolvemos 0 para ambos
            return new float[]{0f, 0f};
        }

        // Calculamos los porcentajes de los gastos y los ingresos
        float porcentajeGastos = (gastos / total) * 100;
        float porcentajeIngresos = (ingresos / total) * 100;

        // Devolvemos los porcentajes de gastos e ingresos
        return new float[]{porcentajeGastos, porcentajeIngresos};
    }




    public ArrayList<Transaccion> consultarTransaccionesPorPeriodo(LocalDateTime inicio, LocalDateTime fin) {
        ArrayList<Transaccion> transaccionesEnPeriodo = new ArrayList<>();

        for (Transaccion transaccion : transacciones) {
            LocalDateTime fechaTransaccion = transaccion.getFecha();

            // Verificamos si la fecha de la transacción está dentro del periodo dado
            if ((fechaTransaccion.isEqual(inicio) || fechaTransaccion.isAfter(inicio)) &&
                    (fechaTransaccion.isEqual(fin) || fechaTransaccion.isBefore(fin))) {
                transaccionesEnPeriodo.add(transaccion);
            }
        }

        return transaccionesEnPeriodo;
    }



    /**
     * Valida que el monto de la transacción sea positivo.
     * @param monto Monto a validar.
     * @throws Exception Si el monto es menor o igual a cero.
     */
    private static void validarMontoPositivo(float monto) throws Exception {
        if (monto <= 0) {
            throw new Exception("El monto debe ser positivo.");
        }
    }

    /**
     * Valida que la categoría de la transacción no sea nula.
     * @param categoria Categoría a validar.
     * @throws Exception Si la categoría es nula.
     */
    private static void validarCategoria(Categoria categoria) throws Exception {
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
        float total = monto + COSTO;
        if (total > saldo) {
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
    private void actualizarSaldo(float monto) throws Exception {
        if (monto < 0 && Math.abs(monto) > saldo) {
            throw new Exception("No se puede restar más saldo del disponible.");
        }
        saldo -= monto + COSTO;
    }

    /**
     * Recibe fondos y aumenta el saldo de la billetera.
     * @param monto Monto a agregar al saldo.
     */
    private void recibirFondos(float monto) throws Exception {
        if (monto <= 0) {
            throw new Exception("El monto a recibir debe ser positivo.");
        }
        saldo += monto;
    }

    /**
     * Agrega una transacción a la lista de transacciones de la billetera.
     * @param transaccion Transacción a agregar.
     */
    private void agregarTransaccion(Transaccion transaccion) {
        if (transaccion != null) {
            transacciones.add(transaccion);
        }
    }

    public void desactivarBilletera() {
        estado = false;
        System.out.println("La billetera ha sido desactivada");
    }

    public void consultarSaldo(){
        System.out.println("El saldo de la billetera con codigo" + codigoUnico + " y propietario " + usuario.getNombre() + " es: " + saldo);
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
        // No se permite cambiar el código único
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    @Override
    public String toString() {
        return "BilleteraVirtual{" +
                "usuario=" + usuario +
                ", saldo=" + saldo +
                ", codigoUnico='" + codigoUnico + '\'' +
                ", transacciones=" + transacciones +
                '}';
    }
}

