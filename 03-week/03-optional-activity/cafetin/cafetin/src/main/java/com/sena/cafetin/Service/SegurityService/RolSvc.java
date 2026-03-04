package com.sena.cafetin.Service.SegurityService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;


import com.sena.cafetin.Dto.SegurityDto.RolDto;
import com.sena.cafetin.Entity.Segurity.Rol;
import com.sena.cafetin.IService.SegurityIService.RolISvc;
import com.sena.cafetin.Repository.SegurityRepository.RolRepo;
public class RolSvc implements RolISvc {

    @Autowired
    private RolRepo rolRepo;

    @Override
    public List<RolDto> FindAll() {
        return rolRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RolDto FindById(Integer id) {
        Rol rol = rolRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        return convertToDto(rol);
    }

    @Override
    public RolDto save(RolDto rolDto) {
        Rol rol = convertToEntity(rolDto);
        Rol saved = rolRepo.save(rol);
        return convertToDto(saved);
    }

    @Override
    public RolDto update(RolDto rolDto, Integer id) {
        Rol existing = rolRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        existing.setName(rolDto.getName());

        Rol updated = rolRepo.save(existing);
        return convertToDto(updated);
    }

    @Override
    public void delete(Integer id) {
        if (!rolRepo.existsById(id)) {
            throw new RuntimeException("Rol no encontrado");
        }
        rolRepo.deleteById(id);
    }

    // ==========================
    // MÉTODOS DE CONVERSIÓN
    // ==========================

    private RolDto convertToDto(Rol rol) {
        return new RolDto(
                rol.getId(),
                rol.getName()
        );
    }

    private Rol convertToEntity(RolDto dto) {
        Rol rol = new Rol();
        rol.setId(dto.getId());
        rol.setName(dto.getName());
        return rol;
    }
}
