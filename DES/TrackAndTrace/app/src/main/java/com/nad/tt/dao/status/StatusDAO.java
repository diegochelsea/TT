package com.nad.tt.dao.status;

import android.util.Log;

import com.nad.tt.comun.dto.BeanResponseDTO;
import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.comun.dto.status.StatusDTO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.WsConection;

import org.ksoap2.serialization.SoapObject;

/**
 * Created by TI-MAURICIO on 14/11/2015.
 */
public class StatusDAO {

    private WsConection wc;

    public StatusDAO() {
        wc = new WsConection();
    }

    public int insert(StatusDTO statusDTO) {
        int result = 0;
        try {
            result =  1;// wc.getSimpleObject(Constants.INSERT,Constants.SOAP_PRIMITIVE);
        } catch (Exception e) {
            result = 0;
        }
        return  result;
    }
}
