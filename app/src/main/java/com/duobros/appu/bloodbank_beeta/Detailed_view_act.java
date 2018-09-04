package com.duobros.appu.bloodbank_beeta;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Detailed_view_act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view_act);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        TextView bloodgrp=(TextView)findViewById(R.id.blood_grp);
        TextView addr1=(TextView)findViewById(R.id.addr);
        Button call=(Button)findViewById(R.id.call);

        Bundle bundle = getIntent().getExtras();

        String grp=bundle.getString("grp");
        String addr=bundle.getString("addr");
        final String phone=bundle.getString("phone");

        bloodgrp.setText(grp);
        addr1.setText(addr);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:"+phone));
                if (ActivityCompat.checkSelfPermission(Detailed_view_act.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(phoneIntent);
            }
        });

    }

}
