package com.sena.cafetin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.cafetin.DTO.UserResponseDTO;
import com.sena.cafetin.entity.User;
import com.sena.cafetin.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //  Crear
    @PostMapping("/create/{idPerson}")
    public UserResponseDTO create(@PathVariable int idPerson, @RequestBody User user) {
        return userService.crear(idPerson, user);
    }

    //  Listar
    @GetMapping
    public List<UserResponseDTO> getAll() {
        return userService.listar();
    }

    //  Buscar por id
    @GetMapping("/{id}")
    public UserResponseDTO getById(@PathVariable int id) {
        return userService.buscarPorId(id);
    }

    //  Actualizar
    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable int id, @RequestBody User user) {
        return userService.actualizar(id, user);
    }

    //  Eliminar
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        userService.eliminar(id);
        return "User eliminado";
    }
}