package com.sena.cafetin.Repository.SegurityRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.cafetin.Entity.Segurity.Users_Rol;

@Repository
public interface UserRolRepo extends  JpaRepository<Users_Rol, Integer> {

}
