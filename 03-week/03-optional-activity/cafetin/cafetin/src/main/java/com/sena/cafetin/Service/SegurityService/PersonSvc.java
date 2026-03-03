package com.sena.cafetin.Service.SegurityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.Entity.Segurity.Person;
import com.sena.cafetin.IService.SegurityIService.PersonISvc;

@Service
public class PersonSvc implements PersonISvc {

    @Autowired
    private PersonISvc personService;

    @Override
    public List<Person> FindAll() {
        return personService.FindAll();
    }

    @Override
    public Person save(Person person) {
        return personService.save(person);
    }
}
