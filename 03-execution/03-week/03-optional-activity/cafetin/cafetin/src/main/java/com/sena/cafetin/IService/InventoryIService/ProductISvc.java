package com.sena.cafetin.IService.InventoryIService;

import java.util.List;
import com.sena.cafetin.Dto.InventoryDto.ProductDto;

public interface ProductISvc {
    List<ProductDto> findAll();
    ProductDto save(ProductDto productDto);
    ProductDto update(ProductDto productDto, Integer id);
    void delete(Integer id);
    ProductDto findById(Integer id);
}