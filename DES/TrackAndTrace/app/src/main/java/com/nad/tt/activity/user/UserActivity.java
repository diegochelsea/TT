package com.nad.tt.activity.user;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nad.tt.activity.login.R;
import com.nad.tt.comun.dto.RolDTO;
import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.comun.enumeration.ElementDTO;
import com.nad.tt.dao.user.UserDAO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.Util;

import java.util.List;

/**
 * Created by TI-MAURICIO on 28/10/2015.
 */
public class UserActivity extends Activity {

    private EditText txtUser;
    private EditText txtLastName;
    private EditText txtEmail;
    private EditText txtPass;
    private TextView lblErrorUser;
    private TextView lblErrorLastName;
    private TextView lblErrorEmail;
    private TextView lblErrorPass;
    private Spinner spinnerRol;
    private AutoCompleteTextView txtautoComUser;
    private UserDAO userDAO = null;
    private List<RolDTO> roles = null;
    private List<UserDTO> users = null;
    ElementDTO elementName = null;
    ElementDTO elementLastName = null;
    ElementDTO elementEmail = null;
    ElementDTO elementPass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();
    }

    private void init() {
        userDAO = new UserDAO();
        txtUser = (EditText) findViewById(R.id.txtName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPass = (EditText) findViewById(R.id.txtPass);

        lblErrorUser = (TextView) findViewById(R.id.lblErrorNeimUser);
        lblErrorLastName = (TextView) findViewById(R.id.lblErrorLastName);
        lblErrorEmail = (TextView) findViewById(R.id.lblErrorEmail);
        lblErrorPass = (TextView) findViewById(R.id.lblErrorPass);
        spinnerRol = (Spinner) findViewById(R.id.spinnerRoles);
        txtautoComUser = (AutoCompleteTextView) findViewById(R.id.txtAutoComUser);

        elementName = new ElementDTO(txtUser, true, Constants.REGEXP_NAME_USER, "Name", lblErrorUser, Constants.ERROR_CODE_OK);
        elementLastName = new ElementDTO(txtLastName, true, Constants.REGEXP_LAST_NAME_USER, "Last Name", lblErrorLastName, Constants.ERROR_CODE_OK);
        elementEmail = new ElementDTO(txtEmail, true, Constants.REGEXP_EMAIL, "Email", lblErrorEmail, Constants.ERROR_CODE_OK);
        elementPass = new ElementDTO(txtPass, true, Constants.REGEXP_PASS, "Password", lblErrorPass, Constants.ERROR_CODE_OK);

        txtUser.addTextChangedListener(watcher(elementName));
        txtLastName.addTextChangedListener(watcher(elementLastName));
        txtEmail.addTextChangedListener(watcher(elementEmail));
        txtPass.addTextChangedListener(watcher(elementPass));


        roles = userDAO.getAllRoles();
        String[] roleStr = new String[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
            roleStr[i] = roles.get(i).getDesc();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, roleStr);
        spinnerRol.setAdapter(adapter);


//        users = userDAO.getAllUsers();
//        String[] userStr = new String[users.size()];
//        if (!users.isEmpty()) {
//            for (int i = 0; i < users.size(); i++) {
//                roleStr[i] = users.get(i).name;
//            }
//            ArrayAdapter<String> adapterUsers = new ArrayAdapter<String>(this,
//                    android.R.layout.simple_spinner_item, roleStr);
//            txtautoComUser.setAdapter(adapterUsers);
//        }

        Log.d(Constants.LOG_NAD, "INIT");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return Util.showMenu(id, this);
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

    public void actionSaveUser(View view) {
        if (Util.isValidComponent(elementName) && Util.isValidComponent(elementLastName) && Util.isValidComponent(elementEmail) && Util.isValidComponent(elementPass)) {

            UserDTO userDTO = new UserDTO();
            userDTO.name = txtUser.getText().toString();
            userDTO.lastName = txtLastName.getText().toString();
            userDTO.email = txtEmail.getText().toString();
            userDTO.password = txtPass.getText().toString();
            userDTO.rol = getRol(spinnerRol.getSelectedItem().toString());
            userDTO = userDAO.saveUser(userDTO);
            Log.d("ActivityUser", "saveUser, values: " + userDTO.name + ", " + userDTO.rol);
            Util.showToast(userDTO.msgError, this);
        }
    }

    private int getRol(String desc) {
        for (RolDTO rolDTO : roles) {
            if (desc.equals(rolDTO.getDesc())) {
                return rolDTO.getIdRol();
            }
        }
        return 0;
    }
}
