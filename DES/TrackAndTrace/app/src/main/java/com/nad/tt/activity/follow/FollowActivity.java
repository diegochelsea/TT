package com.nad.tt.activity.follow;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nad.tt.activity.login.R;
import com.nad.tt.comun.dto.folio.FolioDTO;

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

    private Button btnActiveHide;
    private Button btnUnactiveHide;

    private Drawable drawableActiveHide;
    private Drawable drawableUnactiveHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        FolioDTO folioDTO =  new FolioDTO(2222222,"USA","CANADA","ch");
        init();
        getFolio(folioDTO);
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
            case "na":
                btnCheckin.setEnabled(false);
                btnOnFly.setEnabled(false);
                btnCustom.setEnabled(false);
                btnDelivered.setEnabled(false);
                break;
            case "pu":
                btnPickUp.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnOnFly.setEnabled(false);
                btnCustom.setEnabled(false);
                btnDelivered.setEnabled(false);
                break;
            case "ch":
                btnCheckin.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnCheckin.setEnabled(false);
                btnCustom.setEnabled(false);
                btnDelivered.setEnabled(false);
                break;
            case "onf":
                btnOnFly.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnCheckin.setEnabled(false);
                btnOnFly.setEnabled(false);
                btnDelivered.setEnabled(false);
                break;
            case "cc":
                btnCustom.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnCheckin.setEnabled(false);
                btnCustom.setEnabled(false);
                btnOnFly.setEnabled(false);
                break;
            case "del":
                btnDelivered.setBackground(drawableActiveHide);
                btnPickUp.setEnabled(false);
                btnCheckin.setEnabled(false);
                btnCustom.setEnabled(false);
                btnOnFly.setEnabled(false);
                btnDelivered.setEnabled(false);
                break;
        }

    }




}
