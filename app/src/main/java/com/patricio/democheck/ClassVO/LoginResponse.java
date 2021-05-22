package com.patricio.democheck.ClassVO;

import com.patricio.democheck.Model.User;

import java.util.ArrayList;

public class LoginResponse {
    Boolean estado;
    ArrayList<User> usuario;

    public LoginResponse() {
    }

    public LoginResponse(Boolean estado, ArrayList<User> usuario) {
        this.estado = estado;
        this.usuario = usuario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public ArrayList<User> getUsuario() {
        return usuario;
    }

    public void setUsuario(ArrayList<User> usuario) {
        this.usuario = usuario;
    }
}
