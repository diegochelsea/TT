package com.nad.tt.comun.dto;

/**
 * Created by Diego on 23/10/2015.
 */
public class UserDTO {

    private int idUser;

    private String name;

    private String user;

    public UserDTO() {
    }

    public UserDTO(int idUser, String name, String user) {
        this.idUser = idUser;
        this.name = name;
        this.user = user;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
