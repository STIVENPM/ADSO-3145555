package com.sena.cafetin.Service.SegurityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.Entity.Segurity.User;
import com.sena.cafetin.IService.SegurityIService.UserISvc;
import com.sena.cafetin.Repository.SegurityRepository.UserRepo;

@Service
public class UserSvc implements UserISvc {

    @Autowired
    private UserRepo userRepo;
    
    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User save(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("El usuario debe tener un nombre válido");
        }
        return userRepo.save(user);
    }




}
