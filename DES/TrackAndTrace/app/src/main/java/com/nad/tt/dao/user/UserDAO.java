package com.nad.tt.dao.user;

import android.util.Log;

import com.nad.tt.comun.dto.BeanResponseDTO;
import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.util.Constants;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Diego on 23/10/2015.
 */
public class UserDAO {

    public BeanResponseDTO<UserDTO> login() {
        SoapObject soapObjectResult;
        BeanResponseDTO<UserDTO> responseDTO = new BeanResponseDTO<>();
        String codError = "";
        String msgError = "";
        try {

            SoapObject request = new SoapObject(Constants.NAME_SAPACE, Constants.METHOD);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);
            Log.d("WEB-SERVICE, 4 ", "OK");
            androidHttpTransport.call(Constants.SOAP_ACTION, envelope);
            Log.d("WEB-SERVICE, 5 ", "OK");
            soapObjectResult = (SoapObject) envelope.getResponse();

            responseDTO.setResult((UserDTO) soapObjectResult.getProperty("result"));
            responseDTO.setCodError(soapObjectResult.getProperty("codError").toString());
            responseDTO.setMsgError(soapObjectResult.getProperty("msgError").toString());
            Log.d("WEB-SERVICE, Response ", responseDTO.getCodError() + ", " + responseDTO.getMsgError());
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ",  e.toString());
            Log.d("WEB-SERVICE, Expetion: ",  e.getMessage());
//            Log.println(1, "WEB-SERVICE, Expetion: ", (e.getMessage() == null) ? (e.toString() == null) ? "Exception null" :e.toString() :e.getMessage());
        }
        return responseDTO;
    }
}
