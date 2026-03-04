package com.sena.cafetin.Controller.SegurityController;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.cafetin.Dto.SegurityDto.RolDto;
import com.sena.cafetin.IService.SegurityIService.RolISvc;
@RestController
@RequestMapping("/api/rol")
public class RolCtrl {

    @Autowired
    private RolISvc rolSvc;

    @GetMapping
    public ResponseEntity<List<RolDto>> findAll() {
        return ResponseEntity.ok(rolSvc.FindAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(rolSvc.FindById(id));
    }

    @PostMapping
    public ResponseEntity<RolDto> save(@RequestBody RolDto rolDto) {
        return ResponseEntity.ok(rolSvc.save(rolDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDto> update(@RequestBody RolDto rolDto, @PathVariable Integer id) {
        return ResponseEntity.ok(rolSvc.update(rolDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        rolSvc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
