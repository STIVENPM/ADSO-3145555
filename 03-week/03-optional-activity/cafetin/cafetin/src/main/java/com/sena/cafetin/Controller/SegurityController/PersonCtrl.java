package com.sena.cafetin.Controller.SegurityController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.cafetin.Entity.Segurity.Person;
import com.sena.cafetin.IService.SegurityIService.PersonISvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/person")
public class PersonCtrl {

    @Autowired
    private PersonISvc personService;

    @GetMapping
    public List<Person> FindAll() {
        return personService.FindAll();
    }

    @PostMapping
    public Person save(@RequestParam String name) {
        Person person = new Person();
        person.setName(name);
        return personService.save(person);
    }
}
