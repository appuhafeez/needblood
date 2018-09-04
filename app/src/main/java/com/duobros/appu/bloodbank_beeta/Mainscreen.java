package com.duobros.appu.bloodbank_beeta;


import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
//import android.app.Fragment;
import android.view.View;

public class Mainscreen extends FragmentActivity implements BloodPost_frag.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        //Button b=(Button)findViewById(R.id.aboutus);
        //Fragment fragment;
        Fragment fragment;
        fragment = new BloodReq();
        FragmentManager fm1= getSupportFragmentManager();
        FragmentTransaction ft1= fm1.beginTransaction();
        ft1.add(R.id.group, fragment);
        ft1.commit();
        /*b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Mainscreen.this,AboutUs_act.class);
                startActivity(i);
            }
        });*/



    }
    public void fragchange(View view){
        Fragment fragment1;
        if(view == findViewById(R.id.breq)) {
            fragment1 = new BloodReq();
            FragmentManager fm= getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.group, fragment1);
            ft.addToBackStack(null);
            ft.commit();
        }
        if(view==findViewById(R.id.bpost)){
            fragment1 = new BloodPost_frag();
            FragmentManager fm= getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.remove(fragment1);
            ft.replace(R.id.group, fragment1);
            ft.addToBackStack(null);
            ft.commit();
        }
        if (view==findViewById(R.id.bacc)){
            fragment1 = new Acc_frag();
            FragmentManager fm= getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.group,fragment1);
            ft.addToBackStack(null);
            ft.commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
