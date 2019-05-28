package com.example.user.el;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import android.os.StrictMode;
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

import java.io.IOException;

import okhttp3.FormBody;

public class registerPage extends AppCompatActivity{
    private EditText account;
    private EditText password;
    private Button register;
    private Button enroll;
    private static String player = "";
    String url = "http://shuotu.vip/el";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //隐藏标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }


        register = (Button) findViewById(R.id.registeration);
        enroll = (Button) findViewById(R.id.enrollment);


    }

    public void registerClick(View v) throws IOException, InterruptedException{
        account = (EditText) findViewById(R.id.account_input);
        password = (EditText) findViewById(R.id.password_input);
        String loginName = account.getText().toString();
        String loginPassword = password.getText().toString();
        if (loginName.length()>10 || loginName.length()<2){
            Toast.makeText(getApplicationContext(), "你的用户名",Toast.LENGTH_SHORT).show();
        }
        else if (loginPassword.length()>16 || loginPassword.length()<2){
            Toast.makeText(getApplicationContext(), "你的密码不符合规范！", Toast.LENGTH_SHORT).show();
        }
        else {
            Post p = new Post();
            FormBody formBody = new FormBody.Builder()
                    .add("login_name", loginName)
                    .add("login_password", loginPassword)
                    .build();
            String loginCode = p.post(url + "/register/", formBody);
            if (loginCode.equals("0")) {
                Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();
            } else if (loginCode.equals("1")) {
                Toast.makeText(getApplicationContext(), "用户名已注册！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void enrollClick(View v) throws IOException, InterruptedException {
        account = (EditText) findViewById(R.id.account_input);
        password = (EditText) findViewById(R.id.password_input);
        final String loginName = account.getText().toString();
        final String loginPassword = password.getText().toString();
        if(loginName.equals(null)||loginPassword.equals(null)){
            Toast.makeText(getApplicationContext(), "请输入正确的账号或密码",Toast.LENGTH_SHORT).show();
        }else{
            String loginCode = Login.login(loginName,loginPassword);
            Toast.makeText(getApplicationContext(), loginCode,Toast.LENGTH_SHORT).show();
            new Thread() {
                @Override
                public void run(){
                    try {
                        String loginCode = Login.login(loginName, loginPassword);
                    }catch (Exception e){
                        String loginCode = "3";
                    }
                }
            }.start();
            if (loginCode!=null) {
                if (loginCode.equals("0")) {
                    Toast.makeText(getApplicationContext(), "登录成功！你好， " + loginName + "！", Toast.LENGTH_SHORT).show();
                    player = account.getText().toString();
                    Intent intentMainpage;
                    intentMainpage = new Intent(registerPage.this, mainpage.class);
                    startActivity(intentMainpage);
                } else if (loginCode.equals("2")) {
                    Toast.makeText(getApplicationContext(), "找不到你的用户名呢！", Toast.LENGTH_SHORT).show();
                } else if (loginCode.equals("1")) {
                    Toast.makeText(getApplicationContext(), "密码错了 傻逼！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "有bug。。", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public  static String getPlayer(){
        return player;
    }


}
