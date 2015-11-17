package com.nad.tt.comun.dto.folio;

/**
 * Created by TI-MAURICIO on 15/11/2015.
 */
public class FolioDTO {

    private String folio;
    private String sorce;
    private String receiver;
    private String status;

    public FolioDTO(String folio, String sorce, String receiver, String status) {
        this.folio = folio;
        this.sorce = sorce;
        this.receiver = receiver;
        this.status = status;
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
