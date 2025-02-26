package co.edu.uniquindio.poo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase Banco
 */
public class Banco {
    private ArrayList<Usuario> usuarios;
    private ArrayList<BilleteraVirtual> billeteras;

    /**
     * Constructor banco
     */
    public Banco() {
        this.usuarios = new ArrayList<>();
        this.billeteras = new ArrayList<>();
    }

    /**
     * Metodo para agregar usuario
     *
     * @param usuario usuario a agregar
     * @throws Exception arroja una excepcion en caso de que ya exista un usuario con el mismo id en la base de datos
     */
    public void agregarUsuario(Usuario usuario) throws Exception {
        Usuario buscarUsuario = obtenerUsuario(usuario.getId());

        if (buscarUsuario != null) {
            throw new Exception("Ya existe un usuario " + usuario.getId() + "con el mismo ID");
        } else {
            usuarios.add(usuario);
        }

    }

    /**
     * Metodo para eliminar usuario
     *
     * @param id id del usuario a eliminar
     * @throws Exception arroja una excepcion en caso de que noe exista el usuario
     */
    public void eliminarUsuario(String id) throws Exception {
        Usuario buscarUsuario = obtenerUsuario(id);

        if (buscarUsuario == null) {
            throw new Exception("No existe el usuario con el id ingresado");
        } else {
            usuarios.remove(buscarUsuario);
        }

    }

    /**
     * Metodo para actualizar un usuario
     *
     * @param usuario usuario con los datos actualizados
     * @throws Exception arroja una excepcion en caso de que los datos no esten actualizados
     */
    public void actualizarUsuario(Usuario usuario) throws Exception {
        Usuario buscarUsuario = obtenerUsuario(usuario.getId());

        if (buscarUsuario != null) {
            buscarUsuario.setNombre(usuario.getNombre());
            buscarUsuario.setApellido(usuario.getApellido());
            buscarUsuario.setCorreo(usuario.getCorreo());
            buscarUsuario.setDireccion(usuario.getDireccion());
            buscarUsuario.setTelefono(usuario.getTelefono());
            buscarUsuario.setId(usuario.getId());
        } else {
            throw new Exception("No existe el usuario con el id ingresado");
        }

    }

    /**
     * Metodo para obtener un usuario
     *
     * @param id id del usuario a buscar
     * @return retorna el usuario con la id ingresada
     */
    public Usuario obtenerUsuario(String id) {
        return usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Metodo para agregar una billetera a la lista de billetera
     *
     * @param billetera billetera a ingresar
     * @throws Exception lanza una expecion en caso de la billetera a ingresar tenga el mismo usuario que otra billetera
     *                   en la lista
     */
    public void agregarBilletera(BilleteraVirtual billetera) throws Exception {
        BilleteraVirtual buscarUsuarioBilletera = billeteras.stream().filter(billeteras -> billeteras.getUsuario().
                equals(billetera.getUsuario())).findFirst().orElse(null);

        if (buscarUsuarioBilletera != null) {
            throw new Exception("Ya existe una billetera con este usuario ");
        } else {
            asignarCodigoBilletera(billetera);
            billeteras.add(billetera);
        }

    }

    /**
     * Metodo para obtener una billetera usando el id del usuario asociado a esa billetera
     *
     * @param id id del usuario al que le pertenece la billetera
     * @return billetera con el id
     */
    public BilleteraVirtual obtenerBilletera(String id) {
        return billeteras.stream().filter(billetera -> billetera.getUsuario().getId().equals(id)).
                findFirst().orElse(null);
    }

    /**
     * Metodo que asigna un codigo unico y que no esté repetido a una billetera
     *
     * @param billetera billetera a la que se le va asignar el codigo
     */
    public void asignarCodigoBilletera(BilleteraVirtual billetera) {

        boolean codigoHallado = false;

        String codigoUnico = "";

        while (!codigoHallado) {
            codigoUnico = generarCodigoUnico();
            codigoHallado = true;

            for (BilleteraVirtual b : billeteras) {
                if (b.getCodigoUnico().equals(codigoUnico)) {
                    codigoHallado = false;
                }
            }
        }
        billetera.setCodigoUnico(codigoUnico);
    }

    /**
     * Metodo para generar un codigo aleatorio de 10 digitos
     *
     * @return cadena que contiene el codigo aleatorio
     */
    public String generarCodigoUnico() {
        String codigoUnico = "";
        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            codigoUnico = codigoUnico + r.nextInt(9);
        }
        return codigoUnico;
    }

    public void desactivarBilletera(String idUsuario, String codigoUnico) {

        for (BilleteraVirtual billetera : billeteras) {
            if (billetera.getUsuario().getId().equals(idUsuario) && billetera.getCodigoUnico().equals(codigoUnico)) {
                billetera.desactivarBilletera();
                return;
            }

        }


    }

    public void realizarTransaccion(String idUsuario, String idBilletera, Categoria categoria, float monto, BilleteraVirtual destinatario) throws Exception {

        for (BilleteraVirtual billetera : billeteras) {
            if (billetera.getUsuario().getId().equals(idUsuario) && billetera.getCodigoUnico().equals(idBilletera)) {
                billetera.realizarTransaccion(categoria, monto, destinatario);
                return;

            }

        }

    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<BilleteraVirtual> getBilleteras() {
        return billeteras;
    }

    public void setBilleteras(ArrayList<BilleteraVirtual> billeteras) {
        this.billeteras = billeteras;
    }

    @Override
    public String toString() {
        return "Banco: " +
                "usuarios: " + usuarios +
                ", billeteras: " + billeteras +
                '.';
    }
}
