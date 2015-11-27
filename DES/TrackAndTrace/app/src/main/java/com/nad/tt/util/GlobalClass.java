package com.nad.tt.util;

import android.app.Application;

/**
 * Created by TI-MAURICIO on 27/11/2015.
 */
public class GlobalClass extends Application {

    private int idUsuario = 0;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
