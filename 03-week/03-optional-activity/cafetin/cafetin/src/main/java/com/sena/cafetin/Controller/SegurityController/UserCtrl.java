package com.sena.cafetin.Controller.SegurityController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sena.cafetin.Entity.Segurity.User;
import com.sena.cafetin.IService.SegurityIService.UserISvc;


@RestController
@RequestMapping("/api/users")
public class UserCtrl {

    @Autowired
    private UserISvc userSvc;

    @GetMapping
    public List<User> getAllUsers() {
        return userSvc.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userSvc.save(user);
    }

}
