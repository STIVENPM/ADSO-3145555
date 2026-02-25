package com.sena.cafetin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.cafetin.entity.Rol;
import com.sena.cafetin.service.RolService;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping
    public Rol create(@RequestBody Rol rol) {
        return rolService.crear(rol);
    }

    @GetMapping
    public List<Rol> getAll() {
        return rolService.listar();
    }

    @GetMapping("/{id}")
    public Rol getById(@PathVariable int id) {
        return rolService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        rolService.eliminar(id);
        return "Rol eliminado";
    }
}