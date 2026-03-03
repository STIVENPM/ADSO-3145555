package com.sena.cafetin.Service.InventoryService;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.Dto.InventoryDto.CategoryDto;
import com.sena.cafetin.Entity.Inventory.Category;
import com.sena.cafetin.IService.InventoryIService.CategoryISvc;
import com.sena.cafetin.Repository.InventoryRepository.CategoryRepo;

@Service
public class CategorySvc implements CategoryISvc {

    @Autowired
    private CategoryRepo repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }
public List<CategoryDto> findAllDto() {
    List<Category> entities = repository.findAll();
    List<CategoryDto> dtos = new ArrayList<>();

    for (Category cat : entities) {
        dtos.add(new CategoryDto(cat.getId(), cat.getName(), cat.getDescription()));
    }

    return dtos;
}

}