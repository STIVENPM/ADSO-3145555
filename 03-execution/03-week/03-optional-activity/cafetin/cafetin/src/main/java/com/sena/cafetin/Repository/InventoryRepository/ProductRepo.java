package com.sena.cafetin.Repository.InventoryRepository;

import com.sena.cafetin.Entity.Inventory.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}