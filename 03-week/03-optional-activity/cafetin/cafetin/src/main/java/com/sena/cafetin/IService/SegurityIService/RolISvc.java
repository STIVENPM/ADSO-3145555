package com.sena.cafetin.IService.SegurityIService;

import java.util.List;

import com.sena.cafetin.Entity.Segurity.Rol;

public interface RolISvc {
    List<Rol> FindAll();
    Rol save(Rol rol);

}
