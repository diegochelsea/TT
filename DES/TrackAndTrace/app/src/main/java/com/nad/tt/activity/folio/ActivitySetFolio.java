package com.nad.tt.activity.folio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.nad.tt.activity.login.R;
import com.nad.tt.comun.dto.folio.FolioDTO;
import com.nad.tt.dao.folio.FolioDAO;

import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by TI-MAURICIO on 17/11/2015.
 */
public class ActivitySetFolio extends Activity {

    private String[] user = { "Lorena", "Juan", "Mauricio", "Valentina",
            "Lucia" };
    private AutoCompleteTextView textView;
    private ListView list;
    private String item;

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
    }

    public void init() {

        Intent i = getIntent();
        fo = (FolioDTO) i.getSerializableExtra("FolioDTO");

        btnSave = (View) findViewById(R.id.btn_save);

        list = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, user);

        textView = (AutoCompleteTextView) findViewById(R.id.txt_user);
        textView.setThreshold(1);
        textView.setAdapter(adapter);


        textView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                item = ((TextView) view).getText().toString();

                addList(item);
            }
        });
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
        Intent r = new Intent(this, FolioActivity.class);
        startActivity(r);
    }

    public void goSave(View v)
    {
        int result = 0;
        if (!listItems.isEmpty()) {
            fo.setUsers(listItems);
            FolioDAO fd = new FolioDAO();
            result = fd.insert(fo);
        }
    }


}
