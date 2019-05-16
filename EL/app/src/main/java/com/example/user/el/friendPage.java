package com.example.user.el;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
/**
 * Created by user on 2019/5/13.
 */

public class friendPage extends Fragment {
    public friendPage(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_page_content, container, false);
        return view;
    }

}
