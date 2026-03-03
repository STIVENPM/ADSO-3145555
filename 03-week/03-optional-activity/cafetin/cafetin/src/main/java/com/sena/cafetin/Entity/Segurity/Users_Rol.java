package com.sena.cafetin.Entity.Segurity;
import jakarta.persistence.*;
@Entity(name = "users_rol")
public class Users_Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    public Users_Rol() {
    }

    public Users_Rol(Integer id, User users, Rol rol) {
        this.id = id;
        this.users = users;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
