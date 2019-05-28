package com.example.user.el;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by user on 2019/5/27.
 */

public class storageAdapter extends BaseAdapter {
    private LinkedList<file> mData;
    private Context mContext;

    public storageAdapter(LinkedList<file> mData, Context mContext){
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.storage_file,parent,false);
        TextView name = (TextView) convertView.findViewById(R.id.file_name);
        TextView content = (TextView) convertView.findViewById(R.id.file_content);
        name.setText(mData.get(position).fName);
        content.setText(mData.get(position).fContent);
        name.setEnabled(false);
        content.setEnabled(false);
        return convertView;
    }

}
