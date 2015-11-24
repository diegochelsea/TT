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

    public static final String REGEXP_NAME_USER  = "^[A-ZÑ]{1}[a-zÑáéíóú]{1,15}$";
    public static final String REGEXP_LAST_NAME_USER  = "^[A-ZÑ]{1}[a-zÑáéíóú]{1,15}$";
    public static final String REGEXP_EMAIL = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$";
    public static final String REGEXP_PASS = "^(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

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


    // Costants to get connection to web service
    public static final String URL = "http://localhost:8989/ttws/user?wsdl";
    public static final String NAME_SAPACE = "http://impl.dao.user.tt.nad.com/";
    public static final String SOAP_ACTION_PREFIX = "/";
    public static final String METHOD = "saveUser";

    //Costants to set an action
    public static final String INSERT = "logIn";
    public static final int SOAP_OBJECT = 1;
    public static final int SOAP_PRIMITIVE = 2;
    public static final String STATUS = "Status ";
    public static final String INSERTED = "inserted";
    public static final String ERROR = "Error ocurred";

    //Constant to set an accion2
    public static final String USER_DTO = "userDTO";
    public static final String STATUS_DTO = "statusDTO";
    public static final String FOLIO_DTO = "folioDTO";
    public static final String FOLLOWUP_DTO = "followupDTO";

    //Constants to status
    public static final String NA = "na";
    public static final String PICKUP = "pu";
    public static final String CHEKIN = "ch";
    public static final String ONFLY = "onf";
    public static final String CUSTOMC = "cc";
    public static final String DELIVERED = "del";
}
