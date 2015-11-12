package com.nad.tt.activity.folio;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.nad.tt.activity.login.R;
import com.nad.tt.comun.enumeration.ElementDTO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.Util;

public class FolioActivity extends Activity {
    private EditText txtFolio;
    private TextView lblErrorFolio;
    private EditText txtOrigin;
    private TextView lblErrorOrigin;
    private EditText txtDestination;
    private TextView lblErrorDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folio);
        init();
    }

    private void init() {
        txtFolio = (EditText) findViewById(R.id.txtFolio);
        txtDestination = (EditText) findViewById(R.id.txtDestination);
        txtOrigin = (EditText) findViewById(R.id.txtOrigin);
        lblErrorFolio = (TextView) findViewById(R.id.lblErrorFolio);
        lblErrorOrigin = (TextView) findViewById(R.id.lblErrorOrigin);
        lblErrorDestination = (TextView) findViewById(R.id.lblErrorDestination);

        ElementDTO elementFolio = new ElementDTO(txtFolio, true, Constants.REGEXP_FOLIO_FOLIO, "Folio", lblErrorFolio, Constants.ERROR_CODE_OK);
        txtFolio.addTextChangedListener(watcher(elementFolio));

        ElementDTO elementOrigin = new ElementDTO(txtOrigin, true, Constants.REGEXP_STATUS_DESC, "Origin", lblErrorOrigin, Constants.ERROR_CODE_OK);
        txtOrigin.addTextChangedListener(watcher(elementOrigin));

        ElementDTO elementDestination = new ElementDTO(txtDestination, true, Constants.REGEXP_STATUS_DESC, "Destination", lblErrorDestination, Constants.ERROR_CODE_OK);
        txtDestination.addTextChangedListener(watcher(elementDestination));
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
}
