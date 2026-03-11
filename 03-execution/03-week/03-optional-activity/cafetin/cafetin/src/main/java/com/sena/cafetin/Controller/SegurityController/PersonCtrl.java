package com.sena.cafetin.Controller.SegurityController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.cafetin.Dto.SegurityDto.PersonDto;

import com.sena.cafetin.IService.SegurityIService.PersonISvc;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/person")
public class PersonCtrl {

    @Autowired
    private PersonISvc personSvc;

    // GET: traer todos
    @GetMapping
    public ResponseEntity<List<PersonDto>> findAll() {
        return ResponseEntity.ok(personSvc.FindAll());
    }

    // GET: traer por id
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(personSvc.FindById(id));
    }

    // POST: guardar
    @PostMapping
    public ResponseEntity<PersonDto> save(@RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personSvc.save(personDto));
    }

    // PUT: actualizar
    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> update(@RequestBody PersonDto personDto, @PathVariable Integer id) {
        return ResponseEntity.ok(personSvc.update(personDto, id));
    }

    // DELETE: eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        personSvc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
