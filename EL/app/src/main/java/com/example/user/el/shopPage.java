package com.example.user.el;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by user on 2019/5/13.
 */

public class shopPage extends Fragment {
    private Button purchase;
    private Button history;
    private ImageView payment;
    private TextView playerMoney;
    private int money = mainpage.playerMoney;

    private SoundPool pool;
    private Map<String, Integer> poolMap;
    private final int poolcnt = 2;

    public shopPage(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_page_content, container, false);
        purchase = (Button) view.findViewById(R.id.purchase);
        history = (Button) view.findViewById(R.id.shop_history);
        payment = (ImageView) view.findViewById(R.id.payment);
        playerMoney = (TextView) view.findViewById(R.id.money);
        playerMoney.setText(String.format(getString(R.string.player_money),money));

        poolMap = new HashMap<String, Integer>();
        // 实例化SoundPool，大小为3
        pool = new SoundPool(poolcnt, AudioManager.STREAM_MUSIC, 0);
        // 装载音频进音频池，并且把ID记录在Map中
        poolMap.put("voice1", pool.load(getActivity(), R.raw.voice_effect_1, 1));

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settingPage.checkVoiceEffect){
                    pool.play(poolMap.get("voice1"), 1.0f, 1.0f, 0, 0, 1.0f);
                }
                Toast.makeText(getActivity(), "购买成功", Toast.LENGTH_SHORT).show();
                payment.setVisibility(View.VISIBLE);
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settingPage.checkVoiceEffect){
                    pool.play(poolMap.get("voice1"), 1.0f, 1.0f, 0, 0, 1.0f);
                }
                payment.setVisibility(View.INVISIBLE);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settingPage.checkVoiceEffect){
                    pool.play(poolMap.get("voice1"), 1.0f, 1.0f, 0, 0, 1.0f);
                }
                Intent storage;
                storage = new Intent(getActivity(), goodsStorage.class);
                startActivity(storage);
            }
        });
        return view;
    }

}
