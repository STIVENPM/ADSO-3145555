package com.sena.cafetin.Service.SegurityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.cafetin.Dto.SegurityDto.PersonDto;
import com.sena.cafetin.Entity.Segurity.Person;
import com.sena.cafetin.IService.SegurityIService.PersonISvc;
import com.sena.cafetin.Repository.SegurityRepository.PersonRepo;

public class PersonSvc implements PersonISvc {

    @Autowired
    private PersonRepo personRepo;

    @Override
    public List<PersonDto> FindAll() {
        return personRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public PersonDto FindById(Integer id) {
        Person person = personRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        return convertToDto(person);
    }

    @Override
    public PersonDto save(PersonDto personDto) {
        Person person = convertToEntity(personDto);
        Person saved = personRepo.save(person);
        return convertToDto(saved);
    }

    @Override
    public PersonDto update(PersonDto personDto, Integer id) {

        Person existing = personRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        existing.setName(personDto.getName());
        existing.setAge(personDto.getAge());

        Person updated = personRepo.save(existing);

        return convertToDto(updated);
    }

    @Override
    public void delete(Integer id) {
        personRepo.deleteById(id);
    }

    // ==========================
    // CONVERSION METHODS
    // ==========================

    private PersonDto convertToDto(Person person) {
        return new PersonDto(
                person.getId(),
                person.getName(),
                person.getAge()
        );
    }

    private Person convertToEntity(PersonDto dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setName(dto.getName());
        person.setAge(dto.getAge());
        return person;
    }

}