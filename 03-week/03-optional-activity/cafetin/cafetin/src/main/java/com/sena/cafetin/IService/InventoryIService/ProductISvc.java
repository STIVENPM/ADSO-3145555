package com.sena.cafetin.IService.InventoryIService;

import com.sena.cafetin.Entity.Inventory.Product;
import java.util.List;

public interface ProductISvc {
    List<Product> findAll();
    Product save(Product product);
}