package com.nad.tt.activity.login;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.comun.enumeration.ElementDTO;
import com.nad.tt.dao.user.UserDAO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.Util;

public class LoginActivity extends Activity {

    private     EditText txtUser;
    private EditText txtPassword;
    private TextView lblErrorUser;
    private TextView lblErrorPassword;
    private UserDAO userDAO;

    ElementDTO elementEmail = null;
    ElementDTO elementPass = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_login, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Don't to do to call show menu.
        return false;
    }

    public void init(){
        userDAO = new UserDAO();
        txtUser = (EditText)(findViewById(R.id.txt_user));
        txtPassword = (EditText)(findViewById(R.id.txt_password));
        lblErrorUser = (TextView)findViewById(R.id.lblErrorEmail);
        lblErrorPassword = (TextView)findViewById(R.id.lblErroPassword);

        elementEmail = new ElementDTO(txtUser, true, Constants.REGEXP_EMAIL, "Email", lblErrorUser, Constants.ERROR_CODE_OK);
        elementPass = new ElementDTO(txtPassword, true, Constants.REGEXP_PASS, "Password", lblErrorPassword, Constants.ERROR_CODE_OK);

        txtUser.addTextChangedListener(watcher(elementEmail));
        txtPassword.addTextChangedListener(watcher(elementPass));

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

    public void actionLogin(View view){
        if (Util.isValidComponent(elementEmail) && Util.isValidComponent(elementPass)){

        UserDTO userDTO = new UserDTO();
            userDTO.email = txtUser.getText().toString();
            userDTO.password = txtPassword.getText().toString();
            userDTO = userDAO.login(userDTO);
            if (Constants.ERROR_CODE_OK.equals(userDTO.codError)){
                Util.startActivityByClass(StartActivity.class, this);
            } else {
                Util.showToast(userDTO.msgError, this);
            }
        }

    }
}
