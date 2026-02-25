package com.sena.cafetin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.DTO.UserResponseDTO;
import com.sena.cafetin.entity.Person;
import com.sena.cafetin.entity.User;
import com.sena.cafetin.repository.PersonRepository;
import com.sena.cafetin.repository.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PersonRepository personRepo;

    private UserResponseDTO convertir(User u) {
        return new UserResponseDTO(
            u.getId(),
            u.getEmail(),
            u.getUserName(),
            u.getPerson().getId()
        );
    }

    @Override
    public UserResponseDTO crear(int idPerson, User user) {

        Person p = personRepo.findById(idPerson).orElse(null);
        if (p == null) return null;

        user.setPerson(p);
        User saved = userRepo.save(user);

        return convertir(saved);
    }

    @Override
    public List<UserResponseDTO> listar() {

        List<User> lista = userRepo.findAll();
        List<UserResponseDTO> salida = new ArrayList<>();

        for (User u : lista) {
            salida.add(convertir(u));
        }

        return salida;
    }

    @Override
    public UserResponseDTO buscarPorId(int id) {

        User u = userRepo.findById(id).orElse(null);
        if (u == null) return null;

        return convertir(u);
    }

    @Override
    public UserResponseDTO actualizar(int id, User user) {

        User u = userRepo.findById(id).orElse(null);
        if (u == null) return null;

        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setUserName(user.getUserName());

        return convertir(userRepo.save(u));
    }

    @Override
    public void eliminar(int id) {
        userRepo.deleteById(id);
    }
}