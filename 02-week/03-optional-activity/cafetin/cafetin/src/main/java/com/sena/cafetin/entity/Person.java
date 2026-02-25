package com.sena.cafetin.entity;

import jakarta.persistence.*;

@Entity(name = "person")
public class Person {

    // Llave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Integer id;

    // Columna nombre
    @Column(name = "name", length = 50, nullable = false)
    private String nombre;

    // Columna edad
    @Column(name = "age", nullable = false)
    private int edad;

    // Constructor con parámetros
    public Person(Integer id, String nombre, int edad) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    // Constructor vacío (obligatorio para JPA)
    public Person() {
        super();
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}