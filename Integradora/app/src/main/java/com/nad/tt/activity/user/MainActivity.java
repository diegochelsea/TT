package com.nad.tt.activity.user;

import android.content.res.Resources;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.Toast;

import com.nad.integradora.R;

public class MainActivity extends Activity implements TabHost.OnTabChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec=tabs.newTabSpec("Personal");
        spec.setContent(R.id.tab01);
        spec.setIndicator("",
                res.getDrawable(android.R.drawable.ic_input_get));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("Address");
        spec.setContent(R.id.tab02);
        spec.setIndicator("",
                res.getDrawable(android.R.drawable.ic_menu_agenda));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("Login");
        spec.setContent(R.id.tab03);
        spec.setIndicator("",
                res.getDrawable(android.R.drawable.ic_menu_save));
        tabs.addTab(spec);

        tabs.setOnTabChangedListener(this);
        tabs.setCurrentTab(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onTabChanged(String tabId) {
        Toast toast = Toast.makeText(getApplicationContext(), "Change... to " + tabId, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
