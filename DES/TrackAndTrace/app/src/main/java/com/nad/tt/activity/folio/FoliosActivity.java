package com.nad.tt.activity.folio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.nad.tt.activity.follow.FollowActivity;
import com.nad.tt.activity.login.R;
import com.nad.tt.comun.dto.folio.FolioDTO;
import com.nad.tt.comun.enumeration.ElementDTO;
import com.nad.tt.dao.folio.FolioDAO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TI-MAURICIO on 19/11/2015.
 */
public class FoliosActivity extends Activity {

    private EditText txtFolio;
    private EditText txtOrigin;
    private EditText txtDestination;
    private TextView lblError;
    private TextView lbl_error_origin;
    private TextView lbl_error_destination;
    private Button btnNext;
    private Button btnFollowin;
    private String folio;
    private String begining;
    private String destination;
    private String[] folios = { "1000002", "1000003", "2000002", "2000003" };
    private AutoCompleteTextView textAutoComplete;
    private ListView list;
    private String item;
    private FolioDTO fod;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folios);
        init();
        fod = new FolioDTO();
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
        lbl_error_origin = (TextView) findViewById(R.id.lbl_error_origin);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnFollowin = (Button) findViewById(R.id.btn_followuP);

        txtFolio.addTextChangedListener(watcher(validateFolio()));
        txtOrigin.addTextChangedListener(watcher(validateOrigin()));
        txtDestination.addTextChangedListener(watcher(validateDestination()));

        textAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        return new ElementDTO(txtDestination, true, Constants.REGEXP_DESTINATION, "Destination", lbl_error_destination, Constants.ERROR_CODE_OK);
    }

    public void query(String item) {
        FolioDAO fa = new FolioDAO();
        List<String> ls = new ArrayList<>();
        fod.setIdFolio(Integer.parseInt(textAutoComplete.getText().toString()));
        ls = fa.select(fod);

        fod.setIdFolio(Integer.parseInt(ls.get(0).toString()));
        fod.setBeginning(ls.get(1));
        fod.setDestination(ls.get(2));
        fod.setStatus(ls.get(3));

        txtFolio.setText(ls.get(0).toString());
        txtOrigin.setText(ls.get(1).toString());
        txtDestination.setText(ls.get(2).toString());

        Intent r = new Intent(this, ActivitySetFolio.class);
        r.putExtra("FolioDTO", fod);

        btnNext.setVisibility(View.GONE);
         btnFollowin.setVisibility(View.VISIBLE);

        txtFolio.setEnabled(false);
        txtOrigin.setEnabled(false);
        txtDestination.setEnabled(false);

    }

    public void goToFollow(View view) {
        Intent r = new Intent(this, FollowActivity.class);
        r.putExtra("FolioDTO", fod);
        startActivity(r);
        limpiarCampos();
    }

    public void goToSetFolio(View view) {
        if (Util.isValidComponent(validateFolio()))
        {
            if (Util.isValidComponent(validateFolio()))
            {
                if (Util.isValidComponent(validateFolio()))
                {

                    fod.setIdFolio(Integer.parseInt(txtFolio.getText().toString()));
                    fod.setBeginning(txtOrigin.getText().toString());
                    fod.setDestination(txtDestination.getText().toString());
                    fod.setStatus(Constants.NA);

                    Intent  h= new Intent(this, ActivitySetFolio.class);
                    h.putExtra("FolioDTO", fod);
                    startActivity(h);

                    limpiarCampos();
                }
            }
        }
    }

    public void limpiarCampos()
    {
            textAutoComplete.setText("");
            txtFolio.setText("");
            txtOrigin.setText("");
            txtDestination.setText("");
            lblError.setText("");
            lbl_error_destination.setText("");
            lbl_error_origin.setText("");
    }

}
