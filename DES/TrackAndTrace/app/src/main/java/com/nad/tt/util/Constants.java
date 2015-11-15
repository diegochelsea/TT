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
    public static final String REGEXP_EMAIL = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$";
    public static final String REGEXP_PASS = "^(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

    // Constants to save strings logs
    public static final String LOG_NAD  = "LOG-NAD";


    // Constants to error
    public static final String ERROR_CODE_INVALID_FORMAT  = "E001";
    public static final String ERROR_CODE_REQUIRED  = "E002";
    public static final String ERROR_CODE_OK  = "OK";


    // Costants to get connection to web service
    public static final String URL = "http://192.168.0.12:8484/WSTT/services/UserDAOImpl";
    public static final String NAME_SAPACE = "http://dto.comun.ws.tt.nad.com";
    public static final String SOAP_ACTION = "http://dto.comun.ws.tt.nad.com/logInTest";
    public static final String METHOD = "logInTest";
}
