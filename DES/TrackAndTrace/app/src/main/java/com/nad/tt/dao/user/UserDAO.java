package com.nad.tt.dao.user;

import android.util.Log;

import com.nad.tt.comun.dto.BeanResponseDTO;
import com.nad.tt.comun.dto.RolDTO;
import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.WsConection;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Diego on 23/10/2015.
 */
public class UserDAO {

    private static final String COD_ERROR = "codError";
    private static final String MSG_ERROR = "msgError";
    private static final String ID_USER = "idUser";
    private static final String NAME = "name";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASS = "password";
    private static final String ROL = "rol";
    private static final String FOLIOS = "folios";

    public UserDTO login(UserDTO responseDTO) {
        SoapObject soapObjectResult;

        try {
            soapObjectResult = WsConection.getSimpleObjectWithPatam(Constants.METHOD_LOGIN, responseDTO);
            responseDTO.idUser = (Integer.valueOf(soapObjectResult.getProperty(ID_USER).toString()));
            Log.d("WEB-SERVICE log2", String.valueOf(responseDTO.idUser));
            responseDTO.codError = (String.valueOf(soapObjectResult.getProperty(COD_ERROR)));
            Log.d("WEB-SERVICE", "login: 2");
            responseDTO.msgError = (String.valueOf(soapObjectResult.getProperty(MSG_ERROR)));
            Log.d("WEB-SERVICE", "login: 3");
            responseDTO.name = (String.valueOf(soapObjectResult.getProperty(NAME)));
            Log.d("WEB-SERVICE", "login: 4");
            responseDTO.lastName = (String.valueOf(soapObjectResult.getProperty(LAST_NAME)));
            Log.d("WEB-SERVICE", "login: 5");
            responseDTO.email = (String.valueOf(soapObjectResult.getProperty(EMAIL)));
            Log.d("WEB-SERVICE", "login: 6");
            responseDTO.password = (String.valueOf(soapObjectResult.getProperty(PASS)));
            Log.d("WEB-SERVICE", "login: 7");
            responseDTO.rol = (Integer.valueOf(soapObjectResult.getProperty(ROL).toString()));
            Log.d("WEB-SERVICE", "login: 8");

            Log.d("WEB-SERVICE, response: ", responseDTO.codError + ", " + responseDTO.msgError);
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
        }
        return responseDTO;
    }


    public List<RolDTO> getAllRoles() throws Exception {
        Vector<SoapObject> soapObjectResult = null;
        List<RolDTO> result = new ArrayList<>();
        try {
            soapObjectResult = WsConection.getSimpleObject(Constants.METHOD_SELECT_ROLES, false);
            for (SoapObject object : soapObjectResult) {
                result.add(new RolDTO(Integer.valueOf(object.getProperty("idRol").toString()), object.getProperty("desc").toString()));
            }
            Log.d("getAllRoles", "");
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
            throw  new Exception(e.getMessage());
        }
        return result;
    }

    public List<UserDTO> getAllUsers() throws Exception {
        Vector<SoapObject> soapObjectResult = null;
        List<UserDTO> result = new ArrayList<>();
        Log.d("getAllUsers", "init");
        try {
            soapObjectResult = WsConection.getSimpleObject(Constants.METHOD_SELECT_ALL_USERS, false);
            UserDTO userDTO = null;
            for (SoapObject object : soapObjectResult) {
                userDTO = new UserDTO();
                userDTO.idUser = Integer.valueOf(object.getProperty("idUser").toString());
                userDTO.name = object.getProperty("name").toString();
                userDTO.lastName = object.getProperty("idUser").toString();
                userDTO.email = object.getProperty("email").toString();
                userDTO.password = object.getProperty("password").toString();
                userDTO.rol = Integer.valueOf(object.getProperty("rol").toString());
                result.add(userDTO);
            }
            Log.d("getAllUsers", "");
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
            throw  new Exception(e.getMessage());
        }
        return result;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        int result = 0;
        userDTO.codError = Constants.ERROR_CODE_NOK;
        userDTO.msgError = "¡Error: could not save the user, try again later!";
        try {
            result = WsConection.execStatement(userDTO, Constants.METHOD_SAVE_USER, Constants.USER_DTO);
            if (result > 0){
                userDTO.codError = Constants.ERROR_CODE_OK;
                userDTO.msgError = "¡User saved successfully!";
            }
            Log.d("saveUser", "");
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
        }
        return userDTO;
    }

    public UserDTO deleteUser(UserDTO userDTO) {
        int result = 0;
        Log.d(Constants.LOG_NAD, "Params: " + userDTO.idUser + ", " + userDTO.name);
        userDTO.codError = Constants.ERROR_CODE_NOK;
        userDTO.msgError = "¡Error: could not delete the user, try again later!";
        try {
            result = WsConection.execStatement(userDTO, Constants.METHOD_DELETE_USER, Constants.USER_DTO);
            if (result > 0){
                userDTO.codError = Constants.ERROR_CODE_OK;
                userDTO.msgError = "¡User deleted successfully!";
            }
            Log.d(Constants.LOG_NAD, "deleteUser");
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
        }
        return userDTO;
    }

    public UserDTO getUniqUser(UserDTO userDTO) {
        SoapObject soapObjectResult;

        try {
            soapObjectResult = WsConection.getSimpleObjectWithPatam(Constants.METHOD_SELECT_UNIQ_USER, userDTO);
            userDTO.idUser = (Integer.valueOf(soapObjectResult.getProperty(ID_USER).toString()));
            Log.d(Constants.LOG_NAD, "getUniqUser: 1");
            userDTO.codError = (String.valueOf(soapObjectResult.getProperty(COD_ERROR)));
            Log.d("WEB-SERVICE", "getUniqUser: 2");
            userDTO.msgError = (String.valueOf(soapObjectResult.getProperty(MSG_ERROR)));
            Log.d("WEB-SERVICE", "getUniqUser: 3");
            userDTO.name = (String.valueOf(soapObjectResult.getProperty(NAME)));
            Log.d("WEB-SERVICE", "getUniqUser: 4");
            userDTO.lastName = (String.valueOf(soapObjectResult.getProperty(LAST_NAME)));
            Log.d("WEB-SERVICE", "getUniqUser: 5");
            userDTO.email = (String.valueOf(soapObjectResult.getProperty(EMAIL)));
            Log.d("WEB-SERVICE", "getUniqUser: 6");
            userDTO.password = (String.valueOf(soapObjectResult.getProperty(PASS)));
            Log.d("WEB-SERVICE", "getUniqUser: 7");
            userDTO.rol = (Integer.valueOf(soapObjectResult.getProperty(ROL).toString()));
            Log.d("WEB-SERVICE", "getUniqUser: 8");

            Log.d("WEB-SERVICE, response: ", userDTO.codError + ", " + userDTO.msgError);
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
        }
        return userDTO;
    }
}
