package com.example.user.el;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by user on 2019/5/13.
 */

public class settingPage extends Fragment implements CompoundButton.OnCheckedChangeListener{
    private Button creatorList;
    private Button leave;
    private Button help;
    private Switch voiceEffect;
    private Switch music;
    public static boolean checkVoiceEffect = true;

    private SoundPool pool;
    private Map<String, Integer> poolMap;
    private final int poolcnt = 2;

    public settingPage(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_page_content, container, false);
        creatorList = (Button) view.findViewById(R.id.creator);
        leave = (Button) view.findViewById(R.id.leave);
        help = (Button) view.findViewById(R.id.help);
        voiceEffect = (Switch) view.findViewById(R.id.voiceEffect);
        music = (Switch) view.findViewById(R.id.music);

        poolMap = new HashMap<String, Integer>();
        // 实例化SoundPool，大小为3
        pool = new SoundPool(poolcnt, AudioManager.STREAM_MUSIC, 0);
        // 装载音频进音频池，并且把ID记录在Map中
        poolMap.put("voice2", pool.load(getActivity(), R.raw.voice_effect_2, 1));

        creatorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(settingPage.checkVoiceEffect){
                    pool.play(poolMap.get("voice2"), 1.0f, 1.0f, 0, 0, 1.0f);
                }
                Intent creator;
                creator = new Intent(getActivity(), creatorList.class);
                startActivity(creator);
            }
        });

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settingPage.checkVoiceEffect){
                    pool.play(poolMap.get("voice2"), 1.0f, 1.0f, 0, 0, 1.0f);
                }
                Intent register;
                register = new Intent(getActivity(),registerPage.class);
                startActivity(register);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settingPage.checkVoiceEffect){
                    pool.play(poolMap.get("voice2"), 1.0f, 1.0f, 0, 0, 1.0f);
                }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://www.baidu.com/");//此处填链接
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        voiceEffect.setChecked(true);
        music.setChecked(true);
        voiceEffect.setOnCheckedChangeListener(this);
        music.setOnCheckedChangeListener(this);

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.voiceEffect:
                if(compoundButton.isChecked()) {
                    checkVoiceEffect = true;
                }
                else {
                    checkVoiceEffect = false;
                }
                break;
            case R.id.music:
                if(compoundButton.isChecked()) {
                    mainpage.mp.start();
                }
                else {
                    mainpage.mp.pause();
                }
                break;
        }
    }


}
