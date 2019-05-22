package com.example.user.el;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by user on 2019/5/13.
 */

public class playerPage extends Fragment {
    private TextView playerName;
    private TextView playerMoney;
    private int money;
    private String name;
    private ButtonEffect startGame;


    public playerPage(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        name="lylzuishuai";
        money=123;
        View view = inflater.inflate(R.layout.player_page_content, container, false);
        playerName = (TextView) view.findViewById(R.id.name);
        playerMoney = (TextView) view.findViewById(R.id.money);
        playerName.setText(String.format(getString(R.string.player_name),name));
        playerMoney.setText(String.format(getString(R.string.player_money),money));
        startGame= (ButtonEffect) view.findViewById(R.id.startGame);

        return view;
    }
}
