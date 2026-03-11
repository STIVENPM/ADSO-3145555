package com.sena.cafetin.Service.SegurityService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.Dto.SegurityDto.UserRolDto;
import com.sena.cafetin.Entity.Segurity.Rol;
import com.sena.cafetin.Entity.Segurity.User;
import com.sena.cafetin.Entity.Segurity.Users_Rol;
import com.sena.cafetin.IService.SegurityIService.UserRolISvc;
import com.sena.cafetin.Repository.SegurityRepository.RolRepo;
import com.sena.cafetin.Repository.SegurityRepository.UserRepo;
import com.sena.cafetin.Repository.SegurityRepository.UserRolRepo;

@Service
public class UserRolSvc implements UserRolISvc {

    @Autowired
    private UsersRolRepo usersRolRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RolRepo rolRepo;

    @Override
    public List<UserRolDto> FindAll() {
        return usersRolRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserRolDto FindById(Integer id) {
        Users_Rol ur = usersRolRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Relacion usuario-rol no encontrada"));
        return convertToDto(ur);
    }

    @Override
    public UserRolDto save(UserRolDto dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepo.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Users_Rol ur = new Users_Rol();
        ur.setUser(user);
        ur.setRol(rol);

        Users_Rol saved = usersRolRepo.save(ur);
        return convertToDto(saved);
    }

    @Override
    public UserRolDto update(UserRolDto dto, Integer id) {

        Users_Rol existing = usersRolRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Relacion usuario-rol no encontrada"));

        User user = userRepo.FindById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepo.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        existing.setUser(user);
        existing.setRol(rol);

        Users_Rol updated = usersRolRepo.save(existing);
        return convertToDto(updated);
    }

    @Override
    public void delete(Integer id) {
        if (!usersRolRepo.existsById(id)) {
            throw new RuntimeException("Relacion usuario-rol no encontrada");
        }
        usersRolRepo.deleteById(id);
    }

    // ==========================
    // CONVERSION
    // ==========================
    private UserRolDto convertToDto(Users_Rol ur) {
        return new UserRolDto(
                ur.getId(),
                ur.getUser().getId(),
                ur.getRol().getId()
        );
    }
}