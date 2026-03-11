package com.sena.cafetin.Controller.InventoryController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.cafetin.Dto.InventoryDto.CategoryDto;
import com.sena.cafetin.Entity.Inventory.Category;
import com.sena.cafetin.IService.InventoryIService.CategoryISvc;
import com.sena.cafetin.Repository.InventoryRepository.CategoryRepo;

@RestController
@RequestMapping("/category")
public class CategoryCtrl implements CategoryISvc {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return convertToDto(category);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category saved = categoryRepo.save(category);
        return convertToDto(saved);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Integer id) {

        Category existing = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        existing.setName(categoryDto.getName());
        existing.setDescription(categoryDto.getDescription());

        Category updated = categoryRepo.save(existing);

        return convertToDto(updated);
    }

    @Override
    public void delete(Integer id) {
        if (!categoryRepo.existsById(id)) {
            throw new RuntimeException("Categoria no encontrada");
        }
        categoryRepo.deleteById(id);
    }

    // ==========================
    // MÉTODO DE CONVERSIÓN
    // ==========================
    private CategoryDto convertToDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}