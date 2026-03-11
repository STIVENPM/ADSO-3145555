package com.sena.cafetin.Repository.SegurityRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.cafetin.Entity.Segurity.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

}
