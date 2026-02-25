package com.sena.cafetin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.entity.Person;
import com.sena.cafetin.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepo;

    // Crear
    public Person crear(Person person) {
        return personRepo.save(person);
    }

    // Listar
    public List<Person> listar() {
        return personRepo.findAll();
    }

    // Buscar por id
    public Person buscarPorId(int id) {
        return personRepo.findById(id).orElse(null);
    }

    // Actualizar
    public Person actualizar(int id, Person person) {
        Person p = personRepo.findById(id).orElse(null);
        if (p == null) {
            return null;
        }

        p.setNombre(person.getNombre());
        p.setEdad(person.getEdad());

        return personRepo.save(p);
    }

    // Eliminar
    public void eliminar(int id) {
        personRepo.deleteById(id);
    }
}