package com.nad.tt.activity.follow;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nad.tt.activity.folio.FoliosActivity;
import com.nad.tt.activity.login.R;
import com.nad.tt.comun.dto.folio.FolioDTO;
import com.nad.tt.util.Constants;

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

    FolioDTO fo =  new FolioDTO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);

        FolioDTO folioDTO =  new FolioDTO();

        init();

        Intent i = getIntent();
        fo = (FolioDTO) i.getSerializableExtra("FolioDTO");
        getFolio(fo);
    }

    private void init()
    {
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

    public void getFolio(FolioDTO folioDTO)
    {
        String st = "";
        lblFolio.setText(String.valueOf(folioDTO.idFolio));
        lblSorce.setText(folioDTO.beginning);
        lblReceiver.setText(folioDTO.destination);

        st = folioDTO.status;

        switch (st)
        {
            case Constants.NA:
                btnCheckin.setEnabled(false);
                btnOnFly.setEnabled(false);
                btnCustom.setEnabled(false);
                btnDelivered.setEnabled(false);
                break;
            case Constants.PICKUP:
                btnPickUp.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnOnFly.setEnabled(false);
                btnCustom.setEnabled(false);
                btnDelivered.setEnabled(false);
                break;
            case Constants.CHEKIN:
                btnPickUp.setBackground(drawableActiveHide);
                btnCheckin.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnCheckin.setEnabled(false);
                btnCustom.setEnabled(false);
                btnDelivered.setEnabled(false);
                break;
            case Constants.ONFLY:
                btnPickUp.setBackground(drawableActiveHide);
                btnCheckin.setBackground(drawableActiveHide);
                btnOnFly.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnCheckin.setEnabled(false);
                btnOnFly.setEnabled(false);
                btnDelivered.setEnabled(false);
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
}
