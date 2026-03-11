package com.sena.cafetin.IService.BillIService;

import java.util.List;

import com.sena.cafetin.Dto.BillDto.BillDtlDto;


public interface BillDtlISvc {
    List<BillDtlDto> findAll();
    BillDtlDto save(BillDtlDto billDtlDto);
    BillDtlDto update(BillDtlDto billDtlDto, Integer id);
    void delete(Integer id);
    BillDtlDto findById(Integer id);

}
