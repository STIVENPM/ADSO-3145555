package com.sena.cafetin.IService.SegurityIService;

import java.util.List;

import com.sena.cafetin.Dto.SegurityDto.RolDto;
public interface RolISvc {
    List<RolDto> FindAll();
    RolDto FindById(Integer id);
    RolDto save(RolDto rol);
    RolDto update(RolDto rol, Integer id);
    void delete(Integer id);

}
