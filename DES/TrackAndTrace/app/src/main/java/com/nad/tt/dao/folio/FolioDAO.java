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

    public List<String> select(FolioDTO folioDTO) {
        List<String> ls = new ArrayList<>() ;
        int result = 0;
        try {
            result =  1;// wc.getSimpleObject(Constants.INSERT,Constants.SOAP_PRIMITIVE);
            ls.add("1000002");
            ls.add("Mexico");
            ls.add("Usa");
            ls.add("onf");
        } catch (Exception e) {
            result = 0;
        }
        return ls;
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
