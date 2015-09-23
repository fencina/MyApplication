package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class UsuariosWrapper implements Serializable {

    private ArrayList<Usuario> usuarios;

    public UsuariosWrapper(ArrayList<Usuario> usuarios){
        this.usuarios = usuarios;
    }

    public ArrayList<Usuario> getUsuarios(){
        return this.usuarios;
    }

}
