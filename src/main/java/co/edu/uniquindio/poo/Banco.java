package co.edu.uniquindio.poo;

import java.util.ArrayList;

public class Banco {
    public ArrayList<Usuario> usuarios;
    public ArrayList<Billetera> billeteras ;

    public Banco() {
        this.usuarios = new ArrayList<>();
        this.billeteras = new ArrayList<>();

    }


    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Billetera> getBilleteras() {
        return billeteras;
    }

    public void setBilleteras(ArrayList<Billetera> billeteras) {
        this.billeteras = billeteras;
    }
}
