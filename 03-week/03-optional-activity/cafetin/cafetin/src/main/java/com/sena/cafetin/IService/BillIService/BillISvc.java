package com.sena.cafetin.IService.BillIService;

import java.util.List;

import com.sena.cafetin.Entity.Bill.Bill;

public interface BillISvc {
    List<Bill> findAll();
    Bill save(Bill bill);
}
