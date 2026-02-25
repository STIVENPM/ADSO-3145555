package com.sena.cafetin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.cafetin.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>  {

}
