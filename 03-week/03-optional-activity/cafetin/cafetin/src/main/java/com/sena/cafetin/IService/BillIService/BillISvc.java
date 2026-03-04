package com.sena.cafetin.IService.BillIService;

import java.util.List;
import com.sena.cafetin.Dto.BillDto.BillDto;


public interface BillISvc {
    List<BillDto> findAll();
    BillDto save(BillDto billDto);
    BillDto update(BillDto billDto, Integer id);
    void delete(Integer id);
    BillDto findById(Integer id);
}
