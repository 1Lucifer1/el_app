package com.example.user.el;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import static com.example.user.el.R.id.creator_list_content;

/**
 * Created by user on 2019/5/13.
 */

public class friendPage extends Fragment {
    private List<friend> mData = null;
    private Context mContext;
    private friendAdapter mAdapter = null;
    private ListView friendList;

    public friendPage(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_page_content, container, false);

        mContext = getActivity();
        friendList = (ListView) view.findViewById(R.id.friend_list_content);
        mData = new LinkedList<friend>();

        mData.add(new friend(0,"lyl",1000));
        mData.add(new friend(1,"lzc",-1000));
        mAdapter = new friendAdapter((LinkedList<friend>) mData, mContext);
        friendList.setAdapter(mAdapter);

        return view;
    }

}
