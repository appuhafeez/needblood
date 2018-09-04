package com.duobros.appu.bloodbank_beeta;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class Acc_frag extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_acc_frag, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        Button del=(Button)view.findViewById(R.id.del);
        Button accs=(Button)view.findViewById(R.id.chan);
        Button feedb=(Button)view.findViewById(R.id.feedb);
        //final EditText feed=(EditText)view.findViewById(R.id.feed);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   // File bloodfile = Environment.getExternalStoragePublicDirectory("Bloodbank");
                    File bloodfile=getActivity().getFilesDir();
                    if (!bloodfile.exists())
                        bloodfile.mkdir();
                    File file = new File(bloodfile, "required.txt");
                    if (!file.exists())
                        file.createNewFile();
                    file.delete();
                    Toast.makeText(getActivity(),"Records deleted successfully",Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }
            }
        });
        accs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token="";
                Intent i=new Intent(getActivity(),Signup.class);
                File bloodfile = getActivity().getFilesDir();
                if (!bloodfile.exists())
                    bloodfile.mkdir();
                File file = new File(bloodfile, "token.txt");
                try {
                    if (!file.exists())
                        file.createNewFile();
                    FileReader fr=new FileReader(file);
                    Scanner sc=new Scanner(fr);
                    token=sc.nextLine();
                }catch (Exception e){

                }
                i.putExtra("token",token);
                startActivity(i);
            }
        });
        feedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   File bloodfile = Environment.getExternalStoragePublicDirectory("Bloodbank");
                    if (!bloodfile.exists())
                        bloodfile.mkdir();
                    File file = new File(bloodfile, "user.txt");
                    if(!file.exists()){
                        file.createNewFile();

                    }
                    FileReader fr=new FileReader(file);
                    Scanner sc=new Scanner(fr);
                    String name=sc.nextLine();
                    sc.nextLine();
                    sc.nextLine();
                   // sc.nextLine();
                    String phone=sc.nextLine();
                    String Emaillist[]={};
                    Intent email=new Intent(Intent.ACTION_SEND);
                    email.setType("text/email");
                    email.putExtra(Intent.EXTRA_EMAIL, Emaillist);
                    email.putExtra(Intent.EXTRA_SUBJECT,"");
                    email.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.duobros.appu.bloodbank_beeta");
                    startActivity(email);

                }catch (Exception e) {
                    Toast.makeText(getActivity(), e.toString() + " at big", Toast.LENGTH_LONG).show();
                }
            }
        });
        }
}
