package com.nad.tt.ws.dto.user;

import java.util.ArrayList;
import java.util.List;

import com.nad.tt.ws.util.DataType;
import com.nad.tt.ws.util.TTWSConstants;

public class UserDTO {

	private String codError;
	private String msgError;
	private int idUser;
	private String name = "";
	private String lastName = "";
	private String email = "";
	private String password = "";
	private int rol;

	private String[] folios;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(int idUser, String name, String lastName, String email,
			String password, int rol, String[] folios) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.folios = folios;
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

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String[] getFolios() {
		return folios;
	}

	public void setFolios(String[] folios) {
		this.folios = folios;
	}

	public List<DataType> getColumns(boolean idRequired) {
		List<DataType> dataTypes = new ArrayList<>();

		if (idRequired) {
			DataType typeId = new DataType(TTWSConstants.INTEGER,
					TTWSConstants.COLUMN_USER_ID_USER,
					String.valueOf(getIdUser()));
			dataTypes.add(typeId);
		}
		DataType typeName = new DataType(TTWSConstants.STRING,
				TTWSConstants.COLUMN_USER_NAME, getName());
		DataType typeLastName = new DataType(TTWSConstants.STRING,
				TTWSConstants.COLUMN_USER_LAST_NAME, getLastName());
		DataType typeEmail = new DataType(TTWSConstants.STRING,
				TTWSConstants.COLUMN_USER_EMAIL, getEmail());
		DataType typePass = new DataType(TTWSConstants.STRING,
				TTWSConstants.COLUMN_USER_PASS, getPassword());
		DataType typeRol = new DataType(TTWSConstants.INTEGER,
				TTWSConstants.COLUMN_USER_ROL, String.valueOf(getRol()));

		dataTypes.add(typeName);
		dataTypes.add(typeLastName);
		dataTypes.add(typeEmail);
		dataTypes.add(typePass);
		dataTypes.add(typeRol);
		return dataTypes;
	}

	public DataType getWhereById() {
		StringBuilder where = new StringBuilder(TTWSConstants.WHERE)
				.append(TTWSConstants.COLUMN_USER_ID_USER)
				.append(TTWSConstants.EQUAL)
				.append(TTWSConstants.ONE_WHITE_SPACE);
		DataType idDataType = new DataType(TTWSConstants.INTEGER,
				where.toString(), String.valueOf(getIdUser()));
		return idDataType;
	}

}
