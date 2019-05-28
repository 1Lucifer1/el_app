package com.example.user.el;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.FormBody;

import static com.example.user.el.Login.loginPassword;
import static java.lang.Thread.activeCount;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class gameWait extends Activity {
    public static String url = "http://shuotu.vip/el/";
    TextView playerInRoom;
    TextView gameWaitText;
    private FindEnemy FindEnemy;
    public String r = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_wait);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        playerInRoom = (TextView) findViewById(R.id.player_in_room);
        gameWaitText = (TextView) findViewById(R.id.game_wait_text);
        playerInRoom.setVisibility(View.INVISIBLE);
        gameWaitText.setVisibility(View.INVISIBLE);

        //startService(new Intent(getBaseContext(), FindEnemy.class));
    }
/**
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //返回一个MsgService对象
            FindEnemy = ((FindEnemy.feBinder)service).getService();

            //注册回调接口来接收下载进度的变化
            FindEnemy.setOnProgressListener(new OnProgressListener() {

                @Override
                public void onProgress(String r) {
                    if(r.equals("1")){
                        playerInRoom.setVisibility(View.VISIBLE);
                        gameWaitText.setVisibility(View.INVISIBLE);
                        playerInRoom.setText(FindEnemy.r+"加入了房间");
                        stopService(new Intent(getBaseContext(), FindEnemy.class));
                        stopService(new Intent(getBaseContext(), FindEnemy.class));
                    }
                }
            });

        }
    };

**/
public void startFind(View v){
    playerInRoom.setVisibility(View.INVISIBLE);
    gameWaitText.setVisibility(View.VISIBLE);
    FindOp f = new FindOp();
    while(true){
        playerInRoom.setVisibility(View.INVISIBLE);
        gameWaitText.setVisibility(View.VISIBLE);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            r = f.find(registerPage.getPlayer());
        }catch (Exception e){
            e.printStackTrace();
        }
        if(r.equals("1")){
        }
        else{

            playerInRoom.setText(r+"加入了房间");
            playerInRoom.setVisibility(View.VISIBLE);
            gameWaitText.setVisibility(View.INVISIBLE);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intentGamepage = new Intent();
            intentGamepage.setClass(gameWait.this, gamePage.class);
            startActivity(intentGamepage);
            break;
        }
    }

    }

    public void cancelClick(View v){
        Intent intent = new Intent(gameWait.this,mainpage.class);
        startActivity(intent);
        this.finish();
    }

    /**@Override
    protected void onDestroy() {
        unbindService(conn);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }**/


}
