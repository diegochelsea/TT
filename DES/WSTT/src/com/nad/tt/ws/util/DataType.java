package com.nad.tt.ws.util;

public class DataType {

	private String name;
	private String column;
	private String obj;

	public DataType() {
		// TODO Auto-generated constructor stub
	}

	public DataType(String name, String column, String obj) {
		super();
		this.name = name;
		this.column = column;
		this.obj = obj;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
