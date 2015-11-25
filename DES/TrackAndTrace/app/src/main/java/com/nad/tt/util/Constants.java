package com.nad.tt.util;

/**
 * Created by Diego on 22/10/2015.
 */
public final class Constants {

    public static final String EMPTY_STRING  = "";

    public static final int ZERO = 0;


    public static final String  THE  = "The ";
    public static final String  FIELD_INVALID_FORMAT  = " field has an invalid format.";
    public static final String  FIELD_REQUIRED  = " field is required.";


    // Constants to save regexp
    public static final String REGEXP_STATUS_DESC  = "^[A-ZÑ]{1}[a-zÑáéíóú]{1,15}$";
    public static final String REGEXP_FOLIO_FOLIO  = "^[0-9]{2,6}$";

    public static final String REGEXP_NAME_USER  = "^[A-ZÑ]{1}[a-zÑáéíóú]{1,15}$";
    public static final String REGEXP_LAST_NAME_USER  = "^[A-ZÑ]{1}[a-zÑáéíóú]{1,15}$";
//    public static final String REGEXP_EMAIL = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$";
//    public static final String REGEXP_PASS = "^(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    public static final String REGEXP_EMAIL = "^[a-z]{5,10}$";
    public static final String REGEXP_PASS = "^[a-z0-9]{5,10}$";

    // Constants to save strings logs
    public static final String LOG_NAD  = "LOG-NAD";

    // Constants to folio
    public static final String REGEXP_FOLIO = "^[0-9]{7}$";

    public static final String REGEXP_ORIGIN = "^[A-Z]{1}[a-záéíóúñ]{2,}([\\s][A-Z]{1}[a-záéíóúñ]{2,})*$";
    public static final String REGEXP_DESTINATION = "^[A-Z]{1}[a-záéíóúñ]{2,}([\\s][A-Z]{1}[a-záéíóúñ]{2,})*$";

    // Constants to error
    public static final String ERROR_CODE_INVALID_FORMAT  = "E001";
    public static final String ERROR_CODE_REQUIRED  = "E002";
    public static final String ERROR_CODE_OK  = "OK";
    public static final String ERROR_CODE_NOK  = "NOK";


    // Costants to get connection to web service
//    public static final String URL = "http://10.42.1.152:11107/WSTT/services/UserDAOImpl";
//    public static final String URL = "http://192.168.1.1:11107/WSTT/services/UserDAOImpl";
    public static final String URL = "http://192.168.0.4:11107/WSTT/services/UserDAOImpl";
    public static final String NAME_SAPACE = "http://user.impl.dao.ws.tt.nad.com";

    // Constants to save methods name from WS
    public static final String METHOD_SAVE_USER = "saveUser";
    public static final String METHOD_UPDATE_USER = "updateUser";
    public static final String METHOD_DELETE_USER = "deleteUser";
    public static final String METHOD_SAVE_FOLIO = "saveFolio";
    public static final String METHOD_UPDATE_FOLIO = "updateFolio";
    public static final String METHOD_DELETE_FOLIO = "deleteFolio";
    public static final String METHOD_LOGIN = "logIn";
    public static final String METHOD_SELECT_ALL_USERS = "selectAllUsers";
    public static final String METHOD_SELECT_UNIQ_USER = "selectUniqUser";
    public static final String METHOD_SELECT_USER_FOLIOS = "selectUserFolios";
    public static final String METHOD_SELECT_ROLES = "selectRoles";

    //Costants to set an action
    public static final String INSERT = "logIn";
    public static final int SOAP_OBJECT = 1;
    public static final int SOAP_PRIMITIVE = 2;
    public static final String STATUS = "Status ";
    public static final String INSERTED = "inserted";
    public static final String ERROR = "Error ocurred";

    //Constants to save parameter name of methods from Web Service
    public static final String USER_DTO = "userDTO";
    public static final String STATUS_DTO = "statusDTO";
    public static final String FOLIO_DTO = "folioDTO";
    public static final String FOLLOWUP_DTO = "followupDTO";

    //Constants to save dto class name
    public static final String CLASS_USER_DTO = "UserDTO";
    public static final String CLASS_STATUS_DTO = "StatusDTO";
    public static final String CLASS_FOLIO_DTO = "FolioDTO";
    public static final String CLASS_FOLLOWUP_DTO = "FollowupDTO";
    
    //Constants to status
    public static final String NA = "na";
    public static final String PICKUP = "pu";
    public static final String CHEKIN = "ch";
    public static final String ONFLY = "onf";
    public static final String CUSTOMC = "cc";
    public static final String DELIVERED = "del";

    //Constants to save activity class name
    public static final String CLASS_USER_ACTIVITY = "UserActivity";
}
