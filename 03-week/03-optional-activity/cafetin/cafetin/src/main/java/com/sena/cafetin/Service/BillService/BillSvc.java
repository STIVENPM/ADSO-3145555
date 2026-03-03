package com.sena.cafetin.Service.BillService;

import com.sena.cafetin.Entity.Bill.Bill;
import com.sena.cafetin.IService.BillIService.BillISvc;
import com.sena.cafetin.Repository.BillRepository.BillRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillSvc implements BillISvc {

    @Autowired
    private BillRepo billRepo;

    @Override
    public List<Bill> findAll() {
        return billRepo.findAll();
    }

    @Override
    public Bill save(Bill bill) {
        return billRepo.save(bill);
    }
}
