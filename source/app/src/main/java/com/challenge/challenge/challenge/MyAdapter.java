package com.challenge.challenge.challenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sangeet on 4/3/2016.
 */
public class MyAdapter extends ArrayAdapter<MyItem> {
    ArrayList<MyItem> ar;

    public MyAdapter(Context context,ArrayList<MyItem> ar) {
        super(context,R.layout.row,ar);
        this.ar=ar;
    }
    public static class Holder
    { TextView t1,t2,t3;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        if(convertView==null)
        { convertView= LayoutInflater.from(getContext()).inflate(R.layout.row,parent,false);
            holder.t1=(TextView)convertView.findViewById(R.id.info_text);
            holder.t2=(TextView)convertView.findViewById(R.id.textView);
            holder.t3=(TextView)convertView.findViewById(R.id.textView1);
            convertView.setTag(holder);



        }else
          holder= (Holder) convertView.getTag();
          holder.t1.setText(ar.get(position).getName());
       holder.t2.setText(ar.get(position).getCommit());
        holder.t3.setText(ar.get(position).getMessage());



        return convertView;


    }
}
