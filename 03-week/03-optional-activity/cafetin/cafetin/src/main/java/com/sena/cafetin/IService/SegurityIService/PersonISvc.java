package com.sena.cafetin.IService.SegurityIService;

import java.util.List;

import com.sena.cafetin.Dto.SegurityDto.PersonDto;


public interface PersonISvc {

    List<PersonDto> FindAll();
    PersonDto FindById(Integer id);
    PersonDto save(PersonDto personDto);
    PersonDto update(PersonDto personDto, Integer id);
    void delete(Integer id);



}


