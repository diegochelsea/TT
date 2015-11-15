package com.nad.tt.util;

import android.util.Log;

import com.nad.tt.comun.dto.BeanResponseDTO;
import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.comun.dto.status.StatusDTO;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Objects;

/**
 * Created by TI-MAURICIO on 14/11/2015.
 */
public class WsConection {


    public Object getSimpleObject(String method, int obj) {

        Object object = null;
        StringBuilder soapAction = new StringBuilder();
        try {
            soapAction.append(Constants.NAME_SAPACE);
            soapAction.append(method);
            SoapObject request = new SoapObject(Constants.NAME_SAPACE, method);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);
            androidHttpTransport.call(soapAction.toString(), envelope);
            object = (Objects) envelope.getResponse();
            Log.d("WEB-SERVICE, getObject ", "OK");
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ",  e.toString());
            Log.d("WEB-SERVICE, Expetion: ",  e.getMessage());
            Log.println(1, "WEB-SERVICE, Expetion: ", (e.getMessage() == null) ? (e.toString() == null) ? "Exception null" :e.toString() :e.getMessage());
        }
        return object;
    }

    public int execStatement(Object objet, String method, String objetType) {

        int result = 0;
        StringBuilder soap_action = new StringBuilder();
        soap_action.append("/");
        soap_action.append(method);

        SoapObject soapObjectResult;
        soapObjectResult = new SoapObject(Constants.NAME_SAPACE, method);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);

        PropertyInfo pi = new PropertyInfo();
        String name = "";

        switch (objetType) {
            case Constants.USER_DTO:
                UserDTO us = (UserDTO)objet;
                name = Constants.USER_DTO;
                pi.setValue(us);
                pi.setType(us.getClass());
                envelope.addMapping(soap_action.toString(), "", us.getClass());
            case Constants.STATUS_DTO:
                StatusDTO st = (StatusDTO)objet;
                name = Constants.STATUS_DTO;
                pi.setValue(st);
                pi.setType(st.getClass());
                envelope.addMapping(soap_action.toString(), "", st.getClass());
                /*
            case Constants.FOLIO_DTO:
                FolioDTO f = (FolioDTO)objet;
                name = Constants.FOLIO_DTO;
                pi.setValue(f);
                pi.setType(f.getClass());
                envelope.addMapping(soap_action, clas, f.getClass());
            case Constants.FOLLOWUP_DTO:
                FollowUp fu = (FollowUp)objet;
                name = Constants.FOLLOWUP_DTO;
                pi.setValue(fu);
                pi.setType(fu.getClass());
                envelope.addMapping(soap_action, clas, fu.getClass());
                break;
                */
        }
        pi.setName(name);

        soapObjectResult.addProperty(pi);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);

        try {
            androidHttpTransport.call(soap_action.toString(), envelope);

            result = Integer.valueOf(envelope.getResponse().toString());

        } catch (Exception ex) {
            result = 0;// error
        }
        return result;
    }

}
