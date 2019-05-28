package com.example.user.el;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class mainpage extends AppCompatActivity  implements ViewPager.OnPageChangeListener{
    //UI Objects
    private ViewPager vpager;
    private MyFragmentAdapter mAdapter;

    public static MediaPlayer mp;
    private AudioManager am;//AudioManager引用

    private RadioGroup rg_tab_bar;
    private RadioButton rb_player;
    private RadioButton rb_shop;
    private RadioButton rb_setting;
    private RadioButton rb_friend;

    //data
    String playerName = registerPage.getPlayer();


    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //隐藏标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        try{
            am= (AudioManager) getSystemService(Service.AUDIO_SERVICE);
            mp = MediaPlayer.create(mainpage.this, R.raw.thousand_sakura);
            mp.setLooping(true);
            mp.start();
            Toast.makeText(getApplicationContext(), "播放背景音", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), "播放背景音失败", Toast.LENGTH_SHORT).show();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "播放背景音失败", Toast.LENGTH_SHORT).show();
        }




        mAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        bindViews();
        rb_player.setChecked(true);


        //重写ViewPager页面切换的处理方法
        rg_tab_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_friend:
                        vpager.setCurrentItem(PAGE_ONE);
                        break;
                    case R.id.rb_player:
                        vpager.setCurrentItem(PAGE_TWO);
                        break;
                    case R.id.rb_shop:
                        vpager.setCurrentItem(PAGE_THREE);
                        break;
                    case R.id.rb_setting:
                        vpager.setCurrentItem(PAGE_FOUR);
                        break;
                }
            }
        });

    }


    private void bindViews() {
        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(PAGE_TWO);
        vpager.addOnPageChangeListener(this);

        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_player = (RadioButton) findViewById(R.id.rb_player);
        rb_shop = (RadioButton) findViewById(R.id.rb_shop);
        rb_friend = (RadioButton) findViewById(R.id.rb_friend);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_friend.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_player.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_shop.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rb_setting.setChecked(true);
                    break;
            }
        }
    }

    private Uri uri;
    private static File imageFile;

    public static void setImageFile(File ImageFile){
        imageFile = ImageFile;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        uri = Uri.fromFile(imageFile);
        if (requestCode == Activity.DEFAULT_KEYS_DIALER) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageView playerImage = (ImageView) findViewById(R.id.player_image);
            playerImage.setImageBitmap(bitmap);

        }
    }

    @Override
    protected void onStop() {
        super.onPause();
        mp.pause();
    }

    protected void onDestroy() {// 销毁Activity之前，所做的事
        super.onDestroy();
        mp.stop();
        mp.release();
    }

}
