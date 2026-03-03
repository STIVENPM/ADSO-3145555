package com.sena.cafetin.IService.SegurityIService;

import java.util.List;

import com.sena.cafetin.Entity.Segurity.User;

public interface UserISvc {
    List<User> findAll();
    User save(User user);

}
