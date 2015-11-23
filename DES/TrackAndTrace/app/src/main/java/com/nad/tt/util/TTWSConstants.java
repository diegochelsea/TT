package com.nad.tt.util;

public final class TTWSConstants {

	/** Constants to error codes */
	public static final String ERROR_CODE_OK = "OK";
	public static final String ERROR_CODE_NOK = "NOK";
	public static final String EMPTY_STRING = "";

	/** Constants to build queries */
	public static final String INSERT = "INSERT";
	public static final String DELETE = "DELETE";
	public static final String UPDATE = "UPDATE";
	public static final String SELECT = "SELECT";
	public static final String ONE_WHITE_SPACE = " ";
	public static final String FROM = " FROM ";
	public static final String WHERE = " WHERE ";
	public static final String SET = "SET";
	public static final String INTO = "INTO";
	public static final String VALUES = "VALUES";
	public static final String EQUAL = " = ";
	public static final String OPEN_PARENTHESIS = "(";
	public static final String CLOSING_PARENTHESIS = ")";
	public static final String COMMA = ",";
	public static final String QUOTATION_MARK = "\"";
	public static final String INNER_JOIN = " INNER JOIN ";
	public static final String ON = " ON (";

	/** Constants to data types */
	public static final String INTEGER = "INTEGER";
	public static final String DATE = "DATE";
	public static final String BOOLEAN = "BOOLEAN";
	public static final String STRING = "STRING";
	public static final String DECIMAL = "DECIMAL";

	/** Constants to user columns name */
	public static final String COLUMN_USER_ID_USER = "ID_USER";
	public static final String COLUMN_USER_NAME = "NAME";
	public static final String COLUMN_USER_LAST_NAME = "LAST_NAME";
	public static final String COLUMN_USER_EMAIL = "EMAIL";
	public static final String COLUMN_USER_PASS = "PASS";
	public static final String COLUMN_USER_ROL = "ID_ROL";

	/** Constants to folio columns name */
	public static final String COLUMN_FOLIO_ID_FOLIO = "ID_FOLIO";
	public static final String COLUMN_FOLIO_BEGINNIG = "BEGINNIG";
	public static final String COLUMN_FOLIO_DESTINATION = "DESTINATION";
	public static final String COLUMN_FOLIO_CURRENT_STATUS = "CURRENT_STATUS";

	/** Constants to rol columns name */
	public static final String COLUMN_ROL_ID_ROL = "ID_ROL";
	public static final String COLUMN_ROL_DESC_ROL = "DESC_ROL";
}
