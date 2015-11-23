package com.nad.tt.comun.dto;

public class RolDTO {

	private String codError;
	private String msgError;
	private int idRol;
	private String desc = "";

	public RolDTO() {
		// TODO Auto-generated constructor stub
	}

	public RolDTO(int idRol, String desc) {
		super();
		this.idRol = idRol;
		this.desc = desc;
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

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
