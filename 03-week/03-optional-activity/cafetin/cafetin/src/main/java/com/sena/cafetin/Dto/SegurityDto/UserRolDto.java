package com.sena.cafetin.Dto.SegurityDto;


public class UserRolDto {

    private Integer id;
    private Integer userId;
    private Integer rolId;

    public UserRolDto() {
    }

    public UserRolDto(Integer id, Integer userId, Integer rolId) {
        this.id = id;
        this.userId = userId;
        this.rolId = rolId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }
}