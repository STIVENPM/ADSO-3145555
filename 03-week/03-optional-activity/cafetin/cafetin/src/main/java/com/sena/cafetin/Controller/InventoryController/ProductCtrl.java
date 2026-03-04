package com.sena.cafetin.Controller.InventoryController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.cafetin.Dto.InventoryDto.ProductDto;
import com.sena.cafetin.IService.InventoryIService.ProductISvc;
@RestController
@RequestMapping("/product")
public class ProductCtrl {

    @Autowired
    private ProductISvc productSvc;

   
    // OBTENER TODOS
   
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(productSvc.findAll());
    }

   
    // OBTENER POR ID
   
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(productSvc.findById(id));
    }

   
    // CREAR PRODUCTO
   
    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productSvc.save(productDto));
    }

   
    // ACTUALIZAR PRODUCTO
   
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto,
                                             @PathVariable Integer id) {
        return ResponseEntity.ok(productSvc.update(productDto, id));
    }

   
    // ELIMINAR PRODUCTO
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productSvc.delete(id);
        return ResponseEntity.noContent().build();
    }
}