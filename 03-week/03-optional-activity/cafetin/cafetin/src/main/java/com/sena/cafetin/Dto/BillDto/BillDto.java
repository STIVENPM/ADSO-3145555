package com.sena.cafetin.Dto.BillDto;


import java.time.LocalDate;
import java.util.List;

public class BillDto {

    private Integer id;
    private LocalDate date;
    private Double total;

    private Integer personId; // solo el id de la persona
    private List<Integer> billDetailIds; // lista de ids de detalles

    public BillDto() {
    }

    public BillDto(Integer id, LocalDate date, Double total, Integer personId, List<Integer> billDetailIds) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.personId = personId;
        this.billDetailIds = billDetailIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public List<Integer> getBillDetailIds() {
        return billDetailIds;
    }

    public void setBillDetailIds(List<Integer> billDetailIds) {
        this.billDetailIds = billDetailIds;
    }
}