package com.sena.cafetin.Controller.InventoryController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.sena.cafetin.Entity.Inventory.Category;
import com.sena.cafetin.Service.InventoryService.CategorySvc;

@RestController
@RequestMapping("/category")
public class CategoryCtrl {

    @Autowired
    private CategorySvc service;

    @GetMapping
    public List<Category> list() {
        return service.findAll();
    }

    @PostMapping
    public Category save(@RequestBody Category category) {
        return service.save(category);
    }
}