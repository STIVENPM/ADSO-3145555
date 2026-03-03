package com.sena.cafetin.Controller.BillController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.cafetin.Entity.Bill.BillDtl;
import com.sena.cafetin.Service.BillService.BillDtlSvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/bill-dtl")
public class BillDtlCtrl {
    @Autowired
    private BillDtlSvc billDtlSvc;

    @GetMapping
    public List<BillDtl> list() {
        return billDtlSvc.findAll();
    }

    @PostMapping
    public BillDtl save(@RequestBody BillDtl billDtl) {
        return billDtlSvc.save(billDtl);
    }
    

}
