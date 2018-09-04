package com.duobros.appu.bloodbank_beeta;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BloodPost_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BloodPost_frag#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class BloodPost_frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BloodPost_frag.
     */
    // TODO: Rename and change types and number of parameters
    Button post;
    EditText name,addr,phone;
    Spinner area,state,grp;
    public static BloodPost_frag newInstance(String param1, String param2) {
        BloodPost_frag fragment = new BloodPost_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public BloodPost_frag() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_blood_post_frag, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        post=(Button)view.findViewById(R.id.post);
        name=(EditText)view.findViewById(R.id.name);
        addr=(EditText)view.findViewById(R.id.address);
        phone=(EditText)view.findViewById(R.id.phone);
        area=(Spinner)view.findViewById(R.id.Area);
        state=(Spinner)view.findViewById(R.id.state);
        grp=(Spinner)view.findViewById(R.id.bloodgrp);
        List<String> bloodgrps = new ArrayList<String>();
        bloodgrps.add("O+");
        bloodgrps.add("O-");
        bloodgrps.add("A+");
        bloodgrps.add("A-");
        bloodgrps.add("B+");
        bloodgrps.add("B-");
        bloodgrps.add("AB+");
        bloodgrps.add("AB-");

        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bloodgrps);
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(BloodPost_frag.this,R.layout.support_simple_spinner_dropdown_item, bloodgrps);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grp.setAdapter(dataAdapter);

        final List<String> states=new ArrayList<String>();
        states.add("Andhra Pradesh");
        states.add("Telangana");

        ArrayAdapter<String> dataAdapter1= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,states);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(dataAdapter1);
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

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
                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, area12);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                area.setAdapter(dataAdapter2);


                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //ProgressDialog.show(getActivity(), "Loading", "Wait while loading...");

                        String name1 = name.getText().toString();
                        String phone1 = phone.getText().toString();
                        String addr1 = addr.getText().toString();
                        String state1 = state.getSelectedItem().toString();
                        String area1 = area.getSelectedItem().toString();
                        String grp1 = grp.getSelectedItem().toString();
                        boolean fl = true;
                        if ((TextUtils.isEmpty(name1) && TextUtils.isEmpty(addr1) && TextUtils.isEmpty(phone1))) {
                            fl = false;
                        }

                        if (fl) {
                            if (!(phone1.length() >= 14 || phone1.length() <= 9)) {
                                Toast.makeText(getActivity(), "Loading Please wait", Toast.LENGTH_LONG).show();
                                ProgressDialog pd = new ProgressDialog(getActivity());

                                Background1 bg = new Background1(getActivity(), pd);

                                String data = "";
                                checknet cn2 = new checknet(getActivity());
                                if (cn2.checknetw()) {
                                    try {

                                        Calendar cal = Calendar.getInstance();
                                        cal.add(Calendar.HOUR_OF_DAY, 50);
                                        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                                        String dat = dateFormat.format(cal.getTime());

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

                                        data += "&" + URLEncoder.encode("exp", "UTF-8") + "="
                                                + URLEncoder.encode(dat, "UTF-8");

                                        data += "&" + URLEncoder.encode("area", "UTF-8")
                                                + "=" + URLEncoder.encode(area1, "UTF-8");


                                    } catch (Exception e) {
                                        Toast.makeText(getActivity(), e.toString() + " 12", Toast.LENGTH_LONG).show();
                                    }
                                    try {
                                        String result = bg.execute(data, "request").get().toString();
                                        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                        builder.setTitle("we found " + result + " donar's around you")
                                                .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                })
                                                .setIcon(R.drawable.ic_rss_feed_black_18dp)
                                                .show();

                                    } catch (Exception e) {

                                    }
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                                                    WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                                                    //if(!wifiManager.isWifiEnabled())
                                                        wifiManager.setWifiEnabled(true);
                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                                }
                                            })
                                            .setIcon(R.drawable.ic_report_black_24dp)
                                            .show();
                                }

                            }else {
                                Toast.makeText(getActivity(), "Incorrect phone number", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Fields should not be empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
