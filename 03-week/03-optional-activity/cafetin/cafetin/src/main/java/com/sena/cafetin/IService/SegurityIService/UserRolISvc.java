package com.sena.cafetin.IService.SegurityIService;

import java.util.List;
import com.sena.cafetin.Dto.SegurityDto.UserRolDto;


public interface UserRolISvc {
    List<UserRolDto> FindAll();
    UserRolDto FindById(Integer id);
    UserRolDto save(UserRolDto userRol);
    UserRolDto update(UserRolDto userRol, Integer id);
    void delete(Integer id);


}
