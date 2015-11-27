package com.nad.tt.activity.folio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.nad.tt.activity.login.R;
import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.comun.dto.folio.FolioDTO;
import com.nad.tt.dao.folio.FolioDAO;
import com.nad.tt.dao.user.UserDAO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.Util;

import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TI-MAURICIO on 17/11/2015.
 */
public class ActivitySetFolio extends Activity {

    private String[] user = { "Lorena", "Juan", "Mauricio", "Valentina",
            "Lucia" };
    private AutoCompleteTextView textView;
    private ListView list;
    private String item;
    private TextView lblAssignFolio;

    private UserDAO userDAO = null;

    private List<UserDTO> users = null;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter1;
    FolioDTO fo = new FolioDTO();

    private View btnSave;
    private View btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setfolio);
        init();

        Intent i = getIntent();
        fo = (FolioDTO) i.getSerializableExtra("FolioDTO");
        setFolio(fo);

    }

    public void setFolio(FolioDTO folioDto)
    {
        lblAssignFolio.setText(String.valueOf(folioDto.idFolio));
    }

    public void init() {
        btnSave = (View)findViewById(R.id.btn_save);
        lblAssignFolio = (TextView)findViewById(R.id.lbl_assign_folio);

        list = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, user);

        textView = (AutoCompleteTextView) findViewById(R.id.txt_user);
        textView.setThreshold(1);
        textView.setAdapter(adapter);
        String msgError = Constants.EMPTY_STRING;
        msgError = initUsers();
        Util.showToast(msgError, this);
    }

    public void addList(String name) {
        adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems);

        listItems.add(name);
        list.setAdapter(adapter1);
        textView.setText("");
    }

    public void goBack(View v)
    {
        System.exit(0);
    }

    public void goSave(View v)
    {
        int result = 0;
        if (!listItems.isEmpty()) {
            fo.users = listItems.toArray(new String[listItems.size()]);
            FolioDAO folioDAO = new FolioDAO();
            fo = folioDAO.saveFolio(fo);
            validateSave(fo);
        }
    }

    public void validateSave (FolioDTO folioDTO){
        Log.d("ActivityFolio", "saveFolio, values: " + folioDTO.idFolio);
        Util.showToast(folioDTO.msgError, this);
        cleanTxt();
    }

    private void cleanTxt(){
        textView.setText(Constants.EMPTY_STRING);
        list.clearAnimation();
    }

    private int getUserPosition(String name) {
        for (int i = 0; i  < users.size(); i++) {
            if (name.equals(users.get(i).name)) {
                return i;
            }
        }
        return 0;
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
                            android.R.layout.simple_spinner_item, listItems);
                    textView.setAdapter(adapterUsers);
                }

                textView.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        item = ((TextView) view).getText().toString();

                        addList(item);
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
