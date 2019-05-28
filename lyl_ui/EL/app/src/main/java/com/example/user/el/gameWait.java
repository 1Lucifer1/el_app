package com.example.user.el;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static java.lang.Thread.sleep;

public class gameWait extends AppCompatActivity{
    private waitEnemy t = new waitEnemy();
    private boolean findEnemy = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_wait);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //隐藏标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }
        t.start();
        try {
            sleep(5000); // 主线程延迟5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.exit = true;  // 终止线程thread
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(findEnemy ==true){
            Intent intent = new Intent(gameWait.this,gamePage.class);
            startActivity(intent);
        }

    }

    public void cancelClick(View v){
        Intent intent = new Intent(gameWait.this,mainpage.class);
        startActivity(intent);
    }


}
