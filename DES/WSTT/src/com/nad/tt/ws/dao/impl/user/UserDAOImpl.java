package com.nad.tt.ws.dao.impl.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.nad.tt.ws.comun.Connection;
import com.nad.tt.ws.comun.dto.BeanResponseDTO;
import com.nad.tt.ws.dao.user.UserDAO;
import com.nad.tt.ws.dto.user.FolioDTO;
import com.nad.tt.ws.dto.user.RolDTO;
import com.nad.tt.ws.dto.user.UserDTO;
import com.nad.tt.ws.util.TTWSConstants;
import com.nad.tt.ws.util.Util;

/**
 * Class that implements the logic of the UserDAO interface for accessing
 * application data.
 * 
 * @author Mauricio Hernandez Araiza
 * 
 */
public class UserDAOImpl implements UserDAO {

	/** Constant to user table */
	private static final String USER_TABLE_NAME = "USER";
	
	/** Constant to folio table */
	private static final String FOLIO_TABLE_NAME = "FOLIO";
	
	/** Constant to rol table */
	private static final String ROL_TABLE_NAME = "ROL";
	
	/** Constant to followup table */
	private static final String FOLLOWUP_TABLE_NAME = "FOLLOWUP";
	
	/** Constant to save query LOGIN */
	private static final StringBuilder LOGIN = new StringBuilder(TTWSConstants.SELECT)
			.append(" CASE WHEN (SELECT COUNT(EMAIL)")
			.append(" FROM USER WHERE EMAIL = ?) = 0 THEN -1")
			.append(" WHEN (SELECT COUNT(EMAIL) FROM USER WHERE EMAIL = ?) = 1")
			.append(" AND PASS = ? THEN (SELECT ID_USER FROM USER WHERE EMAIL = ? AND PASS = ?)")
			.append(" ELSE 0 END login").append(TTWSConstants.FROM).append(USER_TABLE_NAME);
	
	
	/** Constant to save query UNIQ_USER */
	private static final StringBuilder UNIQ_USER = new StringBuilder(TTWSConstants.SELECT).append(TTWSConstants.ONE_WHITE_SPACE)
			.append(TTWSConstants.COLUMN_USER_NAME).append(TTWSConstants.COMMA)
			.append(TTWSConstants.COLUMN_USER_LAST_NAME).append(TTWSConstants.COMMA)
			.append(TTWSConstants.COLUMN_USER_EMAIL).append(TTWSConstants.COMMA)
			.append(TTWSConstants.COLUMN_USER_PASS).append(TTWSConstants.COMMA)
			.append(TTWSConstants.COLUMN_USER_ROL).append(TTWSConstants.FROM)
			.append(USER_TABLE_NAME).append(TTWSConstants.WHERE).append(TTWSConstants.COLUMN_USER_ID_USER)
			.append(TTWSConstants.EQUAL).append("?");

	/** Constant to save query ALL_ROLES */
	private static final StringBuilder ALL_ROLES = new StringBuilder(TTWSConstants.SELECT)
			.append(TTWSConstants.ONE_WHITE_SPACE).append(TTWSConstants.COLUMN_ROL_ID_ROL)
			.append(TTWSConstants.COMMA).append(TTWSConstants.COLUMN_ROL_DESC_ROL)
			.append(TTWSConstants.FROM).append(ROL_TABLE_NAME);
	
	/** Constant to save query FOLIOS_FOR_USER */
	private static final StringBuilder FOLIOS_FOR_USER = new StringBuilder(TTWSConstants.SELECT)
			.append(" A.").append(TTWSConstants.COLUMN_FOLIO_ID_FOLIO).append(TTWSConstants.COMMA)
			.append("A.").append(TTWSConstants.COLUMN_FOLIO_BEGINNIG).append(TTWSConstants.COMMA)
			.append("A.").append(TTWSConstants.COLUMN_FOLIO_DESTINATION).append(TTWSConstants.COMMA)
			.append("A.").append(TTWSConstants.COLUMN_FOLIO_CURRENT_STATUS)
			.append(TTWSConstants.FROM).append(FOLIO_TABLE_NAME).append(" A")
			.append(TTWSConstants.INNER_JOIN).append(FOLLOWUP_TABLE_NAME).append(" B").append(TTWSConstants.ON)
			.append("A.").append(TTWSConstants.COLUMN_FOLIO_ID_FOLIO).append(TTWSConstants.EQUAL)
			.append("B.").append(TTWSConstants.COLUMN_FOLIO_ID_FOLIO).append(TTWSConstants.CLOSING_PARENTHESIS)
			.append(TTWSConstants.ONE_WHITE_SPACE)
			.append(TTWSConstants.INNER_JOIN).append(USER_TABLE_NAME).append(" C").append(TTWSConstants.ON)
			.append("B.").append(TTWSConstants.COLUMN_USER_ID_USER).append(TTWSConstants.EQUAL)
			.append("C.").append(TTWSConstants.COLUMN_USER_ID_USER).append(TTWSConstants.CLOSING_PARENTHESIS)
			.append(TTWSConstants.WHERE).append("C.").append(TTWSConstants.COLUMN_USER_ID_USER)
			.append(TTWSConstants.EQUAL).append("?");
	
	/** Constant to save query ALL_USERS */
	private static final StringBuilder ALL_USERS = new StringBuilder(TTWSConstants.SELECT)
			.append(TTWSConstants.ONE_WHITE_SPACE).append(TTWSConstants.COLUMN_USER_ID_USER)
			.append(TTWSConstants.COMMA).append(TTWSConstants.COLUMN_USER_NAME)
			.append(TTWSConstants.COMMA).append(TTWSConstants.COLUMN_USER_LAST_NAME)
			.append(TTWSConstants.COMMA).append(TTWSConstants.COLUMN_USER_EMAIL)
			.append(TTWSConstants.COMMA).append(TTWSConstants.COLUMN_USER_PASS)
			.append(TTWSConstants.COMMA).append(TTWSConstants.COLUMN_USER_ROL)
			.append(TTWSConstants.FROM).append(USER_TABLE_NAME);
	
	@Override
	public int saveUser(UserDTO userDTO) {
		System.out.println("dao saveUser");
		return Connection.execQuery(Util.buldSingleQuery(TTWSConstants.INSERT,
				userDTO.getColumns(false), USER_TABLE_NAME, null));
	}

	@Override
	public int updateUser(UserDTO userDTO) {
		System.out.println("dao updateUser");
		return Connection.execQuery(Util.buldSingleQuery(TTWSConstants.UPDATE,
				userDTO.getColumns(false), USER_TABLE_NAME, userDTO.getWhereById()));
	}

	@Override
	public int deleteUser(UserDTO userDTO) {
		System.out.println("dao deleteUser");
		return Connection.execQuery(Util.buldSingleQuery(TTWSConstants.DELETE,
				null, USER_TABLE_NAME, userDTO.getWhereById()));
	}

	@Override
	public UserDTO[] selectAllUsers() {
		System.out.println("selectAllUsers int");
		BeanResponseDTO<java.sql.Connection> resultConn = Connection
				.getConnection();

		List<UserDTO> list = new ArrayList<>();
		UserDTO[] result = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		System.out.println("selectAllUsers getConn: " + resultConn.getCodError());
		try {
			if (TTWSConstants.ERROR_CODE_OK.equals(resultConn.getCodError())) {

				stmt = (PreparedStatement) resultConn.getResult()
						.prepareStatement(ALL_USERS.toString());
				System.out.println("selectAllUsers, query: " + ALL_USERS.toString());
				resultSet = stmt.executeQuery();
				if (resultConn != null && !resultConn.getResult().isClosed()) {
					while (!resultConn.getResult().isClosed()
							&& resultSet.next()) {
						list.add(new UserDTO(
								resultSet.getInt(TTWSConstants.COLUMN_USER_ID_USER),
								resultSet.getString(TTWSConstants.COLUMN_USER_NAME),
								resultSet.getString(TTWSConstants.COLUMN_USER_LAST_NAME),
								resultSet.getString(TTWSConstants.COLUMN_USER_EMAIL),
								resultSet.getString(TTWSConstants.COLUMN_USER_PASS),
								resultSet.getInt(TTWSConstants.COLUMN_USER_ROL),
								new String[]{}
								));
						System.out.println("selectAllUsers: OK");
					}
					result = list.toArray(new UserDTO[list.size()]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				Connection.closeConn(resultConn.getResult(), stmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end selectAllUsers");
		return result;
	}

	@Override
	public UserDTO selectUniqUser(UserDTO userDTO) {
		System.out.println("int selectUniqUser");
		System.out.println("selectUniqUser: params, " + userDTO.getName() + ", " + userDTO.getLastName()
				+ ", " + userDTO.getEmail()
				+ ", " + userDTO.getPassword()
				+ ", " + userDTO.getRol());
		BeanResponseDTO<java.sql.Connection> resultConn = Connection
				.getConnection();

		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		System.out.println("selectUniqUser getConn: " + resultConn.getCodError());
		try {
			if (TTWSConstants.ERROR_CODE_OK.equals(resultConn.getCodError())) {

				stmt = (PreparedStatement) resultConn.getResult()
						.prepareStatement(UNIQ_USER.toString());
				stmt.setInt(1, userDTO.getIdUser());
				System.out.println("selectUniqUser query: " + UNIQ_USER.toString());
				resultSet = stmt.executeQuery();
				if (!resultConn.getResult().isClosed() && resultSet.next()) {
					userDTO.setName(resultSet.getString(TTWSConstants.COLUMN_USER_NAME));
					userDTO.setLastName(resultSet.getString(TTWSConstants.COLUMN_USER_LAST_NAME));
					userDTO.setEmail(resultSet.getString(TTWSConstants.COLUMN_USER_EMAIL));
					userDTO.setPassword(resultSet.getString(TTWSConstants.COLUMN_USER_PASS));
					userDTO.setRol(resultSet.getInt(TTWSConstants.COLUMN_USER_ROL));
					resultConn.setCodError(TTWSConstants.ERROR_CODE_OK);
					System.out.println("selectUniqUser: OK, " + userDTO.getName() + ", " + userDTO.getLastName()
							+ ", " + userDTO.getEmail()
							+ ", " + userDTO.getPassword()
							+ ", " + userDTO.getRol());
				} else {
					resultConn.setCodError(TTWSConstants.ERROR_CODE_NOK);
					resultConn.setMsgError("User not found.");
				}
			}
			userDTO.setCodError(resultConn.getCodError());
			userDTO.setMsgError(resultConn.getMsgError());
		} catch (SQLException e) {
			userDTO.setCodError(TTWSConstants.ERROR_CODE_NOK);
			userDTO.setMsgError(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				Connection.closeConn(resultConn.getResult(), stmt, resultSet);
			} catch (SQLException e) {
				userDTO.setCodError(TTWSConstants.ERROR_CODE_NOK);
				userDTO.setMsgError(e.getMessage());
				e.printStackTrace();
			}
		}
		System.out.println("end selectUniqUser");
		return userDTO;
	}

	@Override
	public UserDTO logIn(UserDTO userDTO) {
		System.out.println("int logIn, param: " + ((userDTO == null) ? "null" : "not null"));
		BeanResponseDTO<java.sql.Connection> resultConn = Connection
				.getConnection();

		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		System.out.println("logIn getConn: " + resultConn.getCodError());
		try {
			if (TTWSConstants.ERROR_CODE_OK.equals(resultConn.getCodError())) {

				stmt = (PreparedStatement) resultConn.getResult()
						.prepareStatement(LOGIN.toString());
				System.out.println("logIn, query: " + LOGIN.toString());
				stmt.setString(1, userDTO.getEmail());
				stmt.setString(2, userDTO.getEmail());
				stmt.setString(3, userDTO.getPassword());
				stmt.setString(4, userDTO.getEmail());
				stmt.setString(5, userDTO.getPassword());
				System.out.println("logIn, params: " + userDTO.getEmail() + ", " + userDTO.getPassword());

				resultSet = stmt.executeQuery();
				if (!resultConn.getResult().isClosed() && resultSet.next()) {
					int loginRes = resultSet.getInt("login");
					if (loginRes == -1) {
						resultConn.setCodError(TTWSConstants.ERROR_CODE_NOK);
						resultConn.setMsgError("El usuario no existe.");
					} else if (loginRes == 0) {
						resultConn.setCodError(TTWSConstants.ERROR_CODE_NOK);
						resultConn.setMsgError("El password es incorrecto");
					} else {
						resultConn.setCodError(TTWSConstants.ERROR_CODE_OK);
						resultConn.setMsgError("Bienvenido!");
						userDTO.setIdUser(resultSet.getInt("login"));
					}
					System.out.println("Login: " + resultSet.getInt("login"));
				} else {
					resultConn.setMsgError("El usuario no existe.");
					resultConn.setCodError(TTWSConstants.ERROR_CODE_NOK);
				}
			}
			userDTO.setCodError(resultConn.getCodError());
			userDTO.setMsgError(resultConn.getMsgError());
		} catch (SQLException e) {
			userDTO.setCodError(TTWSConstants.ERROR_CODE_NOK);
			userDTO.setMsgError(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				Connection.closeConn(resultConn.getResult(), stmt, resultSet);
			} catch (SQLException e) {
				userDTO.setCodError(TTWSConstants.ERROR_CODE_NOK);
				userDTO.setMsgError(e.getMessage());
				e.printStackTrace();
			}
		}
		System.out.println("end logIn");
		return userDTO;
	}

	@Override
	public int saveFolio(FolioDTO folioDTO) {
		System.out.println("dao saveFolio");
		return Connection.execQuery(Util.buldSingleQuery(TTWSConstants.INSERT,
				folioDTO.getColumns(true), FOLIO_TABLE_NAME, null));
	}

	@Override
	public int updateFolio(FolioDTO folioDTO) {
		System.out.println("dao updateFolio");
		return Connection.execQuery(Util.buldSingleQuery(TTWSConstants.UPDATE,
				folioDTO.getColumns(false), FOLIO_TABLE_NAME, folioDTO.getWhereById()));
	}

	@Override
	public int deleteFolio(FolioDTO folioDTO) {
		System.out.println("dao deleteFolio");
		return Connection.execQuery(Util.buldSingleQuery(TTWSConstants.DELETE,
				null, FOLIO_TABLE_NAME, folioDTO.getWhereById()));
	}

	@Override
	public FolioDTO[] selectUserFolios(String userId) {
		System.out.println("selectUserFolios init");
		BeanResponseDTO<java.sql.Connection> resultConn = Connection
				.getConnection();

		List<FolioDTO> list = new ArrayList<>();
		FolioDTO[] result = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		System.out.println("selectUserFolios getConn: " + resultConn.getCodError());
		try {
			if (TTWSConstants.ERROR_CODE_OK.equals(resultConn.getCodError())) {

				stmt = (PreparedStatement) resultConn.getResult()
						.prepareStatement(FOLIOS_FOR_USER.toString());
				System.out.println("selectUserFolios query: " + FOLIOS_FOR_USER.toString());
				stmt.setString(1, userId);
				resultSet = stmt.executeQuery();
				if (resultConn != null && !resultConn.getResult().isClosed()) {
					while (!resultConn.getResult().isClosed()
							&& resultSet.next()) {
						list.add(new FolioDTO(
								resultSet.getInt(TTWSConstants.COLUMN_FOLIO_ID_FOLIO),
								resultSet.getString(TTWSConstants.COLUMN_FOLIO_BEGINNIG),
								resultSet.getString(TTWSConstants.COLUMN_FOLIO_DESTINATION),
								resultSet.getString(TTWSConstants.COLUMN_FOLIO_CURRENT_STATUS)));
						System.out.println("selectUserFolios: OK");
					}
					result = list.toArray(new FolioDTO[list.size()]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				Connection.closeConn(resultConn.getResult(), stmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end selectUserFolios");
		return result;
	}

	@Override
	public RolDTO[] selectRoles() {
		System.out.println("selectRoles int");
		BeanResponseDTO<java.sql.Connection> resultConn = Connection
				.getConnection();

		List<RolDTO> list = new ArrayList<>();
		RolDTO[] result = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		System.out.println("selectRoles getConn: " + resultConn.getCodError());
		try {
			if (TTWSConstants.ERROR_CODE_OK.equals(resultConn.getCodError())) {

				stmt = (PreparedStatement) resultConn.getResult()
						.prepareStatement(ALL_ROLES.toString());
				resultSet = stmt.executeQuery();
				if (resultConn != null && !resultConn.getResult().isClosed()) {
					while (!resultConn.getResult().isClosed()
							&& resultSet.next()) {
						list.add(new RolDTO(
								resultSet.getInt(TTWSConstants.COLUMN_ROL_ID_ROL),
								resultSet.getString(TTWSConstants.COLUMN_ROL_DESC_ROL)));
						System.out.println("selectRoles: OK");
					}
					result = list.toArray(new RolDTO[list.size()]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				Connection.closeConn(resultConn.getResult(), stmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end selectRoles");
		return result;

	}
}
