package com.duobros.appu.bloodbank_beeta;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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


public class Recyclerview_frag extends Fragment {

    private RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private MyAdapter adapter;



    public Recyclerview_frag() {

    }

    public static Recyclerview_frag newInstance(String param1, String param2) {
        Recyclerview_frag fragment = new Recyclerview_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout=inflater.inflate(R.layout.fragment_recyclerview_frag, container, false);
        recyclerView=(RecyclerView)layout.findViewById(R.id.drawarlist);


        final List<Info> data=getData(getActivity());


        adapter=new MyAdapter(getActivity(),data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(getActivity(),"onClick"+position+" "+data.get(position),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                //Toast.makeText(getActivity(),"onLongClick"+position+" "+data.get(position),Toast.LENGTH_LONG).show();
            }
        }));
        return layout;
    }

    public static List<Info> getData(Context cn){
        List<Info> data1=new ArrayList<>();
        int[] icons={R.drawable.ic_call_black_24dp,R.drawable.ic_error_outline_black_24dp};
        //String[] ph={"8331832949","8331832949"};
        //String[] titles={"testing 1","testing 2"};
        //String[] desc={"testing desc 1","testing desc 2"};
        /*for(int i=0;i<titles.length;i++){
            Info current=new Info();
            current.title=titles[i];
            current.ph=ph[i];
            current.addr=desc[i];
            current.iconId=icons[i];
            data1.add(current);
        }*/

        try {
            File bloodfile = cn.getFilesDir();
            if (!bloodfile.exists())
                bloodfile.mkdir();
            File file = new File(bloodfile, "required.txt");
            if (!file.exists())
                file.createNewFile();
            FileReader fin1 = new FileReader(file);
            Scanner sc1 = new Scanner(fin1);

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String dat = dateFormat.format(cal.getTime());
            Date date12 = sdf.parse(dat);
            if (sc1.hasNextLine()) {
                int i = 0;
                while (sc1.hasNextLine()) {
                    String st = sc1.nextLine();
                    //Toast.makeText(getActivity(),st,Toast.LENGTH_LONG).show();
                    Date date2 = sdf.parse(st);
                    if (date2.after(date12)) {
                        Info current = new Info();
                        current.title = sc1.nextLine()+" required at";
                        current.ph = sc1.nextLine();
                        current.addr = sc1.nextLine();
                        current.iconId = icons[0];
                        data1.add(current);
                        /*Bloodgrp.add(sc1.nextLine());
                        phone.add(sc1.nextLine());
                        address.add(sc1.nextLine());
                        items.add(Bloodgrp.get(i) + " required at " + address.get(i));*/
                        i++;
                    } else {
                        sc1.nextLine();
                        sc1.nextLine();
                        sc1.nextLine();
                    }
                }
                if(data1.size()<1){
                    Info current = new Info();
                    current.title = "No notifications available";
                    current.ph = null;
                    current.addr = null;
                    current.iconId = icons[0];
                    data1.add(current);
                }
            }else{
                Info current = new Info();
                current.title = "No notifications available";
                current.ph = null;
                current.addr = null;
                current.iconId = icons[1];
                data1.add(current);
            }
        }catch (Exception e){

        }
            return data1;
        }


    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;
        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
            Log.d("APPU","constructor invoked");
            this.clickListener=clickListener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {

                    return super.onSingleTapUp(e);
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clickListener!=null)
                    {
                       // clickListener.onLongClick(child,recyclerView.getChildPosition(child));
                    }

                    super.onLongPress(e);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e))
            {
                clickListener.onClick(child,rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d("APPU","ote invoked"+e);
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }



    public static interface ClickListener {
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }
}
