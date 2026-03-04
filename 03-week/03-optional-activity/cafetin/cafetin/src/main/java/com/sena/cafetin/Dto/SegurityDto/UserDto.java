package com.sena.cafetin.Dto.SegurityDto;


import java.util.List;

public class UserDto {

    private Integer id;
    private String username;

    private Integer personId; // solo enviamos el id de la persona
    private List<Integer> roleIds; // lista de ids de roles

    public UserDto() {
    }
//Category, Bill, BillDtl,
    public UserDto(Integer id, String username, Integer personId, List<Integer> roleIds) {
        this.id = id;
        this.username = username;
        this.personId = personId;
        this.roleIds = roleIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
