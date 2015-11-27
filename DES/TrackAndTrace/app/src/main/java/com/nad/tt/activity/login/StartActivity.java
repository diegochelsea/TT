package com.nad.tt.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.nad.tt.activity.folio.FoliosActivity;
import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.util.GlobalClass;
import com.nad.tt.util.Util;

import java.io.Serializable;

public class StartActivity extends Activity {

    private String id_usuario = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
    }

    private void  init()
    {
        Intent intent= getIntent();
        Bundle bundleGetID = intent.getExtras();
        if(bundleGetID!=null){
            id_usuario =(String) bundleGetID.get("UserDTO");
        }
        Log.d("ID_USUARIO", id_usuario);

        setGlobal();
    }

    private void setGlobal()
    {
        Log.d("Envio variable global", id_usuario);
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        globalVariable.setIdUsuario(Integer.parseInt(id_usuario));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Call show menu from java class util
        return Util.showMenu(id, this);
    }
}
