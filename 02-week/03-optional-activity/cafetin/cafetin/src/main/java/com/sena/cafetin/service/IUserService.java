package com.sena.cafetin.service;

import java.util.List;

import com.sena.cafetin.DTO.UserResponseDTO;
import com.sena.cafetin.entity.User;

public interface IUserService {

    UserResponseDTO crear(int idPerson, User user);

    List<UserResponseDTO> listar();

    UserResponseDTO buscarPorId(int id);

    UserResponseDTO actualizar(int id, User user);

    void eliminar(int id);
}