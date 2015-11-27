package com.nad.tt.comun.dto.folio;

import java.io.Serializable;

/**
 * Created by TI-MAURICIO on 15/11/2015.
 */
public class FolioDTO implements Serializable {

    public int idFolio;
    public String beginning = "";
    public String destination = "";
    public String status = "";
    public String[] users;
    public String codError;
    public String msgError;

    public FolioDTO() {
        // TODO Auto-generated constructor stub
    }

    public FolioDTO(int idFolio, String beginning, String destination,
                    String status) {
        super();
        this.idFolio = idFolio;
        this.beginning = beginning;
        this.destination = destination;
        this.status = status;
    }

    public String getCodError() {
        return codError;
    }

    public void setCodError(String codError) {
        this.codError = codError;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public int getIdFolio() {
        return idFolio;
    }

    public void setIdFolio(int idFolio) {
        this.idFolio = idFolio;
    }

    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }
}


