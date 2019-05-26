package com.example.user.el;

/**
 * Created by user on 2019/5/26.
 */

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class AudioService extends Service {
    public static final String TAG = "MyService";
    private MediaPlayer player;
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.w(TAG,"in onCreate");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.w(TAG, "in onStartCommand");
        Log.w(TAG, "MyService:" + this);
        if(player == null)
        {
            player = MediaPlayer.create(this, R.raw.thousand_sakura);
            player.start();
        }
        return  START_STICKY;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        player.stop();

    }
}