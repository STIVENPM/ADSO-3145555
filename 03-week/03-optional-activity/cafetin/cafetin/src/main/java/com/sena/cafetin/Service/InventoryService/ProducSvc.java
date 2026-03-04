package com.sena.cafetin.Service.InventoryService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.Dto.InventoryDto.ProductDto;
import com.sena.cafetin.Entity.Inventory.Category;
import com.sena.cafetin.Entity.Inventory.Product;
import com.sena.cafetin.IService.InventoryIService.ProductISvc;
import com.sena.cafetin.Repository.InventoryRepository.CategoryRepo;
import com.sena.cafetin.Repository.InventoryRepository.ProductRepo;

@Service
public class ProducSvc implements ProductISvc {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<ProductDto> findAll() {
        return productRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Integer id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return convertToDto(product);
    }

    @Override
    public ProductDto save(ProductDto productDto) {

        Category category = categoryRepo.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCategory(category);

        Product saved = productRepo.save(product);
        return convertToDto(saved);
    }

    @Override
    public ProductDto update(ProductDto productDto, Integer id) {

        Product existing = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Category category = categoryRepo.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        existing.setName(productDto.getName());
        existing.setPrice(productDto.getPrice());
        existing.setStock(productDto.getStock());
        existing.setCategory(category);

        Product updated = productRepo.save(existing);
        return convertToDto(updated);
    }

    @Override
    public void delete(Integer id) {
        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productRepo.deleteById(id);
    }

    // ==========================
    // MÉTODO DE CONVERSIÓN
    // ==========================
    private ProductDto convertToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getId()
        );
    }
}
