package com.nad.tt.comun.dto.folio;

import com.nad.tt.util.DataType;
import com.nad.tt.util.TTWSConstants;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by TI-MAURICIO on 15/11/2015.
 */
public class FolioDTO implements KvmSerializable {

    public String codError;
    public  String msgError;
    public  int idFolio;
    public  String beginning = "";
    public String destination = "";
    public  String status = "";
    public String[] users;

    public FolioDTO() {
        // TODO Auto-generated constructor stub
    }

    public FolioDTO(int idFolio, String beginning, String destination,
                    String status) {
        super();
        this.idFolio = idFolio;
        this.beginning = beginning;
        this.destination = destination;
        this.status = status;
    }

    @Override
    public Object getProperty(int i) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int i, Object o) {

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {

    }
}
