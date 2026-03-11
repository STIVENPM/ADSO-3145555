package com.sena.cafetin.Repository.BillRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.cafetin.Entity.Bill.Bill;

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer> {

}
