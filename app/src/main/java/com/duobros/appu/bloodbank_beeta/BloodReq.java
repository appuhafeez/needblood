package com.duobros.appu.bloodbank_beeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class BloodReq extends ListFragment {





    List<String> Bloodgrp=new ArrayList<>();
    List<String> phone=new ArrayList<>();
    List<String> address=new ArrayList<>();
    List<String> items=new ArrayList<>();
    //String[] Bloodgrp,phone,address;
   // ListView lv;
    ArrayAdapter<String> listAdapter ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            //File bloodfile= Environment.getExternalStoragePublicDirectory("Bloodbank");
            File bloodfile=getActivity().getFilesDir();
            if (!bloodfile.exists())
                bloodfile.mkdir();
            File file=new File(bloodfile,"required.txt");
            if (!file.exists())
                file.createNewFile();
            FileReader fin1=new FileReader(file);
            Scanner sc1=new Scanner(fin1);

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String dat=dateFormat.format(cal.getTime());
            Date date1 = sdf.parse(dat);
            if(sc1.hasNextLine()) {
                int i = 0;
                while (sc1.hasNextLine()) {
                    String st=sc1.nextLine();
                    //Toast.makeText(getActivity(),st,Toast.LENGTH_LONG).show();
                    Date date2 = sdf.parse(st);
                    if(date2.after(date1)){
                        Bloodgrp.add(sc1.nextLine());
                        phone.add(sc1.nextLine());
                        address.add(sc1.nextLine());
                        items.add(Bloodgrp.get(i) + " required at " + address.get(i));
                        i++;
                    }else{
                        sc1.nextLine();
                        sc1.nextLine();
                        sc1.nextLine();
                    }
                }
                setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, items));
            }else{
                String[] f=new String[]{"No requests Found"};
                setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1,f));
                Toast.makeText(getActivity(),"No requests found", Toast.LENGTH_LONG).show();
            }
            //db.close();
        }catch (Exception e){
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }
       //String[] item_menu;
       /*String[] item_menu = new String[]{
            "Mercury", "Venus", "Earth", "Mars",
                    "Jupiter", "Saturn", "Uranus", "Neptune"
        };

        if (getArguments() != null) {

        }*/

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        /*try {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("Blood",null,null);
            db.execSQL("create table if not exists breq(id integer primary key autoincrement, grp text[5], phone text[20],addr text[100])");
            db.execSQL("insert into breq(grp,phone,addr) values('O+','+918331832949','MB-82,main hospital area')");
            Cursor c=db.rawQuery("select * from breq", null);
            int count =c.getCount();
            int i;
            String[] item_menu=new String[count];
            Bloodgrp=new String[count];
            phone=new String[count];
            address=new String[count];
            if (count==0){
                Toast.makeText(getActivity(),"NO BLOOD REQUIRED PERSONS FOUND",Toast.LENGTH_SHORT).show();
            }
            for (i=0;c.moveToNext();i++){
                Bloodgrp[i]=c.getString(c.getColumnIndex("grp"));
                phone[i]=c.getString(c.getColumnIndex("phone"));
                address[i]=c.getString(c.getColumnIndex("addr"));
                item_menu[i]=c.getString(c.getColumnIndex("grp"))+" required at "+c.getString(c.getColumnIndex("addr"));
            }
            setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, item_menu));
            db.close();
            //    setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,item_menu));

        }catch (Exception e){

        }finally {

        }*/




        //lv=(ListView)view.findViewById(R.id.ll_list);
        /*String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        listAdapter.add( "Ceres" );
        listAdapter.add("Pluto" );
        listAdapter.add( "Haumea" );
        listAdapter.add( "Makemake" );
        listAdapter.add( "Eris" );

        // Set the ArrayAdapter as the ListView's adapter.
        lv.setAdapter( listAdapter );*/
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent i=new Intent(getActivity(),Detailed_view_act.class);
        i.putExtra("phone",phone.get(position).toString());
        i.putExtra("addr", address.get(position).toString());
        i.putExtra("grp",Bloodgrp.get(position).toString());
        startActivity(i);
    }

}
