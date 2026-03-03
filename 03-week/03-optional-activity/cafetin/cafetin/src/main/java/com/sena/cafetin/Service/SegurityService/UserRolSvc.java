package com.sena.cafetin.Service.SegurityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.Entity.Segurity.Users_Rol;
import com.sena.cafetin.IService.SegurityIService.UserRolISvc;
import com.sena.cafetin.Repository.SegurityRepository.UserRolRepo;


@Service
public class UserRolSvc implements UserRolISvc {

    @Autowired
    private UserRolRepo userRolRepo;

    @Override
    public List<Users_Rol> FindAll() {
        return userRolRepo.findAll();
    }

    @Override
    public Users_Rol save(Users_Rol usersRol) {
        if (usersRol.getUsers() == null || usersRol.getRol() == null) {
            throw new IllegalArgumentException("Debe asignar un usuario y un rol validos");
        }
        return userRolRepo.save(usersRol);
    }

}
