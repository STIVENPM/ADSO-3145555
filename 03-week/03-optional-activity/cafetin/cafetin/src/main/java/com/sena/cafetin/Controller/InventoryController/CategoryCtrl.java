package com.sena.cafetin.Controller.InventoryController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.sena.cafetin.Dto.InventoryDto.CategoryDto;
import com.sena.cafetin.Entity.Inventory.Category;
import com.sena.cafetin.Service.InventoryService.CategorySvc;

@RestController
@RequestMapping("/category")
public class CategoryCtrl {

    @Autowired
    private CategorySvc service;

    @GetMapping
    public List<CategoryDto> list() {
        return service.findAllDto();
    }

    @PostMapping
    public CategoryDto save(@RequestBody CategoryDto dto) {
        // Convertimos DTO → Entity
        Category category = new Category(dto.getId(), dto.getName(), dto.getDescription());
        Category saved = service.save(category);

        // Devolvemos DTO
        return new CategoryDto(saved.getId(), saved.getName(), saved.getDescription());
    }

}