package com.sena.cafetin.entity;

import jakarta.persistence.*;

@Entity(name = "users")
public class User {

    //  ID principal
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;

    // Email
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    //  Password
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    //  Username
    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;

    //  Relación con Person
    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false)
    private Person person;

    //  Constructor vacío (OBLIGATORIO JPA)
    public User() {
        super();
    }

    //  Constructor con parámetros
    public User(int id, String email, String password, String userName, Person person) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.person = person;
    }

    //  Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}