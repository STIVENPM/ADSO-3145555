package com.sena.cafetin.Controller.SegurityController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.cafetin.Dto.SegurityDto.UserRolDto;
import com.sena.cafetin.IService.SegurityIService.UserRolISvc;
@RestController
@RequestMapping("/api/user-rol")
public class UserRolCtrl {

    @Autowired
    private UserRolISvc userRolSvc;

    @GetMapping
    public ResponseEntity<List<UserRolDto>> findAll() {
        return ResponseEntity.ok(userRolSvc.FindAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRolDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userRolSvc.FindById(id));
    }

    @PostMapping
    public ResponseEntity<UserRolDto> save(@RequestBody UserRolDto userRolDto) {
        return ResponseEntity.ok(userRolSvc.save(userRolDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRolDto> update(@RequestBody UserRolDto userRolDto, @PathVariable Integer id) {
        return ResponseEntity.ok(userRolSvc.update(userRolDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userRolSvc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
