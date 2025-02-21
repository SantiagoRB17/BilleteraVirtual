package co.edu.uniquindio.poo;

import java.util.ArrayList;

public class GestionBilletera {
    private ArrayList<Usuario> usuarios;

    public GestionUsuario() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) throws Exception{
        Usuario buscarUsuario = obtener(usuario.getId());

        if(buscarUsuario!=null){
            throw new Exception("Ya existe un usuario " + usuario.getId() + "con el mismo ID");
        }else{
            usuarios.add(usuario);
        }

    }

    public void eliminarUsuario(String id) throws Exception{
        Usuario buscarUsuario = obtener(id);

        if(buscarUsuario==null){
            throw new Exception("No existe el usuario con el id ingresado");
        }else{
            usuarios.remove(buscarUsuario);
        }

    }

    public void actualizarUsuario(Usuario usuario) throws Exception{
        Usuario buscarUsuario = obtener(usuario.getId());

        if(buscarUsuario!=null){
            buscarUsuario.setNombre(buscarUsuario.getNombre());
            buscarUsuario.setApellido(buscarUsuario.getApellido());
            buscarUsuario.setCorreo(buscarUsuario.getCorreo());
            buscarUsuario.setDireccion(buscarUsuario.getDireccion());
            buscarUsuario.setTelefono(buscarUsuario.getTelefono());
            buscarUsuario.setId(buscarUsuario.getId());
        }else{
            throw new Exception("No existe el usuario con el id ingresado");
        }

    }

    public Usuario obtener(String id){
        return usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst().orElse(null);
    }
}
