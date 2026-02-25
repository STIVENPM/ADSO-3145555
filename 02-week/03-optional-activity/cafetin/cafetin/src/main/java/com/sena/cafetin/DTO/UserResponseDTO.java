package com.sena.cafetin.DTO;

public class UserResponseDTO {

    private int id;
    private String email;
    private String userName;
    private int idPerson;

    public UserResponseDTO() {
        super();
    }

    public UserResponseDTO(int id, String email, String userName, int idPerson) {
        super();
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.idPerson = idPerson;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public int getIdPerson() { return idPerson; }
    public void setIdPerson(int idPerson) { this.idPerson = idPerson; }
}