package com.sena.cafetin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.entity.Rol;
import com.sena.cafetin.entity.User;
import com.sena.cafetin.entity.UsersRol;
import com.sena.cafetin.repository.RolRepository;
import com.sena.cafetin.repository.UserRepository;
import com.sena.cafetin.repository.UsersRolRepository;

@Service
public class UsersRolService {

    @Autowired
    private UsersRolRepository usersRolRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RolRepository rolRepo;

    // Asignar un rol a un usuario
    public UsersRol asignar(int idUser, int idRol) {

        User u = userRepo.findById(idUser).orElse(null);
        Rol r = rolRepo.findById(idRol).orElse(null);

        if (u == null || r == null) {
            return null;
        }

        UsersRol ur = new UsersRol();
        ur.setUser(u);
        ur.setRol(r);

        return usersRolRepo.save(ur);
    }

    // Listar todas las asignaciones
    public List<UsersRol> listar() {
        return usersRolRepo.findAll();
    }

    // Buscar por id
    public UsersRol buscarPorId(int id) {
        return usersRolRepo.findById(id).orElse(null);
    }

    // Eliminar asignación
    public void eliminar(int id) {
        usersRolRepo.deleteById(id);
    }
}