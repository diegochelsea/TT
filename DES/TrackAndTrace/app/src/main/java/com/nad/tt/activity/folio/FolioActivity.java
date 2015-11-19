package com.nad.tt.activity.folio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nad.tt.activity.login.R;
import com.nad.tt.comun.dto.folio.FolioDTO;
import com.nad.tt.comun.enumeration.ElementDTO;
import com.nad.tt.dao.folio.FolioDAO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.Util;

import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TI-MAURICIO on 17/11/2015.
 */
public class FolioActivity extends Activity {

    private EditText txtFolio;
    private EditText txtOrigin;
    private EditText txtDestination;

    private TextView lblError;
    private TextView lbl_error_origin;
    private TextView lbl_error_destination;

    private View btnNext;
    private View btnFollowin;

    private String folio;
    private String begining;
    private String destination;

    private String[] folios = { "10002", "10003", "20002", "20003" };
    private AutoCompleteTextView textAutoComplete;
    private ListView list;
    private String item;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folio);
        init();
    }

    public void init() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, folios);

        textAutoComplete = (AutoCompleteTextView) findViewById(R.id.txtautoComplete_folio);
        textAutoComplete.setThreshold(1);
        textAutoComplete.setAdapter(adapter);

        txtFolio = (EditText) findViewById(R.id.txt_folio);
        txtOrigin = (EditText) findViewById(R.id.txt_origin);
        txtDestination = (EditText) findViewById(R.id.txtDestination);

        lblError = (TextView) findViewById(R.id.lbl_error_folio);
        lbl_error_destination = (TextView) findViewById(R.id.lbl_error_destination);
        lblError = (TextView) findViewById(R.id.lbl_error_origin);

        btnNext = (View) findViewById(R.id.btnNext);
        btnFollowin = (View) findViewById(R.id.btn_followuP);

        //validacion
        txtFolio.addTextChangedListener(watcher(validateFolio()));
        txtOrigin.addTextChangedListener(watcher(validateOrigin()));
        txtDestination.addTextChangedListener(watcher(validateDestination()));

        textAutoComplete.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                item = ((TextView) view).getText().toString();

                query(item);
            }
        });
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

    private ElementDTO validateFolio() {
        return new ElementDTO(txtFolio, true, Constants.REGEXP_FOLIO, "Folio", lblError, Constants.ERROR_CODE_OK);
    }

    private ElementDTO validateOrigin() {
        return new ElementDTO(txtOrigin, true, Constants.REGEXP_ORIGIN, "Begin", lbl_error_origin, Constants.ERROR_CODE_OK);
    }
    private ElementDTO validateDestination() {
        return new ElementDTO(txtDestination, true, Constants.REGEXP_DESTINATION, "Destination", lblError, Constants.ERROR_CODE_OK);
    }

    public void query(String item) {
        FolioDAO fa = new FolioDAO();
        List<String> ls = new ArrayList<>();
        FolioDTO fod = new FolioDTO();
        fod.setFolio(textAutoComplete.getText().toString());
        ls = fa.select(fod);

        fod.setFolio(ls.get(0).toString());
        fod.setSorce(ls.get(1));
        fod.setReceiver(ls.get(2));
        fod.setStatus(ls.get(3));

        Intent r = new Intent(this, ActivitySetFolio.class);
        r.putExtra("FolioDTO", fod);

      //  btnNext.setVisibility(0);
      //  btnFollowin.setVisibility(1);

        txtFolio.setEnabled(true);
        txtOrigin.setEnabled(true);
        txtDestination.setEnabled(true);

    }


    public void goNext()
    {
        if(Util.isValidComponent(validateFolio()))
        {
            if(Util.isValidComponent(validateOrigin()))
            {
                if(Util.isValidComponent(validateDestination()))
                {
                    FolioDTO fod = new FolioDTO();
                    fod.setFolio(folio);
                    fod.setReceiver(destination);
                    fod.setSorce(begining);
                    Intent r = new Intent(this, ActivitySetFolio.class);
                    r.putExtra("FolioDTO", fod);
                    startActivity(r);
                }

            }
        }

    }

}
