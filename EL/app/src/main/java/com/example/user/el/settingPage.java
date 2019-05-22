package com.example.user.el;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by user on 2019/5/13.
 */

public class settingPage extends Fragment {
    private Button creatorList;
    public settingPage(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_page_content, container, false);
        Button creatorList = (Button) view.findViewById(R.id.creator);
        creatorList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent creator;
                creator = new Intent(getActivity(), creatorList.class);
                startActivity(creator);
            }
        });
        return view;
    }
}
