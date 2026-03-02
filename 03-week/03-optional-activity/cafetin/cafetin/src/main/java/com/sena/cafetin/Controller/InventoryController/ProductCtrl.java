package com.sena.cafetin.Controller.InventoryController;


import com.sena.cafetin.Entity.Inventory.Product;
import com.sena.cafetin.Service.InventoryService.productSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductCtrl {

    @Autowired
    private productSvc service;

    @GetMapping
    public List<Product> list() {
        return service.findAll();
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return service.save(product);
    }
}