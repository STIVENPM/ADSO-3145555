package com.sena.cafetin.Service.InventoryService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}