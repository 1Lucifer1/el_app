package com.example.user.el;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by user on 2019/5/27.
 */

public class friendAdapter extends BaseAdapter {

    private LinkedList<friend> mData;
    private Context mContext;

    public friendAdapter(LinkedList<friend> mData, Context mContext){
        this.mData = mData;
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.friend_list,parent,false);
        TextView name = (TextView) convertView.findViewById(R.id.friend_name);
        TextView number = (TextView) convertView.findViewById(R.id.friend_number);
        TextView grade = (TextView) convertView.findViewById(R.id.friend_grade);
        name.setText(mData.get(position).name);
        number.setText(mData.get(position).number);
        grade.setText(mData.get(position).grade);
        return convertView;
    }
}
