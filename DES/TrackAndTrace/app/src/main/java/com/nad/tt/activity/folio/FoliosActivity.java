package com.nad.tt.activity.folio;

import android.app.Activity;
import android.content.Intent;
import android.content.Loader;
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
import com.nad.tt.comun.dto.UserDTO;
import com.nad.tt.comun.dto.folio.FolioDTO;
import com.nad.tt.comun.dto.folio.FolioDTOS;
import com.nad.tt.comun.enumeration.ElementDTO;
import com.nad.tt.dao.folio.FolioDAO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.GlobalClass;
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
    private List<FolioDTO> folios=null;
    private AutoCompleteTextView textAutoComplete;
    private ListView list;
    private String item;
    private FolioDTOS fods;
    private FolioDAO folioDAO = null;
    private UserDTO userDTO;
    private  TextView lblIdStatusHidde;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folios);
        init();
        fods = new FolioDTOS();
    }

    public void init() {

        folioDAO = new FolioDAO();
        textAutoComplete = (AutoCompleteTextView) findViewById(R.id.txtautoComplete_folio);

        lblIdStatusHidde = (TextView)findViewById(R.id.lbl_id_status_hidde);

        txtFolio = (EditText) findViewById(R.id.txt_folio);
        txtOrigin = (EditText) findViewById(R.id.txt_origin);
        txtDestination = (EditText) findViewById(R.id.txtDestination);


        lblError = (TextView) findViewById(R.id.lbl_error_folio);
        lbl_error_destination = (TextView) findViewById(R.id.lbl_error_destination);
        lbl_error_origin = (TextView) findViewById(R.id.lbl_error_origin);

        btnNext = (Button) findViewById(R.id.btn_next);
        btnFollowin = (Button) findViewById(R.id.btn_followuP);

        txtFolio.addTextChangedListener(watcher(validateFolio()));
        txtOrigin.addTextChangedListener(watcher(validateOrigin()));
        txtDestination.addTextChangedListener(watcher(validateDestination()));

        getGlobal();

        String msgError = Constants.EMPTY_STRING;

        if (Constants.EMPTY_STRING.equals(msgError)) {
            msgError = initFolios();
        }

        Util.showToast(msgError, this);

    }

    private void getGlobal()
    {
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final int id_usuario = globalVariable.getIdUsuario();
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


    public void goToFollow(View view) {
        Intent r = new Intent(this, FollowActivity.class);
        r.putExtra("FolioDTOS", fods);
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

                    fods.setIdFolio(Integer.parseInt(txtFolio.getText().toString()));
                    fods.setBeginning(txtOrigin.getText().toString());
                    fods.setDestination(txtDestination.getText().toString());
                    fods.setStatus(Constants.NA);

                    Intent  h= new Intent(this, ActivitySetFolio.class);
                    h.putExtra("FolioDTOS", fods);
                    startActivity(h);

                    limpiarCampos();
                }
            }
        }
    }

    private String initFolios()
    {
        Log.d("initFolios", "1");
        String result= Constants.EMPTY_STRING;
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final int id_usuario = globalVariable.getIdUsuario();
        Log.d("initFolios", "1"+id_usuario);
        try {
            folios = folioDAO.selectAllFolio(String.valueOf(id_usuario));
            if (folios!= null && !folios.isEmpty()) {
                String[] foliosArray = new  String[folios.size()];
                for (int i = 0; i  < folios.size(); i++) {
                    foliosArray[i] = String.valueOf(folios.get(i).idFolio);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line, foliosArray);
                textAutoComplete.setAdapter(adapter);

                textAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Log.d(Constants.LOG_NAD, "OnItem positipon: " + position + ", id: " + id);
                        int pos = getFolioPosition(textAutoComplete.getText().toString());
                        Log.d("POSITION",""+pos);
                        Util.showToast(getFolio(pos), getApplicationContext());
                    }
                });


            }
            else {
                result = "Not exists folios, ¡Try later!";
            }

        }catch (Exception e)
        {
            Log.d(Constants.LOG_NAD, e.getMessage());
            result = "Error: Ocurred an error to select Users";
        }

        return result;
    }

    private int getFolioPosition(String name) {
        for (int i = 0; i  < folios.size(); i++) {
            if (name.equals(String.valueOf(folios.get(i).idFolio))) {
                return i;
            }
        }
        return 0;
    }

    private String getFolio(int pos) {
        String msgError = Constants.EMPTY_STRING;
        Log.d(Constants.LOG_NAD, "Inicia getFolio");
        try {
            FolioDTO dto = folios.get(pos);
            dto = folioDAO.getUniqFolio(dto);

                Log.d(Constants.LOG_NAD, "add values to view: ");
                btnFollowin.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.GONE);

                txtFolio.setText(String.valueOf(dto.idFolio));
                txtOrigin.setText(dto.beginning);
                txtDestination.setText(dto.destination);
                lblIdStatusHidde.setText(dto.status);

                txtFolio.setEnabled(false);
                txtOrigin.setEnabled(false);
                txtDestination.setEnabled(false);

                fods.setIdFolio(Integer.parseInt(txtFolio.getText().toString()));
                fods.setDestination(txtDestination.getText().toString());
                fods.setBeginning(txtOrigin.getText().toString());
                fods.setStatus(lblIdStatusHidde.getText().toString());

        } catch (Exception e) {
            Log.d(Constants.LOG_NAD, e.getMessage());
            msgError = "Error: ¡Ocurred an error to search folio, try later!";
        }
        return msgError;
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
