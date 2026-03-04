package com.sena.cafetin.Service.SegurityService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.Dto.SegurityDto.UserDto;
import com.sena.cafetin.Entity.Segurity.Person;
import com.sena.cafetin.Entity.Segurity.Rol;
import com.sena.cafetin.Entity.Segurity.User;
import com.sena.cafetin.Entity.Segurity.Users_Rol;
import com.sena.cafetin.IService.SegurityIService.UserISvc;
import com.sena.cafetin.Repository.SegurityRepository.PersonRepo;
import com.sena.cafetin.Repository.SegurityRepository.RolRepo;
import com.sena.cafetin.Repository.SegurityRepository.UserRepo;
import com.sena.cafetin.Repository.SegurityRepository.UserRolRepo;

@Service
public class UserSvc implements UserISvc {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private UsersRolRepo usersRolRepo;

    @Autowired
    private RolRepo rolRepo;

    @Override
    public List<UserDto> findAll() {
        return userRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto FindById(Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return convertToDto(user);
    }

    @Override
    public UserDto save(UserDto userDto) {

        Person person = personRepo.findById(userDto.getPersonId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        User user = new User();
        user.setUsername(userDto.getUsername());
        // La contraseña normalmente se maneja en otro DTO (registro/login).
        // Aquí la dejamos vacía o se setea desde otro flujo.
        user.setPerson(person);

        User savedUser = userRepo.save(user);

        // Si llegan roles, los guardamos en la tabla puente Users_Rol
        if (userDto.getRoleIds() != null) {
            for (Integer rolId : userDto.getRoleIds()) {

                Rol rol = rolRepo.findById(rolId)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

                Users_Rol ur = new Users_Rol();
                ur.setUser(savedUser);
                ur.setRol(rol);
                usersRolRepo.save(ur);
            }
        }

        // Volvemos a consultar para reflejar roles (opcional)
        User finalUser = userRepo.findById(savedUser.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return convertToDto(finalUser);
    }

    @Override
    public UserDto update(UserDto userDto, Integer id) {

        User existing = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Person person = personRepo.findById(userDto.getPersonId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        existing.setUsername(userDto.getUsername());
        existing.setPerson(person);

        User updatedUser = userRepo.save(existing);

        // Si quieres actualizar roles aquí hay dos caminos:
        // 1) Borrar roles actuales y volver a insertar
        // 2) Comparar y actualizar
        // Te dejo la opción 1 (simple):

        if (userDto.getRoleIds() != null) {
            // borrar relaciones actuales
            List<Users_Rol> actuales = usersRolRepo.findAll()
                    .stream()
                    .filter(x -> x.getUser().getId().equals(updatedUser.getId()))
                    .collect(Collectors.toList());

            for (Users_Rol ur : actuales) {
                usersRolRepo.deleteById(ur.getId());
            }

            // insertar nuevas
            for (Integer rolId : userDto.getRoleIds()) {
                Rol rol = rolRepo.findById(rolId)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

                Users_Rol ur = new Users_Rol();
                ur.setUser(updatedUser);
                ur.setRol(rol);
                usersRolRepo.save(ur);
            }
        }

        User finalUser = userRepo.findById(updatedUser.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return convertToDto(finalUser);
    }

    @Override
    public void delete(Integer id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Borrar primero relaciones usuario-rol (para evitar error de FK)
        List<Users_Rol> actuales = usersRolRepo.findAll()
                .stream()
                .filter(x -> x.getUser().getId().equals(id))
                .collect(Collectors.toList());

        for (Users_Rol ur : actuales) {
            usersRolRepo.deleteById(ur.getId());
        }

        userRepo.deleteById(id);
    }

    // ==========================
    // CONVERSION
    // ==========================
    private UserDto convertToDto(User user) {

        List<Integer> roleIds = null;

        if (user.getUsersRoles() != null) {
            roleIds = user.getUsersRoles()
                    .stream()
                    .map(ur -> ur.getRol().getId())
                    .collect(Collectors.toList());
        }

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPerson().getId(),
                roleIds
        );
    }
}