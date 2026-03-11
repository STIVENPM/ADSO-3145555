package com.sena.cafetin.Entity.Bill;

import java.time.LocalDate;


import jakarta.persistence.*;

@Entity(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate Date ;
    private Double total;

    public Bill() {
    }

    public Bill(Integer id, LocalDate Date, Double total) {
        this.id = id;
        this.Date = Date;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

   


    
}