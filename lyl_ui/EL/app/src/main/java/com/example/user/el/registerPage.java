package com.example.user.el;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import okhttp3.FormBody;

public class registerPage extends AppCompatActivity {
    private EditText account;
    private EditText password;
    private Button register;
    private Button enroll;
    private static String player = "";
    String url = "http://shuotu.vip/el";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //隐藏标题栏
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }

        account = (EditText) findViewById(R.id.account_input);
        password = (EditText) findViewById(R.id.password_input);
        register = (Button) findViewById(R.id.registeration);
        enroll = (Button) findViewById(R.id.enrollment);
        Thread threadDetectIfOnline = new Thread();
        threadDetectIfOnline.run();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void registerClick(View v) throws IOException, InterruptedException {
        account = (EditText) findViewById(R.id.account_input);
        password = (EditText) findViewById(R.id.password_input);
        String loginName = account.getText().toString();
        String loginPassword = password.getText().toString();
        if (loginName.length() > 10 || loginName.length() < 2) {
            Toast.makeText(getApplicationContext(), "你的用户名", Toast.LENGTH_SHORT).show();
        } else if (loginPassword.length() > 16 || loginPassword.length() < 2) {
            Toast.makeText(getApplicationContext(), "你的密码不符合规范！", Toast.LENGTH_SHORT).show();
        } else {
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
        if (loginName.equals(null) || loginPassword.equals(null)) {
            Toast.makeText(getApplicationContext(), "请输入正确的账号或密码", Toast.LENGTH_SHORT).show();
        } else {
            String loginCode = Login.login(loginName, loginPassword);
            Toast.makeText(getApplicationContext(), loginCode, Toast.LENGTH_SHORT).show();
            new Thread() {
                @Override
                public void run() {
                    try {
                        String loginCode = Login.login(loginName, loginPassword);
                    } catch (Exception e) {
                        String loginCode = "3";
                    }
                }
            }.start();
            if (loginCode != null) {
                if (loginCode.equals("0")) {
                    Toast.makeText(getApplicationContext(), "登录成功！你好， " + loginName + "！", Toast.LENGTH_SHORT).show();
                    player = account.getText().toString();
                    Intent intentMainpage = new Intent();
                    intentMainpage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    intentMainpage.setClass(registerPage.this, mainpage.class);
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

    public static String getPlayer() {
        return player;
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("registerPage Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
