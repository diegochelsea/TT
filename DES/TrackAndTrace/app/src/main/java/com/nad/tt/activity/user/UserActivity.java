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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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
    private Button btnSave;
    private Button btnDelete;
    private UserDAO userDAO = null;
    private List<RolDTO> roles = null;
    private List<UserDTO> users = null;
    ElementDTO elementName = null;
    ElementDTO elementLastName = null;
    ElementDTO elementEmail = null;
    ElementDTO elementPass = null;
    private int idUserDelete = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init(false);
    }

    private void init(boolean restart) {
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
        btnSave = (Button) findViewById(R.id.btnUserSave);
        btnDelete = (Button) findViewById(R.id.btnUserDelete);

        elementName = new ElementDTO(txtUser, true, Constants.REGEXP_NAME_USER, "Name", lblErrorUser, Constants.ERROR_CODE_OK);
        elementLastName = new ElementDTO(txtLastName, true, Constants.REGEXP_LAST_NAME_USER, "Last Name", lblErrorLastName, Constants.ERROR_CODE_OK);
        elementEmail = new ElementDTO(txtEmail, true, Constants.REGEXP_EMAIL, "Email", lblErrorEmail, Constants.ERROR_CODE_OK);
        elementPass = new ElementDTO(txtPass, true, Constants.REGEXP_PASS, "Password", lblErrorPass, Constants.ERROR_CODE_OK);

        txtUser.addTextChangedListener(watcher(elementName));
        txtLastName.addTextChangedListener(watcher(elementLastName));
        txtEmail.addTextChangedListener(watcher(elementEmail));
        txtPass.addTextChangedListener(watcher(elementPass));

        Log.d(Constants.LOG_NAD, "INIT");

        if ((getIntent().getExtras() != null && getIntent().getExtras().get("actionUsr") != null && "add".equals(getIntent().getExtras().get("actionUsr"))) || restart) {
            btnDelete.setVisibility(View.GONE);
            btnSave.setVisibility(View.VISIBLE);
        } else {
            btnSave.setVisibility(View.GONE);
            btnDelete.setVisibility(View.VISIBLE);
        }
        String msgError = Constants.EMPTY_STRING;
        msgError = initRoles();

        if (Constants.EMPTY_STRING.equals(msgError)) {
            msgError = initUsers();
        }
        idUserDelete = 0;
        Util.showToast(msgError, this);
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
            init(true);
            cleanTxt();
        }
    }

    public void actionDeleteUser(View view){
        UserDTO userDTO = fillUser();
        userDTO.idUser = idUserDelete;
        userDTO = userDAO.deleteUser(userDTO);
        Log.d("ActivityUser", "deleteUser, values: " + userDTO.name + ", " + userDTO.idUser);
        init(true);
        Util.showToast(userDTO.msgError, this);
        cleanTxt();
    }

    private UserDTO fillUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.name = txtUser.getText().toString();
        userDTO.lastName = txtLastName.getText().toString();
        userDTO.email = txtEmail.getText().toString();
        userDTO.password = txtPass.getText().toString();
        userDTO.rol = getRol(spinnerRol.getSelectedItem().toString());
        return userDTO;
    }

    private void cleanTxt(){
        txtUser.setText(Constants.EMPTY_STRING);
        txtLastName.setText(Constants.EMPTY_STRING);
        txtEmail.setText(Constants.EMPTY_STRING);
        txtPass.setText(Constants.EMPTY_STRING);
        txtautoComUser.setText(Constants.EMPTY_STRING);
    }

    private int getRol(String desc) {
        for (RolDTO rolDTO : roles) {
            if (desc.equals(rolDTO.getDesc())) {
                return rolDTO.getIdRol();
            }
        }
        return 0;
    }

    private int getUserPosition(String name) {
        for (int i = 0; i  < users.size(); i++) {
            if (name.equals(users.get(i).name)) {
                return i;
            }
        }
        return 0;
    }

    private int getRolPosition(int idRol) {
        for (int i = 0; roles != null && i < roles.size(); i++) {
            if (idRol == roles.get(i).getIdRol()) {
                return i;
            }
        }
        return 0;
    }

    private String getUser(int pos) {
        String msgError = Constants.EMPTY_STRING;
        try {
            UserDTO dto = users.get(pos);
            Log.d(Constants.LOG_NAD, "pos: " + pos + ", DTO:" + dto.idUser + ", " + dto.name);
            dto = userDAO.getUniqUser(dto);
            Log.d(Constants.LOG_NAD, "DTO BD:" + dto.idUser + ", " + dto.name);
            int rolPos = getRolPosition(dto.rol);
            Log.d(Constants.LOG_NAD, "rolPos: " + rolPos);
            if (Constants.ERROR_CODE_OK.equals(dto.codError) && rolPos > -1) {
                Log.d(Constants.LOG_NAD, "add values to view: ");
                btnDelete.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.GONE);
                spinnerRol.setSelection(rolPos);
                txtEmail.setText(dto.email);
                txtLastName.setText(dto.lastName);
                txtPass.setText(dto.password);
                txtUser.setText(dto.name);
                idUserDelete = dto.idUser;
            } else {
                Log.d(Constants.LOG_NAD, "hidden elements");
                btnSave.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.GONE);
                msgError = "Error: ¡User not found!";
            }
        } catch (Exception e) {
            Log.d(Constants.LOG_NAD, e.getMessage());
            msgError = "Error: ¡Ocurred an error to search user, try later!";
        }
        return msgError;
    }

    private String initRoles() {
        String result = "";
        try {
            roles = userDAO.getAllRoles();
            if (roles != null && roles.size() > 0) {
                String[] roleStr = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    roleStr[i] = roles.get(i).getDesc();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, roleStr);
                spinnerRol.setAdapter(adapter);
            } else {
                result = "Not exists roles, ¡Try later!";
            }
        } catch (Exception e) {
            Log.d(Constants.LOG_NAD, e.getMessage());
            result = "Error: Ocurred an error to select Roles";
        }
        return result;
    }


    private String initUsers() {
        String result = "";
        try {
            users = userDAO.getAllUsers();
            if (users != null && !users.isEmpty()) {
                String[] userStr = new String[users.size()];
                if (!users.isEmpty()) {
                    Log.d("UserActivity", "users: " + users.size() + ", array: " + userStr.length);
                    for (int i = 0; i < users.size(); i++) {
                        userStr[i] = users.get(i).name;
                    }
                    ArrayAdapter<String> adapterUsers = new ArrayAdapter<String>(this,
                            android.R.layout.simple_spinner_item, userStr);
                    txtautoComUser.setAdapter(adapterUsers);
                }

                txtautoComUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d(Constants.LOG_NAD, "OnItem positipon: " + position + ", id: " + id);
                        int pos = getUserPosition(txtautoComUser.getText().toString());
                        Log.d(Constants.LOG_NAD, "txtautoComUser: " + txtautoComUser.getText().toString());
                        Util.showToast(getUser(pos), getApplicationContext());
                    }
                });
            } else {
                result = "Not exists users, ¡Try later!";
            }
        } catch (Exception e) {
            Log.d(Constants.LOG_NAD, e.getMessage());
            result = "Error: Ocurred an error to select Users";
        }
        return result;
    }
}
