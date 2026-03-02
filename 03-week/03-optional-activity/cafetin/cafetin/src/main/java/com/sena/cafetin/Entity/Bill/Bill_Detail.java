package com.sena.cafetin.Entity.Bill;

import com.sena.cafetin.Entity.Inventory.Product;

import jakarta.persistence.*;
@Entity(name = "bill_detail")
public class Bill_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;
    private Double price;



    @ManyToOne
    @JoinColumn(name = "Product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "Bill_id")
    private Bill bill;

    public Bill_Detail() {
    }



    public Bill_Detail(Integer id, Integer quantity, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
