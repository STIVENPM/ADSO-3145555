package com.sena.cafetin.IService.SegurityIService;

import java.util.List;

import com.sena.cafetin.Entity.Segurity.Person;

public interface PersonISvc {
    List<Person> FindAll();
    Person save(Person person);

}
