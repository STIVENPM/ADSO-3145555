package com.sena.cafetin.IService.BillIService;

import java.util.List;

import com.sena.cafetin.Entity.Bill.BillDtl;

public interface BillDtlISvc {
    List<BillDtl> findAll();
    BillDtl save(BillDtl billDtl);

}
