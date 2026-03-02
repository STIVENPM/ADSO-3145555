package com.sena.cafetin.Repository.InventoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.cafetin.Entity.Inventory.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}