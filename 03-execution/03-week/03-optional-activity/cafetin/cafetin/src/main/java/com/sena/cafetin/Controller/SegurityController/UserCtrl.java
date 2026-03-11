package com.sena.cafetin.Controller.SegurityController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.cafetin.Dto.SegurityDto.UserDto;
import com.sena.cafetin.IService.SegurityIService.UserISvc;

@RestController
@RequestMapping("/api/users")
public class UserCtrl {

    @Autowired
    private UserISvc userSvc;

    
    // OBTENER TODOS
    
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userSvc.findAll());
    }

    
    // OBTENER POR ID
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userSvc.FindById(id));
    }

    
    // CREAR USUARIO
    
    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userSvc.save(userDto));
    }

    
    // ACTUALIZAR USUARIO
    
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto,
                                          @PathVariable Integer id) {
        return ResponseEntity.ok(userSvc.update(userDto, id));
    }

    
    // ELIMINAR USUARIO
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userSvc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
