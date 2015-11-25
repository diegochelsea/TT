package com.nad.tt.dao.folio;

import com.nad.tt.comun.dto.folio.FolioDTO;
import com.nad.tt.util.WsConection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TI-MAURICIO on 17/11/2015.
 */
public class FolioDAO {

    private WsConection wc;

    public FolioDAO() {
        wc = new WsConection();
    }

    public List<FolioDTO> selectAllFolio() {

        return null;
    }

    public int insert(FolioDTO folioDTO) {
        int result = 0;
        try {
            result =  1;// wc.getSimpleObject(Constants.INSERT,Constants.SOAP_PRIMITIVE);
        } catch (Exception e) {
            result = 0;
        }
        return  result;
    }
}
