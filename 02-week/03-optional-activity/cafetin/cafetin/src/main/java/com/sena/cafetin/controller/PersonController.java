package com.sena.cafetin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.cafetin.entity.Person;
import com.sena.cafetin.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    // Crear
    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.crear(person);
    }

    // Listar
    @GetMapping
    public List<Person> getAll() {
        return personService.listar();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public Person getById(@PathVariable int id) {
        return personService.buscarPorId(id);
    }

    // Actualizar
    @PutMapping("/{id}")
    public Person update(@PathVariable int id, @RequestBody Person person) {
        return personService.actualizar(id, person);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        personService.eliminar(id);
        return "Person eliminada";
    }
}