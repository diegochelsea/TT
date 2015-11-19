package com.nad.tt.comun.dto.folio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by TI-MAURICIO on 15/11/2015.
 */
public class FolioDTO implements Serializable {

    private String folio;
    private String sorce;
    private String receiver;
    private String status;

    private ArrayList<String> users;

    public FolioDTO() {

    }

    public FolioDTO(String folio, String sorce, String receiver, String status) {
        this.folio = folio;
        this.sorce = sorce;
        this.receiver = receiver;
        this.status = status;
    }


    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getSorce() {
        return sorce;
    }

    public void setSorce(String sorce) {
        this.sorce = sorce;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
