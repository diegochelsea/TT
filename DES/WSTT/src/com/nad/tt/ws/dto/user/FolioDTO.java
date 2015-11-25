package com.nad.tt.ws.dto.user;

import java.util.ArrayList;
import java.util.List;

import com.nad.tt.ws.util.DataType;
import com.nad.tt.ws.util.TTWSConstants;

public class FolioDTO {

	private String codError;
	private String msgError;
	private int idFolio;
	private String beginning = "";
	private String destination = "";
	private String status = "";
	private String[] users;

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

	public List<DataType> getColumns(boolean idRequired) {
		List<DataType> dataTypes = new ArrayList<>();

		if (idRequired) {
			DataType typeId = new DataType(TTWSConstants.INTEGER,
					TTWSConstants.COLUMN_FOLIO_ID_FOLIO,
					String.valueOf(getIdFolio()));
			dataTypes.add(typeId);
		}
		DataType typeBegin = new DataType(TTWSConstants.STRING,
				TTWSConstants.COLUMN_FOLIO_BEGINNIG, getBeginning());
		DataType typeDestination = new DataType(TTWSConstants.STRING,
				TTWSConstants.COLUMN_FOLIO_DESTINATION, getDestination());
		DataType typeStatus = new DataType(TTWSConstants.STRING,
				TTWSConstants.COLUMN_FOLIO_CURRENT_STATUS, getStatus());

		dataTypes.add(typeBegin);
		dataTypes.add(typeDestination);
		dataTypes.add(typeStatus);
		return dataTypes;
	}

	public DataType getWhereById() {
		StringBuilder where = new StringBuilder(TTWSConstants.WHERE)
				.append(TTWSConstants.COLUMN_FOLIO_ID_FOLIO)
				.append(TTWSConstants.EQUAL)
				.append(TTWSConstants.ONE_WHITE_SPACE);
		DataType idDataType = new DataType(TTWSConstants.INTEGER,
				where.toString(), String.valueOf(getIdFolio()));
		return idDataType;
	}
}
