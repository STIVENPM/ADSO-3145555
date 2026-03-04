package com.sena.cafetin.IService.InventoryIService;

import com.sena.cafetin.Dto.InventoryDto.CategoryDto;
import java.util.List;

public interface CategoryISvc {
    List<CategoryDto> findAll();
    CategoryDto save(CategoryDto categoryDto);
    CategoryDto update(CategoryDto categoryDto, Integer id);
    void delete(Integer id);
    CategoryDto findById(Integer id);
}




