package com.duobros.appu.bloodbank_beeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Splash extends AppCompatActivity {
    private Intent myintent;
    File bloodfile,file;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Intent i1=new Intent(this,Mainactivity123.class);
        //startActivity(i1);
        myintent = new Intent(this, Mainactivity123.class);


        FirebaseMessaging.getInstance().subscribeToTopic("Bloodbank");
        String token=FirebaseInstanceId.getInstance().getToken();
        try {
        //bloodfile=    Environment.getExternalStorageDirectory();
        //bloodfile = Environment.getExternalStoragePublicDirectory("Bloodbank");
            bloodfile= getFilesDir();
        if (!bloodfile.exists())
            bloodfile.mkdir();
        file = new File(bloodfile, "token.txt");
        if(!file.exists()){
            Toast.makeText(this, "Please wait identifying device", Toast.LENGTH_LONG).show();
        }



        if (!file.exists()) {
            file.createNewFile();
            FileWriter fout1 = new FileWriter(file);
            fout1.write("hello");
            fout1.close();
        }
            splashScreen(500, file);
    }catch (Exception e){
        Toast.makeText(this,e.toString()+" at big", Toast.LENGTH_LONG).show();
    }finally {

    }


    }
    public void splashScreen (final int x,File bf)throws IOException
    {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(x);

                   String token="";
                    token=FirebaseInstanceId.getInstance().getToken();
                    //FileWriter fout = null;
                    try {


                        //File bloodf = Environment.getExternalStoragePublicDirectory("Bloodbank");
                        File bloodf=  getFilesDir();
                        if (!bloodf.exists())
                            bloodf.mkdir();
                        File user = new File(bloodf, "user.txt");
                        token=FirebaseInstanceId.getInstance().getToken();
                        if(!user.exists()) {
                            user.createNewFile();
                        }
                        FileReader fr=new FileReader(user);
                        Scanner sc=new Scanner(fr);
                        token=FirebaseInstanceId.getInstance().getToken();
                        //Toast.makeText(Splash.this,token,Toast.LENGTH_LONG).show();

                        FileWriter  fout = new FileWriter(file);
                        if(!sc.hasNextLine()) {
                            Intent i = new Intent(Splash.this, Signup.class);
                            i.putExtra("token", token);
                         //   Toast.makeText(Splash.this,token,Toast.LENGTH_LONG).show();

                            startActivity(i);
                        }else{
                            fout.write(token);
                            fout.close();
                            startActivity(myintent);
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.getStackTraceString(e);
                        Toast.makeText(Splash.this, e.getStackTrace()+ "at mid", Toast.LENGTH_LONG).show();

                    }
                    //Formatter fmt1=new Formatter();
                    //fmt1.format("%s",token);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Splash.this, e.toString()+ "at end", Toast.LENGTH_LONG).show();
                    Toast.makeText(Splash.this, e.getStackTrace().toString(), Toast.LENGTH_LONG).show();
                }

            }
        }).run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
