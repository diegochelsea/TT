package com.nad.tt.dao.folio;

import android.util.Log;

import com.nad.tt.comun.dto.RolDTO;
import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.comun.dto.folio.FolioDTO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.WsConection;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by TI-MAURICIO on 17/11/2015.
 */
public class FolioDAO {


    private WsConection wc;

    public FolioDAO() {
        wc = new WsConection();
    }

    public List<FolioDTO> selectAllFolio(String idUsuario) throws Exception{
        Vector<SoapObject> soapObjectsResult = null;
        List<FolioDTO> result = new ArrayList<>();
        Log.d("selectAllFolio", "init");
        try
        {
            soapObjectsResult = WsConection.getSimpleObjectParam(Constants.METHOD_SELECT_USER_FOLIOS,idUsuario);
            FolioDTO folioDTO = null;
            for(SoapObject object: soapObjectsResult)
            {
                folioDTO =  new FolioDTO();
                folioDTO.idFolio = Integer.valueOf(object.getProperty("idFolio").toString());
                folioDTO.beginning = object.getProperty("beginning").toString();
                folioDTO.destination = object.getProperty("destination").toString();
                folioDTO.status = object.getProperty("status").toString();
                result.add(folioDTO);
            }
            Log.d("getAllFolios", "");
        }catch (Exception e)
        {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
            throw  new Exception(e.getMessage());
        }
        return result;
    }

    public FolioDTO getUniqFolio(FolioDTO folioDTO) {
        SoapObject soapObjectResult;

        try {
            soapObjectResult = WsConection.getSimpleObjectWithPatam(Constants.METHOD_SELECT_UNIQ_FOLIO, folioDTO);
            folioDTO.idFolio = (Integer.valueOf(soapObjectResult.getProperty("idFolio").toString()));
            Log.d(Constants.LOG_NAD, "getUniqUser: 1");
            folioDTO.beginning = (String.valueOf(soapObjectResult.getProperty("beginning")));
            Log.d("WEB-SERVICE", "getUniqUser: 2");
            folioDTO.destination = (String.valueOf(soapObjectResult.getProperty("destination")));
            Log.d("WEB-SERVICE", "getUniqUser: 3");
            folioDTO.status = (String.valueOf(soapObjectResult.getProperty("status")));
            Log.d("WEB-SERVICE", "getUniqUser: 4");

        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
        }
        return folioDTO;
    }

    public FolioDTO updateFolio(FolioDTO folioDTO) {
        int result = 0;
        folioDTO.codError = Constants.ERROR_CODE_NOK;
        folioDTO.msgError = "¡Error: could not save the folio, try again later!";
        try {
            result = WsConection.execStatement(folioDTO, Constants.METHOD_UPDATE_FOLIO, Constants.FOLIO_DTO);
            if (result > 0){
                folioDTO.codError = Constants.ERROR_CODE_OK;
                folioDTO.msgError = "¡Updated successfully!";
            }
            Log.d("saveUser", "");
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
        }
        return folioDTO;
    }

    public FolioDTO saveFolio(FolioDTO folioDTO) {
        int result = 0;
        folioDTO.codError = Constants.ERROR_CODE_NOK;
        folioDTO.msgError = "¡Error: could not save the Folio, try again later!";
        try {
            result = WsConection.execStatement(folioDTO, Constants.METHOD_SAVE_FOLIO, Constants.FOLIO_DTO);
            if (result > 0){
                folioDTO.codError = Constants.ERROR_CODE_OK;
                folioDTO.msgError = "¡User saved successfully!";
            }
            Log.d("saveUser", "");
        } catch (Exception e) {
            Log.d("WEB-SERVICE, Expetion: ", e.toString());
            Log.d("WEB-SERVICE, Expetion: ", e.getMessage());
        }
        return folioDTO;
    }
}
