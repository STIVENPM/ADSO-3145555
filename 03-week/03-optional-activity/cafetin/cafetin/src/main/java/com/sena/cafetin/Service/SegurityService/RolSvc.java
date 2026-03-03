package com.sena.cafetin.Service.SegurityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.Entity.Segurity.Rol;
import com.sena.cafetin.IService.SegurityIService.RolISvc;

@Service
public class RolSvc implements RolISvc {
    @Autowired
    private RolISvc rolService;

    @Override
    public List<Rol> FindAll() {
        return rolService.FindAll();
    }

    @Override
    public Rol save(Rol rol) {
        return rolService.save(rol);
    }
}
