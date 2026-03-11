package com.sena.cafetin.Dto.BillDto;



public class BillDtlDto  {

    private Integer id;
    private Integer quantity;
    private Double price;

    private Integer productId;
    private Integer billId;

    public BillDtlDto() {
    }

    public BillDtlDto(Integer id, Integer quantity, Double price, Integer productId, Integer billId) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.billId = billId;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }
}