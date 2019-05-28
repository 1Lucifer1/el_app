package com.example.user.el;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2019/5/13.
 */

public class playerPage extends Fragment {
    private TextView playerName;
    private TextView playerMoney;
    private int money;
    private String name;
    private Button startGame;
    private ImageView playerImage;
    private PopWindow popWindow;
    private Activity activity = getActivity();
    private MediaPlayer mPlayer;

    private SoundPool pool;
    private Map<String, Integer> poolMap;
    private final int poolcnt = 2;

    public playerPage(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        name=registerPage.getPlayer();
        money=mainpage.playerMoney;
        View view = inflater.inflate(R.layout.player_page_content, container, false);
        playerName = (TextView) view.findViewById(R.id.name);
        playerMoney = (TextView) view.findViewById(R.id.money);
        playerName.setText(String.format(getString(R.string.player_name),name));
        playerMoney.setText(String.format(getString(R.string.player_money),money));
        startGame= (Button) view.findViewById(R.id.startGame);
        playerImage = (ImageView) view.findViewById(R.id.player_image);




        poolMap = new HashMap<String, Integer>();
        // 实例化SoundPool，大小为3
        pool = new SoundPool(poolcnt, AudioManager.STREAM_MUSIC, 0);
        // 装载音频进音频池，并且把ID记录在Map中
        poolMap.put("voice1", pool.load(getActivity(), R.raw.voice_effect_1, 1));

        popWindow=new PopWindow(getActivity(),playerImage, activity);
        playerImage.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(settingPage.checkVoiceEffect){
                    pool.play(poolMap.get("voice1"), 1.0f, 1.0f, 0, 0, 1.0f);
                }
                popWindow.initPopWindow(v);
                if (ContextCompat.checkSelfPermission(getActivity() , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                }
            }
        });

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settingPage.checkVoiceEffect){
                    pool.play(poolMap.get("voice1"), 1.0f, 1.0f, 0, 0, 1.0f);
                }
                Intent start = new Intent(getActivity(),gamePage.class);
                startActivity(start);
            }
        });
        return view;
    }

}
