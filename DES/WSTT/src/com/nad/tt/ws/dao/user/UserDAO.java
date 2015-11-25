package com.nad.tt.ws.dao.user;

import com.nad.tt.ws.dto.user.FolioDTO;
import com.nad.tt.ws.dto.user.RolDTO;
import com.nad.tt.ws.dto.user.UserDTO;

public interface UserDAO {

	
	/**
	 * Method to save some user
	 * @param userDTO object with information of some user
	 * @return int data type with response to action
	 */
	int saveUser(UserDTO userDTO);

	/**
	 * Method to update some user
	 * @param userDTO object with information of some user
	 * @return int data type with response to action
	 */
	int updateUser(UserDTO userDTO);

	/**
	 * Method to delete some user
	 * @param userDTO object with information of some user
	 * @return int data type with response to action
	 */
	int deleteUser(UserDTO userDTO);

	/**
	 * Method to select all stored users
	 * @return array with all users
	 */
	UserDTO[] selectAllUsers();

	/**
	 * Method to select uniq user
	 * @param userDTO object with information of some user
	 * @return user found
	 */
	UserDTO selectUniqUser(UserDTO userDTO);
	
	/**
	 * Method to check login
	 * @param userDTO object with information of some user
	 * @return user found for login action
	 */
	UserDTO logIn(UserDTO userDTO);
	
	/**
	 * Method to save some folio
	 * @param folioDTO object with information of some folio
	 * @return int data type with response to action
	 */
	int saveFolio(FolioDTO folioDTO);

	/**
	 * Method to update some folio
	 * @param folioDTO object with information of some folio
	 * @return int data type with response to action
	 */
	int updateFolio(FolioDTO folioDTO);

	/**
	 * Method to delete some folio
	 * @param folioDTO object with information of some folio
	 * @return int data type with response to action
	 */
	int deleteFolio(FolioDTO folioDTO);
	
	
	/**
	 * Method to select all stored folios for one user
	 * @param userId user id
	 * @return array with all folios
	 */
	FolioDTO[] selectUserFolios(String userId);
	
	/**
	 * Method to select all stored Roles
	 * @return array with all roles
	 */
	RolDTO[] selectRoles();

}
