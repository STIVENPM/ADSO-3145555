package com.sena.cafetin.Controller.BillController;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sena.cafetin.Dto.BillDto.BillDto;
import com.sena.cafetin.Entity.Bill.Bill;
import com.sena.cafetin.Entity.Segurity.Person;
import com.sena.cafetin.IService.BillIService.BillISvc;
import com.sena.cafetin.Repository.BillRepository.BillRepo;
import com.sena.cafetin.Repository.SegurityRepository.PersonRepo;
import org.springframework.web.bind.annotation.*;






@RestController
@RequestMapping("/bill-dtl")
public class BillDtlCtrl implements BillISvc {

    @Autowired
    private BillRepo billRepo;

    @Autowired
    private PersonRepo personRepo;

    @Override
    public List<BillDto> findAll() {
        return billRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BillDto findById(Integer id) {
        Bill bill = billRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return convertToDto(bill);
    }

    @Override
    public BillDto save(BillDto billDto) {

        Person person = personRepo.findById(billDto.getPersonId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Bill bill = new Bill();
        bill.setDate(billDto.getDate());
        bill.setTotal(billDto.getTotal());
        bill.setPerson(person);

        Bill saved = billRepo.save(bill);

        return convertToDto(saved);
    }

    @Override
    public BillDto update(BillDto billDto, Integer id) {

        Bill existing = billRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        Person person = personRepo.findById(billDto.getPersonId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        existing.setDate(billDto.getDate());
        existing.setTotal(billDto.getTotal());
        existing.setPerson(person);

        Bill updated = billRepo.save(existing);

        return convertToDto(updated);
    }

    @Override
    public void delete(Integer id) {
        if (!billRepo.existsById(id)) {
            throw new RuntimeException("Factura no encontrada");
        }
        billRepo.deleteById(id);
    }

    // ==========================
    // MÉTODOS DE CONVERSIÓN
    // ==========================

    private BillDto convertToDto(Bill bill) {
        return new BillDto(
                bill.getId(),
                bill.getDate(),
                bill.getTotal(),
                bill.getPerson().getId(),
                null // puedes agregar billDetailIds después
        );
    }
}