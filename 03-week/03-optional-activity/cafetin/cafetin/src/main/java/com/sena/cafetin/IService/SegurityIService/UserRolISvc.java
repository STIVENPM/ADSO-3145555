package com.sena.cafetin.IService.SegurityIService;

import java.util.List;

import com.sena.cafetin.Entity.Segurity.Users_Rol;

public interface UserRolISvc {
    List<Users_Rol> FindAll();
    Users_Rol save(Users_Rol userRol);

}
