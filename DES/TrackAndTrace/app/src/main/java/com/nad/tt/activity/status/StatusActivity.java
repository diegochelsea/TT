package com.nad.tt.activity.status;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nad.tt.activity.login.R;
import com.nad.tt.comun.dto.status.StatusDTO;
import com.nad.tt.comun.enumeration.ElementDTO;
import com.nad.tt.dao.status.StatusDAO;
import com.nad.tt.dao.user.UserDAO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.Util;

import android.view.View.OnClickListener;
import android.widget.Toast;

public class StatusActivity extends Activity {

    private StatusDAO statusDAO;
    private EditText txtDesc;
    private TextView lblErrorDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        init();

    }

    private void init() {

        statusDAO = new StatusDAO();

        txtDesc = (EditText) findViewById(R.id.txtDesc);
        lblErrorDesc = (TextView) findViewById(R.id.lblErrorDesc);
        txtDesc.addTextChangedListener(watcher(validateView()));

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
        getMenuInflater().inflate(R.menu.menu_start, menu);
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

    public void test(View view) {
        UserDAO user = new UserDAO();
    }


    private ElementDTO validateView() {
        return new ElementDTO(txtDesc, true, Constants.REGEXP_STATUS_DESC, "Description", lblErrorDesc, Constants.ERROR_CODE_OK);
    }

    public void saveStatus(View v) {
        int result = 0;
        Toast toast = null;
        StringBuilder msg = null;
        if (Util.isValidComponent(validateView())) {
            StatusDTO statusDTO = new StatusDTO(1, txtDesc.getText().toString());
            result = statusDAO.insert(statusDTO);
        }

        if (result == 1) {
            msg.append(Constants.STATUS);
            msg.append(Constants.INSERTED);
        } else {
            msg.append(Constants.ERROR);
        }
        toast = Toast.makeText
                (this, msg , Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }
}
