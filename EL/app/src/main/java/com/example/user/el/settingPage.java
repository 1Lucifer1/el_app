package com.example.user.el;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.List;


/**
 * Created by user on 2019/5/13.
 */

public class settingPage extends Fragment {
    private Button creatorList;
    private CheckBox[] voiceEffect = new CheckBox[2];
    private CheckBox[] music = new CheckBox[2];
    public settingPage(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_page_content, container, false);
        Button creatorList = (Button) view.findViewById(R.id.creator);
        voiceEffect[0] = (CheckBox) view.findViewById(R.id.voiceEffectTurnOn);
        voiceEffect[1] = (CheckBox) view.findViewById(R.id.voiceEffectTurnOff);
        music[0] = (CheckBox) view.findViewById(R.id.musicTurnOn) ;
        music[1] = (CheckBox) view.findViewById(R.id.musicTurnOff);

        creatorList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent creator;
                creator = new Intent(getActivity(), creatorList.class);
                startActivity(creator);
            }
        });

        voiceEffect[0].setChecked(true);
        voiceEffect[1].setChecked(false);
        music[0].setChecked(true);
        music[1].setChecked(false);

        return view;
    }
}
