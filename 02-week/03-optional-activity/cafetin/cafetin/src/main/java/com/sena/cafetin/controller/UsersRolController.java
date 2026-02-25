package com.sena.cafetin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.cafetin.entity.UsersRol;
import com.sena.cafetin.service.UsersRolService;

@RestController
@RequestMapping("/api/usersrol")
public class UsersRolController {

    @Autowired
    private UsersRolService usersRolService;

    // Asignar rol a user
    @PostMapping("/asignar/{idUser}/{idRol}")
    public UsersRol asignar(@PathVariable int idUser, @PathVariable int idRol) {
        return usersRolService.asignar(idUser, idRol);
    }

    // Listar asignaciones
    @GetMapping
    public List<UsersRol> getAll() {
        return usersRolService.listar();
    }

    // Buscar asignación por id
    @GetMapping("/{id}")
    public UsersRol getById(@PathVariable int id) {
        return usersRolService.buscarPorId(id);
    }

    // Eliminar asignación
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        usersRolService.eliminar(id);
        return "Asignacion eliminada";
    }
}