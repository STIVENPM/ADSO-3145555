package com.sena.cafetin.Repository.BillRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.cafetin.Entity.Bill.BillDtl;

@Repository
public interface BillDtlRepo extends JpaRepository<BillDtl, Integer> {


}
