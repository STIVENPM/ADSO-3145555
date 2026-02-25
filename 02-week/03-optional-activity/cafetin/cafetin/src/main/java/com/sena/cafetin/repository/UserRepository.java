package com.sena.cafetin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.cafetin.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>  {

}
