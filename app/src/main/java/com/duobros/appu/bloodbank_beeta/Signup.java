package com.duobros.appu.bloodbank_beeta;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Signup extends AppCompatActivity implements AsyncResponse{

    Button post;
    private Toolbar toolbar;
    EditText name,addr,phone;
    Spinner area,state,grp;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Bundle bundle = getIntent().getExtras();
        String token=bundle.getString("token");

        toolbar=(Toolbar)findViewById(R.id.appb);
        toolbar.inflateMenu(R.menu.menu1);
        setSupportActionBar(toolbar);

        post=(Button)findViewById(R.id.spost);
        name=(EditText)findViewById(R.id.sname);
        addr=(EditText)findViewById(R.id.saddress);
        phone=(EditText)findViewById(R.id.sphone);
        area=(Spinner)findViewById(R.id.sArea);
        state=(Spinner)findViewById(R.id.sstate);
        grp=(Spinner)findViewById(R.id.sbloodgrp);
        //pb=(ProgressBar)findViewById(R.id.progress_loader);
        //pb.setVisibility(View.INVISIBLE);
        List<String> bloodgrps = new ArrayList<String>();
        bloodgrps.add("O+");
        bloodgrps.add("O-");
        bloodgrps.add("A+");
        bloodgrps.add("A-");
        bloodgrps.add("B+");
        bloodgrps.add("B-");
        bloodgrps.add("AB+");
        bloodgrps.add("AB-");

        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(Signup.this,android.R.layout.simple_spinner_dropdown_item,bloodgrps);
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(BloodPost_frag.this,R.layout.support_simple_spinner_dropdown_item, bloodgrps);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grp.setAdapter(dataAdapter);

        final List<String> states=new ArrayList<String>();
        states.add("Andhra Pradesh");
        states.add("Telangana");

        ArrayAdapter<String> dataAdapter1= new ArrayAdapter<String>(Signup.this,android.R.layout.simple_spinner_dropdown_item,states);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(dataAdapter1);
        //state.setOnItemClickListener(new);
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> area12 = new ArrayList<String>();
                if (position == 0) {

                    area12.add("Anantapur");
                    area12.add("Chittoor");
                    area12.add("Kakinada");
                    area12.add("Guntur");
                    area12.add("Vijayawada");
                    area12.add("Kadapa");
                    area12.add("Machilipatnam");
                    area12.add("Kurnool");
                    area12.add("Nellore");
                    area12.add("Prakasam");
                    area12.add("Srikakulam");
                    area12.add("Visakhapatnam");
                    area12.add("Vizianagaram");
                    area12.add("Eluru");

                } else if (position == 1) {
                    //List<String> area12=new ArrayList<String>();


                    area12.add("Adilabad");
                    area12.add("Kothagudem");
                    area12.add("Hyderabad");
                    area12.add("Jagtial");
                    area12.add("Jangaon");
                    area12.add("Bhupalpalle");
                    area12.add("Gadwal");
                    area12.add("Kamareddy");
                    area12.add("Karimnagar");
                    area12.add("Khammam");
                    area12.add("Asifabad");
                    area12.add("Mahabubabad");
                    area12.add("Mahabubnagar");
                    area12.add("Mancherial");
                    area12.add("Medak");
                    area12.add("Medchal");
                    area12.add("Nagarkurnool");
                    area12.add("Nalgonda");
                    area12.add("Nirmal");
                    area12.add("Nizamabad");
                    area12.add("Peddapalli");
                    area12.add("Sircilla");
                    area12.add("Sangareddi");
                    area12.add("Siddipet");
                    area12.add("Suryapet");
                    area12.add("Vikarabad");
                    area12.add("Wanaparthy");
                    area12.add("Warangal");
                    area12.add("Bhongir");

                }
                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(Signup.this, android.R.layout.simple_spinner_dropdown_item, area12);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                area.setAdapter(dataAdapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                post.setText("LOADING...");
                post.setTextColor(Signup.this.getResources().getColor(R.color.black));
                //ProgressDialog.show(Signup.this, "Loading", "Wait while loading...");

                boolean fl=true;
                final String name1 = name.getText().toString();
                final String phone1 = phone.getText().toString();
                final String addr1 = addr.getText().toString();
                final String state1 = state.getSelectedItem().toString();
                final String area1 = area.getSelectedItem().toString();
                final String grp1 = grp.getSelectedItem().toString();

                final String token1 = FirebaseInstanceId.getInstance().getToken();
                checknet cn2 = new checknet(Signup.this);

                if((TextUtils.isEmpty(name1) && TextUtils.isEmpty(addr1) && TextUtils.isEmpty(phone1))){
                    fl=false;
                }

                if(fl) {
                    if(!(phone1.length()>=14 || phone1.length()<=9)) {
                        if (cn2.checknetw()) {
                            Toast.makeText(Signup.this, "Loading Please wait", Toast.LENGTH_LONG).show();
                            try {
                                //File bloodf = Environment.getExternalStoragePublicDirectory("Bloodbank");
                                File bloodf = getFilesDir();
                                if (!bloodf.exists())
                                    bloodf.mkdir();
                                File user = new File(bloodf, "user.txt");

                                if (!user.exists()) {
                                    user.createNewFile();
                                }
                                FileReader fr = new FileReader(user);
                                Scanner sc = new Scanner(fr);


                                FileWriter fout = new FileWriter(user);

                                if (!sc.hasNextLine())
                                    fout.write(token1);
                                fout.close();
                            } catch (Exception e) {

                            }

                            //pb.setVisibility(View.VISIBLE);
                            //pb.setProgress(20);

                            String data = "";
                            try {
                                data = URLEncoder.encode("name", "UTF-8")
                                        + "=" + URLEncoder.encode(name1, "UTF-8");

                                data += "&" + URLEncoder.encode("phone", "UTF-8") + "="
                                        + URLEncoder.encode(phone1, "UTF-8");

                                data += "&" + URLEncoder.encode("addr", "UTF-8")
                                        + "=" + URLEncoder.encode(addr1, "UTF-8");

                                data += "&" + URLEncoder.encode("state", "UTF-8")
                                        + "=" + URLEncoder.encode(state1, "UTF-8");

                                data += "&" + URLEncoder.encode("grp", "UTF-8") + "="
                                        + URLEncoder.encode(grp1, "UTF-8");

                                data += "&" + URLEncoder.encode("area", "UTF-8")
                                        + "=" + URLEncoder.encode(area1, "UTF-8");

                                data += "&" + URLEncoder.encode("token", "UTF-8")
                                        + "=" + URLEncoder.encode(token1, "UTF-8");


                            } catch (Exception e) {
                                Toast.makeText(Signup.this, e.toString() + " 12", Toast.LENGTH_LONG).show();
                            }
                           // pb.setProgress(40);
                            ProgressDialog progress = new ProgressDialog(Signup.this);
                /*progress.setTitle("Connecting Server");
                progress.setMessage("Please wait untle registration complete");
                progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                progress.show();*/

                            Background1 bg = new Background1(Signup.this, progress);
                            try {
                                String result = bg.execute(data, "register").get().toString();

                                Toast.makeText(Signup.this, result, Toast.LENGTH_LONG).show();
                                if (result.equals("success")) {
                                    try {
                                        //File bloodf = Environment.getExternalStoragePublicDirectory("Bloodbank");
                                        File bloodf = getFilesDir();
                                        if (!bloodf.exists())
                                            bloodf.mkdir();
                                        File user = new File(bloodf, "user.txt");
                                        if (!user.exists()) {
                                            user.createNewFile();
                                        }
                                        FileWriter fout = new FileWriter(user);
                                        fout.write(name1 + "\n" + grp1 + "\n" + area1 + "\n" + state1 + "\n" + phone1);
                                        fout.close();


                                    } catch (Exception e) {
                                        Toast.makeText(Signup.this, e.toString(), Toast.LENGTH_LONG).show();
                                    }
                                    Intent i = new Intent(Signup.this, Mainactivity123.class);
                                    startActivity(i);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                                    builder.setTitle("Can't reach server at this moment Please try again")
                                            .setPositiveButton("Goto Settings", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                                }
                                            })
                                            .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            })
                                            .setIcon(R.drawable.ic_rss_feed_black_18dp)
                                            .show();
                                }
                                //pb.setProgress(90);
                                //pb.setVisibility(View.INVISIBLE);
                                post.setText("Signup");
                                post.setTextColor(Signup.this.getResources().getColor(R.color.white));

                            } catch (Exception e) {

                            }
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                            builder.setTitle("Please check your internet")
                                    .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                        }
                                    })
                                    .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .setNeutralButton("WIFI-Settings", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            WifiManager wifiManager = (WifiManager) Signup.this.getSystemService(Context.WIFI_SERVICE);
                                           // if(!wifiManager.isWifiEnabled())
                                                wifiManager.setWifiEnabled(true);
                                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                        }
                                    })
                                    .setIcon(R.drawable.ic_report_black_24dp)
                                    .show();
                        }
                    }else {
                        Toast.makeText(Signup.this,"Incorrect phone number",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Signup.this,"Fields should not be empty",Toast.LENGTH_SHORT).show();
                }
                post.setText("Signup");
                post.setTextColor(Signup.this.getResources().getColor(R.color.white));
            }

        });

    }

    @Override
    public void processFinish(String output) {
        Toast.makeText(Signup.this, output + " 12", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
