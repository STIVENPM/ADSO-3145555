package com.sena.cafetin.Controller.BillController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.cafetin.Entity.Bill.Bill;
import com.sena.cafetin.IService.BillIService.BillISvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/bill")
public class BillCtrl {

    @Autowired
    private BillISvc service;

    @GetMapping
    public List<Bill> list() {
    return service.findAll();
    }

    @PostMapping
    public Bill save(@RequestParam Bill bill) {
        return service.save(bill);
    }
    
}
