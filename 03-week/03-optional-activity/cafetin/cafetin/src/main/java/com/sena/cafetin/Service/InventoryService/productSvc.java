package com.sena.cafetin.Service.InventoryService;


import com.sena.cafetin.Entity.Inventory.Product;
import com.sena.cafetin.IService.InventoryIService.ProductISvc;
import com.sena.cafetin.Repository.InventoryRepository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productSvc implements ProductISvc {

    @Autowired
    private ProductRepo repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);

    }
}