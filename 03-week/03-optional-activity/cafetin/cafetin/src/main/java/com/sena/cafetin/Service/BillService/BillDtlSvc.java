package com.sena.cafetin.Service.BillService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.Entity.Bill.Bill;
import com.sena.cafetin.Entity.Bill.BillDtl;
import com.sena.cafetin.IService.BillIService.BillDtlISvc;
import com.sena.cafetin.Repository.BillRepository.BillDtlRepo;
import com.sena.cafetin.Repository.BillRepository.BillRepo;


@Service
public class BillDtlSvc implements BillDtlISvc {
    @Autowired
    private BillDtlRepo billDtlRepo;

    @Autowired
    private BillRepo billRepo;

    @Override
    public List<BillDtl> findAll() {
        return billDtlRepo.findAll();
    }

    @Override
    public BillDtl save(BillDtl detail) {
        // Calcular subtotal del detalle
        double subtotal = detail.getQuantity() * detail.getPrice();

        // Actualizar el total de la factura
        Bill bill = detail.getBill();
        if (bill != null) {
            Double currentTotal = bill.getTotal() != null ? bill.getTotal() : 0.0;
            bill.setTotal(currentTotal + subtotal);
            billRepo.save(bill);
        }

        // Guardar detalle
        return billDtlRepo.save(detail);
    }
}
