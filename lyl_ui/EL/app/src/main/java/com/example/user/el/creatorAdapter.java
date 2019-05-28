package com.example.user.el;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by user on 2019/5/23.
 */

public class creatorAdapter extends BaseAdapter {
    private LinkedList<creator> mData;
    private Context mContext;

    public creatorAdapter(LinkedList<creator> mData, Context mContext){
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.creator_list,parent,false);
        TextView name = (TextView) convertView.findViewById(R.id.creator_name);
        TextView work = (TextView) convertView.findViewById(R.id.creator_work);
        name.setText(mData.get(position).name);
        work.setText(mData.get(position).work);
        name.setEnabled(false);
        work.setEnabled(false);
        return convertView;
    }
}
