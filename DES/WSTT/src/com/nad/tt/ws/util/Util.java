package com.nad.tt.ws.util;

import java.util.ArrayList;
import java.util.List;

import com.nad.tt.ws.dto.user.UserDTO;

public final class Util {

	public static String buldSingleQuery(String type, List<DataType> values,
			String table, DataType whereValue) {
		System.out.println("buldSingleQuery init");
		StringBuilder query = new StringBuilder();
		switch (type) {
		case TTWSConstants.INSERT:
			query = getInsert(query, values, table);
			break;
		case TTWSConstants.DELETE:
			query = getDelete(query, table);
			break;
		case TTWSConstants.UPDATE:
			query = getUpdate(query, values, table);
			break;
		case TTWSConstants.SELECT:
			query = getSelect(query, values, table);
			break;
		default:
			break;
		}
		if (!isNull(whereValue)) {
			query.append(whereValue.getColumn());
			query = getByDataType(query, whereValue);
		}
		System.out.println("Query: " + query.toString());
		return query.toString();
	}

	private static StringBuilder getInsert(StringBuilder query,
			List<DataType> values, String table) {
		System.out.println("getInsert init");
		query.append(TTWSConstants.INSERT)
				.append(TTWSConstants.ONE_WHITE_SPACE)
				.append(TTWSConstants.INTO)
				.append(TTWSConstants.ONE_WHITE_SPACE).append(table)
				.append(TTWSConstants.OPEN_PARENTHESIS);
		System.out.println("getInsert query 1: " + query.toString());
		StringBuilder builderValues = new StringBuilder();
		for (int i = 0; i < values.size(); i++) {
			query.append(values.get(i).getColumn());
			if (values.size() - 1 != i) {
				query.append(TTWSConstants.COMMA);
			}
			builderValues = getByDataType(builderValues, values.get(i));
			if (values.size() - 1 != i) {
				builderValues.append(TTWSConstants.COMMA);
			}
		}
		System.out.println("getInsert query 2: " + query.toString());

		query.append(TTWSConstants.CLOSING_PARENTHESIS)
				.append(TTWSConstants.VALUES)
				.append(TTWSConstants.OPEN_PARENTHESIS);

		query.append(builderValues.toString()).append(
				TTWSConstants.CLOSING_PARENTHESIS);
		System.out.println("getInsert query 3: " + query.toString());
		return query;
	}

	private static StringBuilder getUpdate(StringBuilder query,
			List<DataType> values, String table) {
		query.append(TTWSConstants.UPDATE)
				.append(TTWSConstants.ONE_WHITE_SPACE).append(table)
				.append(TTWSConstants.ONE_WHITE_SPACE)
				.append(TTWSConstants.SET)
				.append(TTWSConstants.ONE_WHITE_SPACE);
		for (int i = 0; i < values.size(); i++) {
			query.append(values.get(i).getColumn()).append(TTWSConstants.EQUAL);
			query = getByDataType(query, values.get(i));
			if (values.size() - 1 != i) {
				query.append(TTWSConstants.COMMA);
			}
		}
		return query;
	}

	private static StringBuilder getSelect(StringBuilder query,
			List<DataType> values, String table) {
		query.append(TTWSConstants.SELECT)
				.append(TTWSConstants.ONE_WHITE_SPACE);
		System.out.println("getSelect 1: " + query.toString());
		for (int i = 0; i < values.size(); i++) {
			query.append(values.get(i).getColumn());
			if (values.size() - 1 != i) {
				query.append(TTWSConstants.COMMA);
			}
		}
		System.out.println("getSelect 2: " + query.toString());
		query.append(TTWSConstants.FROM).append(table);
		System.out.println("getSelect 3: " + query.toString());
		return query;
	}

	private static StringBuilder getDelete(StringBuilder query, String table) {
		query.append(TTWSConstants.DELETE)
				.append(TTWSConstants.FROM).append(table);
		return query;
	}

	private static StringBuilder getByDataType(StringBuilder query,
			DataType dataTypeEnum) {
		switch (dataTypeEnum.getName()) {
		case TTWSConstants.INTEGER:
			query.append(Integer.valueOf(dataTypeEnum.getObj().toString()));
			break;
		case TTWSConstants.BOOLEAN:
			query.append(Boolean.valueOf(dataTypeEnum.getObj().toString()));
			break;
		case TTWSConstants.DATE:
			query.append(dataTypeEnum.getObj());
			break;
		case TTWSConstants.DECIMAL:
			query.append(Double.valueOf(dataTypeEnum.getObj()));
			break;
		case TTWSConstants.STRING:
			query.append(TTWSConstants.QUOTATION_MARK)
					.append(dataTypeEnum.getObj().toString())
					.append(TTWSConstants.QUOTATION_MARK);
			break;
		default:
			break;
		}
		return query;
	}

	public static boolean isEmptyString(String value) {
		return value == null || value.isEmpty();
	}

	public static boolean isNull(Object value) {
		return value == null;
	}
	
	
	public static List<DataType> getColumns(boolean idRequired, UserDTO dto) {
		List<DataType> dataTypes = new ArrayList<>();

		if (idRequired) {
			DataType typeId = new DataType(TTWSConstants.INTEGER,
					TTWSConstants.COLUMN_USER_ID_USER,
					String.valueOf(dto.getIdUser()));
			dataTypes.add(typeId);
		}
		DataType typeName = new DataType(TTWSConstants.STRING,
				TTWSConstants.COLUMN_USER_NAME, dto.getName());
		DataType typeLastName = new DataType(TTWSConstants.STRING,
				TTWSConstants.COLUMN_USER_LAST_NAME, dto.getLastName());
		DataType typeEmail = new DataType(TTWSConstants.STRING,
				TTWSConstants.COLUMN_USER_EMAIL, dto.getEmail());
		DataType typePass = new DataType(TTWSConstants.STRING,
				TTWSConstants.COLUMN_USER_PASS, dto.getPassword());
		DataType typeRol = new DataType(TTWSConstants.INTEGER,
				TTWSConstants.COLUMN_USER_ROL, String.valueOf(dto.getRol()));

		dataTypes.add(typeName);
		dataTypes.add(typeLastName);
		dataTypes.add(typeEmail);
		dataTypes.add(typePass);
		dataTypes.add(typeRol);
		return dataTypes;
	}

	public static DataType getWhereById(UserDTO dto) {
		StringBuilder where = new StringBuilder(TTWSConstants.WHERE)
				.append(TTWSConstants.COLUMN_USER_ID_USER)
				.append(TTWSConstants.EQUAL)
				.append(TTWSConstants.ONE_WHITE_SPACE);
		DataType idDataType = new DataType(TTWSConstants.INTEGER,
				where.toString(), String.valueOf(dto.getIdUser()));
		return idDataType;
	}
}
