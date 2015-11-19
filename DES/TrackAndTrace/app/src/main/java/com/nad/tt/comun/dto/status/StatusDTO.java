package com.nad.tt.comun.dto.status;

/**
 * Created by TI-MAURICIO on 14/11/2015.
 */
public class StatusDTO {

    private int idStatus;
    private String descStatus;

    public StatusDTO(int idStatus, String descStatus) {
        this.idStatus = idStatus;
        this.descStatus = descStatus;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }
}
