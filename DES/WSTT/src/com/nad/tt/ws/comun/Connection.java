package com.nad.tt.ws.comun;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nad.tt.ws.comun.dto.BeanResponseDTO;
import com.nad.tt.ws.util.TTWSConstants;
import com.nad.tt.ws.util.Util;

public final class Connection {

	private static java.sql.Connection connection = null;

	private Connection() {
	}

	public static BeanResponseDTO<java.sql.Connection> getConnection() {
		BeanResponseDTO<java.sql.Connection> result = new BeanResponseDTO<>(
				TTWSConstants.ERROR_CODE_NOK, TTWSConstants.EMPTY_STRING, null);
		System.out.println("getConnection init");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// DataSource ds = (DataSource) new InitialContext()
			// .lookup("jdbcTTWS");
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost/ttws?"
							+ "user=root&password=admin");

			// connection = (java.sql.Connection) ds.getConnection();
			System.out.println("getConnection getConn");
			result.setCodError(TTWSConstants.ERROR_CODE_OK);
			result.setResult(connection);
		} catch (/* NamingException | */SQLException | InstantiationException
				| IllegalAccessException | ClassNotFoundException e) {
			result.setMsgError(e.getMessage());
			System.out.println("getConnection error: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public static void closeConn(java.sql.Connection connection,
			Statement statement, ResultSet resultSet) throws SQLException {
		if (!Util.isNull(connection) && !connection.isClosed()) {
			connection.close();
		}
		if (!Util.isNull(statement) && !statement.isClosed()) {
			statement.close();
		}
		if (!Util.isNull(resultSet) && !resultSet.isClosed()) {
			resultSet.close();
		}
	}

	public static int execQuery(String query) {
		System.out.println("execQuery inti");

		BeanResponseDTO<java.sql.Connection> resultConn = getConnection();
		int result = (TTWSConstants.ERROR_CODE_OK.equals(resultConn
				.getCodError()) ? 1 : 0);
		Statement stmt = null;
		if (TTWSConstants.ERROR_CODE_OK.equals(resultConn.getCodError())) {
			try {
				stmt = resultConn.getResult().createStatement();
				// Either (1) the row count for SQL Data Manipulation Language
				// (DML) statements
				// Or (2) 0 for SQL statements that return nothing
				System.out.println("execQuery query: " + query);
				result = stmt.executeUpdate(query);
			} catch (SQLException e) {
				result = 0;
				e.printStackTrace();
			} finally {

				try {
					closeConn(resultConn.getResult(), stmt, null);
				} catch (SQLException e) {
					result = 0;
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
