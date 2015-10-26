package com.nad.tt.activity.status;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nad.tt.activity.login.R;
import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.comun.enumeration.ElementDTO;
import com.nad.tt.dao.user.UserDAO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.Util;

public class StatusActivity extends Activity {

    private EditText txtDesc;
    private TextView lblErrorDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        init();
    }

    private void init() {
        txtDesc = (EditText) findViewById(R.id.txtDesc);
        lblErrorDesc = (TextView) findViewById(R.id.lblErrorDesc);
        ElementDTO elementDesc = new ElementDTO(txtDesc, true, Constants.REGEXP_STATUS_DESC, "Descripcion", lblErrorDesc, Constants.ERROR_CODE_OK);
        txtDesc.addTextChangedListener(watcher(elementDesc));
        Log.d(Constants.LOG_NAD, "INIT");
    }


    private TextWatcher watcher(final ElementDTO dto) {
        Log.d(Constants.LOG_NAD, "WATCHER");
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(Constants.LOG_NAD, "OnTextChanged");
                Util.isValidComponent(dto);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        return textWatcher;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_status, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return Util.showMenu(id, this);
    }

    public void test(View view){
        UserDAO user = new UserDAO();
        user.getWS();
    }
}
