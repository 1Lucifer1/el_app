package com.example.user.el;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerPage extends AppCompatActivity{
    private EditText account;
    private EditText password;
    private Button register;
    private Button enroll;
    private static String player = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //隐藏标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        account = (EditText) findViewById(R.id.account_input);
        password = (EditText) findViewById(R.id.password_input);
        register = (Button) findViewById(R.id.registeration);
        enroll = (Button) findViewById(R.id.enrollment);

    }

    public void registerClick(View v) {
        player = account.getText().toString();
        Intent intentMainpage;
        intentMainpage = new Intent(registerPage.this, mainpage.class);
        startActivity(intentMainpage);
    }

    public void enrollClick(View v) {
        if(account.getText()==null||password.getText()==null){
            Toast.makeText(getApplicationContext(), "请输入正确的账号或密码",Toast.LENGTH_SHORT).show();
        }else{
            player = account.getText().toString();
            Intent intentMainpage;
            intentMainpage = new Intent(registerPage.this, mainpage.class);
            startActivity(intentMainpage);
        }
    }

    public  static String getPlayer(){
        return player;
    }


}
