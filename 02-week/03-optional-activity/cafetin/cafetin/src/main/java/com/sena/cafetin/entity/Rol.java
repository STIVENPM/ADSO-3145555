package com.sena.cafetin.entity;

import jakarta.persistence.*;

@Entity(name = "roles")
public class Rol {

    //  ID del rol
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private int id;

    //  Nombre del rol
    @Column(name = "rol", length = 50, nullable = false, unique = true)
    private String rol;

    //  Constructor vacío (OBLIGATORIO para JPA)
    public Rol() {
        super();
    }

    //  Constructor con parámetros
    public Rol(int id, String rol) {
        super();
        this.id = id;
        this.rol = rol;
    }

    //  Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}