package com.nad.tt.dao.user;

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

    public void getWS() {
        /**try {

            URL url = new URL("http://localhost:8989/ttws/user?wsdl");

            QName qname = new QName("http://ws.mkyong.com/", "HelloWorldImplService");
            Service service = Service.create(url, qname);
            com.nad.tt.inter.user.UserDAO userDAO = service.getPort(com.nad.tt.inter.user.UserDAO.class);
            BeanResponseDTO<UserDTO> responseDTO = null;
            BeanResponseDTO<UserDTO> dto =  userDAO.saveUser(responseDTO);
            Log.d(Constants.LOG_NAD, "Values: " + dto.getCodError() + ", " + dto.getMsgError() + ", "
            + ", " + dto.getResult().getName());
        } catch (Exception exception) {

        }*/

        try{

            SoapObject request = new SoapObject(Constants.NAME_SAPACE, Constants.METHOD);

            PropertyInfo pa1 = new PropertyInfo();
            pa1.setName("Username");
            pa1.setValue("value");

            PropertyInfo pb1 = new PropertyInfo();
            pb1.setName("Password");
            pb1.setValue("value");

            PropertyInfo pc1 = new PropertyInfo();
            pc1.setName("UserType");
            pc1.setValue("value");

            request.addProperty(pa1);
            request.addProperty(pb1);
            request.addProperty(pc1);


            SoapSerializationEnvelope envelope = new    SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            System.out.println("new3");
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);
            androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            try{
                androidHttpTransport.call(Constants.SOAP_ACTION_PREFIX, envelope);
            }
            catch(Exception e)
            {
            }
            SoapObject response = (SoapObject)envelope.bodyIn;

//            result = response.getProperty(0).toString();

        }catch (Exception e) {
            // TODO: handle exception
        }
    }
}
