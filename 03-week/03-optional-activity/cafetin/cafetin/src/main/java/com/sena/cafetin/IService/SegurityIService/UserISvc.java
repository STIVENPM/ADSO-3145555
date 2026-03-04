package com.sena.cafetin.IService.SegurityIService;

import java.util.List;
import com.sena.cafetin.Dto.SegurityDto.UserDto;

public interface UserISvc {
    List<UserDto> findAll();
    UserDto FindById(Integer id);
    UserDto save(UserDto user);
    UserDto update(UserDto user, Integer id);
    void delete(Integer id);


}
