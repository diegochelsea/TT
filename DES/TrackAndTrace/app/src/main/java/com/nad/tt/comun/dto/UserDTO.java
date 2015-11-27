package com.nad.tt.comun.dto;

/**
 * Created by Diego on 23/10/2015.
 */

import com.nad.tt.util.DataType;
import com.nad.tt.util.TTWSConstants;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Diego on 23/10/2015.
 */
public class UserDTO implements KvmSerializable,Serializable {

    public String codError = "";
    public String msgError = "";
    public int idUser = 0;
    public String name = "";
    public String lastName = "";
    public String email = "";
    public String password = "";
    public int rol = 0;
    public String[] folios = null;

    public UserDTO() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Object getProperty(int i) {

        switch (i) {
            case 0:
                return codError;
            case 1:
                return msgError;
            case 2:
                return idUser;
            case 3:
                return name;
            case 4:
                return lastName;
            case 5:
                return email;
            case 6:
                return password;
            case 7:
                return rol;
            case 8:
                return folios;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 9;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                codError = o.toString();
                break;
            case 1:
                msgError = o.toString();
                break;
            case 2:
                idUser = Integer.valueOf(o.toString());
                break;
            case 3:
                name = o.toString();
                break;
            case 4:
                lastName = o.toString();
                break;
            case 5:
                email = o.toString();
                break;
            case 6:
                password = o.toString();
                break;
            case 7:
                rol = Integer.valueOf(o.toString());
                break;
            case 8:
                folios = (String[]) o;
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "codError";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "msgError";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "idUser";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "name";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "lastName";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "email";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "password";
                break;
            case 7:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "rol";
                break;
            case 8:
                propertyInfo.type = PropertyInfo.VECTOR_CLASS;
                propertyInfo.name = "folios";
                break;
            default:
                break;
        }
    }
}
