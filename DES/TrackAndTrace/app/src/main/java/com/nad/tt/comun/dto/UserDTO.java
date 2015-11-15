package com.nad.tt.comun.dto;

/**
 * Created by Diego on 23/10/2015.
 */
import com.nad.tt.util.DataType;
import com.nad.tt.util.TTWSConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 23/10/2015.
 */
public class UserDTO {

    private int idUser;
    private String name = "";
    private String lastName = "";
    private String email = "";
    private String password = "";

    public UserDTO() {
        // TODO Auto-generated constructor stub
    }

    public UserDTO(int idUser, String name, String lastName, String email,
                   String password) {
        super();
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public List<DataType> getColumns(boolean idRequired) {
        List<DataType> dataTypes = new ArrayList<>();

        if (idRequired) {
            DataType typeId = new DataType(TTWSConstants.INTEGER, "idUser",
                    String.valueOf(getIdUser()));
            dataTypes.add(typeId);
        }
        DataType typeName = new DataType(TTWSConstants.STRING, "name",
                getName());
        DataType typeLastName = new DataType(TTWSConstants.STRING, "lastName",
                getLastName());
        DataType typeEmail = new DataType(TTWSConstants.INTEGER, "email",
                getEmail());
        DataType typePass = new DataType(TTWSConstants.INTEGER, "password",
                getPassword());

        dataTypes.add(typeName);
        dataTypes.add(typeLastName);
        dataTypes.add(typeEmail);
        dataTypes.add(typePass);
        return dataTypes;
    }

    public DataType getWhereById() {
        StringBuilder where = new StringBuilder(TTWSConstants.WHERE)
                .append("idUser").append(TTWSConstants.EQUAL)
                .append(TTWSConstants.ONE_WHITE_SPACE);
        DataType idDataType = new DataType(TTWSConstants.INTEGER,
                where.toString(), String.valueOf(getIdUser()));
        return idDataType;
    }

}
