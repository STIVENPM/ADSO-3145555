package com.sena.cafetin.Service.BillService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.cafetin.Dto.BillDto.BillDtlDto;
import com.sena.cafetin.Entity.Bill.Bill;
import com.sena.cafetin.Entity.Bill.BillDtl;
import com.sena.cafetin.Entity.Inventory.Product;
import com.sena.cafetin.IService.BillIService.BillDtlISvc;
import com.sena.cafetin.Repository.BillRepository.BillDtlRepo;
import com.sena.cafetin.Repository.BillRepository.BillRepo;
import com.sena.cafetin.Repository.InventoryRepository.ProductRepo;

@Service
public class BillDtlSvc implements BillDtlISvc {

    @Autowired
    private BillDtlRepo billDtlRepo;

    @Autowired
    private BillRepo billRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<BillDtlDto> findAll() {
        return billDtlRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BillDtlDto findById(Integer id) {
        BillDtl billDtl = billDtlRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de factura no encontrado"));
        return convertToDto(billDtl);
    }

    @Override
    public BillDtlDto save(BillDtlDto billDtlDto) {

        Bill bill = billRepo.findById(billDtlDto.getBillId())
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        Product product = productRepo.findById(billDtlDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        BillDtl billDtl = new BillDtl();
        billDtl.setQuantity(billDtlDto.getQuantity());
        billDtl.setPrice(billDtlDto.getPrice());
        billDtl.setBill(bill);
        billDtl.setProduct(product);

        BillDtl saved = billDtlRepo.save(billDtl);

        return convertToDto(saved);
    }

    @Override
    public BillDtlDto update(BillDtlDto billDtlDto, Integer id) {

        BillDtl existing = billDtlRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de factura no encontrado"));

        Bill bill = billRepo.findById(billDtlDto.getBillId())
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        Product product = productRepo.findById(billDtlDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existing.setQuantity(billDtlDto.getQuantity());
        existing.setPrice(billDtlDto.getPrice());
        existing.setBill(bill);
        existing.setProduct(product);

        BillDtl updated = billDtlRepo.save(existing);

        return convertToDto(updated);
    }

    @Override
    public void delete(Integer id) {
        if (!billDtlRepo.existsById(id)) {
            throw new RuntimeException("Detalle de factura no encontrado");
        }
        billDtlRepo.deleteById(id);
    }

    // ==========================
    // MÉTODOS DE CONVERSIÓN
    // ==========================

    private BillDtlDto convertToDto(BillDtl billDtl) {
        return new BillDtlDto(
                billDtl.getId(),
                billDtl.getQuantity(),
                billDtl.getPrice(),
                billDtl.getProduct().getId(),
                billDtl.getBill().getId()
        );
    }
}