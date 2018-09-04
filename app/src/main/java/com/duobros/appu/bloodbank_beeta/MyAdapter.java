package com.duobros.appu.bloodbank_beeta;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by appu on 17/8/17.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private LayoutInflater inflater;

    List<Info> data= Collections.emptyList();
    Context cn;
    public MyAdapter(Context context,List<Info> data){
        cn=context;
        inflater=LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.customer_row,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Info current=data.get(position);
        holder.title.setText(current.title);
        holder.desc.setText(current.addr);
        holder.icon.setImageResource(current.iconId);
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(cn,"onclick"+position,Toast.LENGTH_LONG).show();
                if(!(TextUtils.isEmpty(data.get(position).ph))) {
                    Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                    phoneIntent.setData(Uri.parse("tel:" + data.get(position).ph));
                    if (ActivityCompat.checkSelfPermission(cn, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    cn.startActivity(phoneIntent);
                }else {

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,desc;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            desc=(TextView)itemView.findViewById(R.id.desc);
            icon=(ImageView)itemView.findViewById(R.id.icon);
        }
    }
}
