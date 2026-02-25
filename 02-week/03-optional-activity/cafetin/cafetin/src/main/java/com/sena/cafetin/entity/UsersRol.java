package com.sena.cafetin.entity;

import jakarta.persistence.*;

@Entity(name = "users_rol")
public class UsersRol {

    // ID principal
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_rol")
    private int id;

    // Relación con User
    @ManyToOne
    @JoinColumn(name = "id_users", nullable = false)
    private User user;

    // Relación con Rol
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    // Constructor vacío (obligatorio)
    public UsersRol() {
        super();
    }

    // Constructor con parámetros
    public UsersRol(int id, User user, Rol rol) {
        super();
        this.id = id;
        this.user = user;
        this.rol = rol;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}