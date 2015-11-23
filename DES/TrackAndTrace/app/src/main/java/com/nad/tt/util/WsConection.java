package com.nad.tt.util;

import android.util.Log;

import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.comun.dto.folio.FolioDTO;
import com.nad.tt.comun.dto.status.StatusDTO;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Objects;
import java.util.Vector;

/**
 * Created by TI-MAURICIO on 14/11/2015.
 */
public final class WsConection {


    public static Vector<SoapObject> getSimpleObject(String method, boolean isSoapObject) {

        Vector<SoapObject> soapObject = null;
        try {
            Log.d("WEB-SERVICE", "1");
            SoapObject request = new SoapObject(Constants.NAME_SAPACE, method);
            Log.d("WEB-SERVICE", "2");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            Log.d("WEB-SERVICE", "3");
            envelope.setOutputSoapObject(request);
            Log.d("WEB-SERVICE", "4");
            HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);
            Log.d("WEB-SERVICE", "5");
            androidHttpTransport.call(new StringBuilder(Constants.NAME_SAPACE).append("/").append(method).toString(), envelope);
            soapObject = (Vector<SoapObject>) envelope.getResponse();
            Log.d("WEB-SERVICE, getObject ", "OK");
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
        }
        return soapObject;
    }

    public static SoapObject getSimpleObjectWithPatam(String method, UserDTO userDTO) {

        SoapObject soapObject = null;
        try {
            Log.d("WEB-SERVICE", "1, params: " + userDTO.email + ", " + userDTO.password);
            SoapObject request = new SoapObject(Constants.NAME_SAPACE, method);
            Log.d("WEB-SERVICE", "2");

            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setName(Constants.USER_DTO);
            propertyInfo.setValue(userDTO);
            propertyInfo.setType(userDTO.getClass());
            request.addProperty(propertyInfo);
            Log.d("WEB-SERVICE", "3 add property");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            Log.d("WEB-SERVICE", "4");
            envelope.setOutputSoapObject(request);
            Log.d("WEB-SERVICE", "5");
            envelope.addMapping(Constants.NAME_SAPACE, Constants.CLASS_USER_DTO, userDTO.getClass());
            Log.d("WEB-SERVICE", "6");
            HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);
            Log.d("WEB-SERVICE", "7 method: " + new StringBuilder(Constants.NAME_SAPACE).append("/").append(method).toString());
            androidHttpTransport.call(new StringBuilder(Constants.NAME_SAPACE).append("/").append(method).toString(), envelope);
            Log.d("WEB-SERVICE", "8");
            soapObject = (SoapObject) envelope.getResponse();
            Log.d("WEB-SERVICE, getObject ", "OK");
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
        }
        return soapObject;
    }

    public static int execStatement(Object objet, String method, String objetType) {

        int result = 0;

        SoapObject soapObjectResult;
        soapObjectResult = new SoapObject(Constants.NAME_SAPACE, method);
        PropertyInfo pi = new PropertyInfo();
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        switch (objetType) {
            case Constants.USER_DTO:
                UserDTO us = (UserDTO) objet;
                pi.setName(Constants.USER_DTO);
                pi.setValue(us);
                pi.setType(us.getClass());
                soapObjectResult.addProperty(pi);
                envelope.setOutputSoapObject(soapObjectResult);
                envelope.addMapping(Constants.NAME_SAPACE, Constants.CLASS_USER_DTO, us.getClass());
                Log.d("CASE", "1");
                break;
            case Constants.STATUS_DTO:
                StatusDTO st = (StatusDTO) objet;
                pi.setName(Constants.STATUS_DTO);
                pi.setValue(st);
                pi.setType(st.getClass());
                soapObjectResult.addProperty(pi);
                envelope.setOutputSoapObject(soapObjectResult);
                envelope.addMapping(Constants.NAME_SAPACE, Constants.CLASS_STATUS_DTO, st.getClass());
                Log.d("CASE", "2");
                break;
            case Constants.FOLIO_DTO:
                FolioDTO f = (FolioDTO) objet;
                pi.setName(Constants.FOLIO_DTO);
                pi.setValue(f);
                pi.setType(f.getClass());
                soapObjectResult.addProperty(pi);
                envelope.setOutputSoapObject(soapObjectResult);
                envelope.addMapping(Constants.NAME_SAPACE, Constants.CLASS_FOLIO_DTO, f.getClass());
                Log.d("CASE", "3");
                break;
            /*case Constants.FOLLOWUP_DTO:
                FollowUp fu = (FollowUp)objet;
                pi.setName(Constants.FOLLOWUP_DTO);
                pi.setValue(fu);
                pi.setType(fu.getClass());
                envelope.addMapping(soap_action, clas, fu.getClass());
                break;
                */
            default:
                break;
        }
        HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);

        try {
            androidHttpTransport.call(new StringBuilder(Constants.NAME_SAPACE).append("/").append(method).toString(), envelope);

            result = Integer.valueOf(envelope.getResponse().toString());

        } catch (Exception ex) {
            result = 0;// error
        }
        return result;
    }

}
