package com.sena.cafetin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.entity.Rol;
import com.sena.cafetin.repository.RolRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepo;

    public Rol crear(Rol rol) {
        return rolRepo.save(rol);
    }

    public List<Rol> listar() {
        return rolRepo.findAll();
    }

    public Rol buscarPorId(int id) {
        return rolRepo.findById(id).orElse(null);
    }

    public void eliminar(int id) {
        rolRepo.deleteById(id);
    }
}