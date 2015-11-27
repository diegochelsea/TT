package com.nad.tt.activity.follow;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nad.tt.activity.folio.FoliosActivity;
import com.nad.tt.activity.login.R;
import com.nad.tt.comun.dto.folio.FolioDTO;
import com.nad.tt.comun.dto.folio.FolioDTOS;
import com.nad.tt.dao.folio.FolioDAO;
import com.nad.tt.util.Constants;
import com.nad.tt.util.Util;

/**
 * Created by TI-MAURICIO on 15/11/2015.
 */
public class FollowActivity extends Activity {

    private TextView lblFolio;
    private TextView lblSorce;
    private TextView lblReceiver;

    private Button btnPickUp;
    private Button btnCheckin;
    private Button btnOnFly;
    private Button btnCustom;
    private Button btnDelivered;

    private Button btnBackFollow;

    private Button btnActiveHide;
    private Button btnUnactiveHide;

    private Drawable drawableActiveHide;
    private Drawable drawableUnactiveHide;

    FolioDTO fo =  null;
    FolioDTOS fos =  null;
    FolioDAO folioDAO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);

        init();

        //folio del intent anterior
        Intent i = getIntent();
        fos = (FolioDTOS) i.getSerializableExtra("FolioDTOS");
        getFolio(fos);
    }

    private void init()
    {
        folioDAO = new FolioDAO();
        fo = new FolioDTO();

        lblFolio = (TextView)findViewById(R.id.lbl_folio);
        lblSorce = (TextView)findViewById(R.id.lbl_sorce_set);
        lblReceiver = (TextView)findViewById(R.id.lbl_receiver_set);

        btnPickUp = (Button)findViewById(R.id.btn_pick_up);
        btnCheckin = (Button)findViewById(R.id.btn_checkin);
        btnOnFly = (Button)findViewById(R.id.btn_on_fly);
        btnCustom = (Button)findViewById(R.id.btn_custom);
        btnDelivered = (Button)findViewById(R.id.btn_deliveres);

        btnBackFollow = (Button)findViewById(R.id.btn_back_follow);

        btnActiveHide = (Button)findViewById(R.id.btn_hide_active);
        btnUnactiveHide = (Button)findViewById(R.id.btn_hide_unactive);

        drawableActiveHide = btnActiveHide.getBackground();
        drawableUnactiveHide = btnUnactiveHide.getBackground();

    }

    public void getFolio(FolioDTOS folioDTOS)
    {
        String st = "";
        lblFolio.setText(String.valueOf(folioDTOS.idFolio));
        lblSorce.setText(folioDTOS.beginning);
        lblReceiver.setText(folioDTOS.destination);
        st = folioDTOS.status;


        switch (st)
        {
            case Constants.NA:
                btnCheckin.setEnabled(false);
                btnOnFly.setEnabled(false);
                btnCustom.setEnabled(false);
                btnDelivered.setEnabled(false);

                //Estara habilitado solo el boton PickUp
                //Llenamos el constructor para actualizar a pc
                fo.idFolio=  Integer.parseInt(lblFolio.getText().toString());
                fo.beginning = lblSorce.getText().toString();
                fo.destination = lblReceiver.getText().toString();
                fo.status = Constants.PICKUP;

                break;
            case Constants.PICKUP:
                btnPickUp.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnOnFly.setEnabled(false);
                btnCustom.setEnabled(false);
                btnDelivered.setEnabled(false);

                fo.idFolio=  Integer.parseInt(lblFolio.getText().toString());
                fo.beginning = lblSorce.getText().toString();
                fo.destination = lblReceiver.getText().toString();
                fo.status = Constants.CHEKIN;

                break;
            case Constants.CHEKIN:
                btnPickUp.setBackground(drawableActiveHide);
                btnCheckin.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnCheckin.setEnabled(false);
                btnCustom.setEnabled(false);
                btnDelivered.setEnabled(false);

                fo.idFolio=  Integer.parseInt(lblFolio.getText().toString());
                fo.beginning = lblSorce.getText().toString();
                fo.destination = lblReceiver.getText().toString();
                fo.status = Constants.ONFLY;
                break;
            case Constants.ONFLY:
                btnPickUp.setBackground(drawableActiveHide);
                btnCheckin.setBackground(drawableActiveHide);
                btnOnFly.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnCheckin.setEnabled(false);
                btnOnFly.setEnabled(false);
                btnDelivered.setEnabled(false);

                fo.idFolio=  Integer.parseInt(lblFolio.getText().toString());
                fo.beginning = lblSorce.getText().toString();
                fo.destination = lblReceiver.getText().toString();
                fo.status = Constants.CUSTOMC;
                break;
            case Constants.CUSTOMC:
                btnPickUp.setBackground(drawableActiveHide);
                btnCheckin.setBackground(drawableActiveHide);
                btnOnFly.setBackground(drawableActiveHide);
                btnCustom.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnCheckin.setEnabled(false);
                btnCustom.setEnabled(false);
                btnOnFly.setEnabled(false);

                fo.idFolio=  Integer.parseInt(lblFolio.getText().toString());
                fo.beginning = lblSorce.getText().toString();
                fo.destination = lblReceiver.getText().toString();
                fo.status = Constants.DELIVERED;
                break;
            case Constants.DELIVERED:
                btnPickUp.setBackground(drawableActiveHide);
                btnCheckin.setBackground(drawableActiveHide);
                btnOnFly.setBackground(drawableActiveHide);
                btnCustom.setBackground(drawableActiveHide);
                btnDelivered.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnCheckin.setEnabled(false);
                btnCustom.setEnabled(false);
                btnOnFly.setEnabled(false);
                btnDelivered.setEnabled(false);
                break;
        }

    }

    public void goToBack(View view) {
        System.exit(0);
    }

    public void setStatus(View view) {

        FolioDTO folio = new FolioDTO();
        folio.idFolio = fo.idFolio;
        folio.beginning = fo.beginning;
        folio.destination =fo.destination;
        folio.status = fo.status;
        folio = folioDAO.updateFolio(folio);

        FolioDTOS folios = new FolioDTOS();
        folios.idFolio =  fo.getIdFolio();
        folios.beginning =  fo.getBeginning();
        folios.destination =  fo.getDestination();
        folios.status =  fo.getStatus();
        getFolio(folios);
        Util.showToast(String.valueOf(folio.idFolio)+" "+folio.msgError, this);
    }



}
