package com.nad.tt.comun.dto;

/**
 * Created by Diego on 23/10/2015.
 */
public class BeanResponseDTO<T> {

    private String codError;
    private String msgError;
    private T result;

    public BeanResponseDTO() {
    }

    public BeanResponseDTO(String codError, String msgError, T result) {
        this.codError = codError;
        this.msgError = msgError;
        this.result = result;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}