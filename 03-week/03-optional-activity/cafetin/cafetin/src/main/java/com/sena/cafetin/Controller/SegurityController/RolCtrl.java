package com.sena.cafetin.Controller.SegurityController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sena.cafetin.Entity.Segurity.Rol;
import com.sena.cafetin.IService.SegurityIService.RolISvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/rol")
public class RolCtrl {
    @Autowired
    private RolISvc rolService;

    @GetMapping
    public List<Rol> FindAll() {
        return rolService.FindAll();
    }

    @PostMapping
    public Rol save(@RequestParam String name) {
        Rol rol = new Rol();
        rol.setName(name);
        return rolService.save(rol);
    }
}
