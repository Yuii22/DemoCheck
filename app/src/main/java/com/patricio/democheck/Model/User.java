package com.patricio.democheck.Model;

public class User {
    String id;
    String Nombre;
    String Status;

    public User() {
    }

    public User(String id, String nombre, String status) {
        this.id = id;
        Nombre = nombre;
        Status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
