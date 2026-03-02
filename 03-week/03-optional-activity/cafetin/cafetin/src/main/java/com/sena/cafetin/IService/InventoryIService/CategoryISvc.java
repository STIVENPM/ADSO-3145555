package com.sena.cafetin.IService.InventoryIService;

import com.sena.cafetin.Entity.Inventory.Category;
import java.util.List;

public interface CategoryISvc {
    List<Category> findAll();
    Category save(Category category);
}




