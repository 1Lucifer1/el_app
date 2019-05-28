package com.example.user.el;


        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.graphics.Color;
        import android.os.Handler;
        import android.os.Message;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ProgressBar;
        import android.widget.TextView;

        import java.io.IOException;
        import java.util.Timer;
        import java.util.TimerTask;

        import okhttp3.FormBody;

class Problem {
    public String problem[] = new String[6];
    public String aAnswer[] = new String[6];
    public String bAnswer[] = new String[6];
    public String cAnswer[] = new String[6];
    public String dAnswer[] = new String[6];
    public String rightAnswer[] = new String[6];
}

public class gamePage extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView1;
    private ImageView imageView2;
    private ProgressBar progressBar;
    private EditText editText;
    private double i=0;
    private Timer timer = null;
    private TimerTask task = null;
    private TextView time;
    private TextView Score1;
    private TextView Score2;
    private TextView Problem;
    private Button A;
    private Button B;
    private int Round=0;
    private Button C;
    private Button D;
    private String key="A";
    private boolean waitingMode=true;
    private TextView RoundText;
    private boolean correct;
    private boolean finished=false;
    private boolean otherFinished=true;
    private boolean gameOver=false;
    private static final int roundNumber=3;
    Problem question = new Problem();
    Post p = new Post();
    Get g = new Get();

    public gamePage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        //隐藏标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }
        Get g = new Get();
        String text = null;
        try {
            text = g.run(FindOp.roomUrl + "getquestion/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String Items[] = text.split("///");
        Problem question = new Problem();
        for (int i = 0; i < Items.length; i++) {
            String[] items =
                    Items[i].split("#");
            question.problem[i] = items[0];
            question.aAnswer[i] = items[1];
            question.bAnswer[i] = items[2];
            question.cAnswer[i] = items[3];
            question.dAnswer[i] = items[4];
            question.rightAnswer[i] = items[5];
        }
        A=(Button)findViewById(R.id.button);
        B=(Button)findViewById(R.id.button2);
        C=(Button)findViewById(R.id.button3);
        D=(Button)findViewById(R.id.button4);
        Problem=(TextView)findViewById(R.id.textView) ;
        Score1=(TextView)findViewById(R.id.textView2) ;
        Score2=(TextView)findViewById(R.id.textView3) ;
        RoundText=(TextView)findViewById(R.id.textView4);
        imageView1=(ImageView)findViewById(R.id.imageview1);//设置成玩家A的照片
        imageView2=(ImageView)findViewById(R.id.imageview2);//设置成玩家B的照片
        imageView1.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_foreground));
        imageView2.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_foreground));
        progressBar=(ProgressBar)findViewById(R.id.progressBar4);
        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        Problem.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        time = (TextView) findViewById(R.id.textTime);
        RoundText.setText("第"+(Round+1)+"回合"+'\n'+"共"+roundNumber+"回合");
        i=20;



        Problem.setText(question.problem[Round]);
        A.setText(question.aAnswer[Round]);
        B.setText(question.bAnswer[Round]);
        C.setText(question.cAnswer[Round]);
        D.setText(question.dAnswer[Round]);
        key=question.rightAnswer[Round];

        startTime();
        A.setBackgroundColor(Color.parseColor("#C0C0C0"));
        B.setBackgroundColor(Color.parseColor("#C0C0C0"));
        C.setBackgroundColor(Color.parseColor("#C0C0C0"));
        D.setBackgroundColor(Color.parseColor("#C0C0C0"));
    }
    @Override
    public void onClick(View v){
        if(i<=0){
            A.setEnabled(false);
            B.setEnabled(false);
            C.setEnabled(false);
            D.setEnabled(false);
            return;}
        RoundText.setText("第"+(Round+1)+"回合"+'\n'+"共"+roundNumber+"回合");
        int nowScore;
        if(i>=15)nowScore=200;
        else if(i>=10)nowScore=150;
        else if(i>=5)nowScore=100;
        else nowScore=50;
        switch (v.getId()){
            case R.id.button:
                if(key.equals("A")){Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())+nowScore));A.setBackgroundColor(Color.parseColor("#66ff33"));A.setEnabled(false);B.setEnabled(false);C.setEnabled(false);D.setEnabled(false);finished=true;correct=true;}
                else {Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())-50));A.setEnabled(false);B.setEnabled(false);C.setEnabled(false);D.setEnabled(false);A.setBackgroundColor(Color.parseColor("#ff0099"));finished=true;correct=false;}
                if(finished==true&&otherFinished==true){
                    i=0;
                    try {
                        g.run(FindOp.roomUrl+"next/");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.button2:
                if(key.equals("B")){Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())+nowScore));B.setBackgroundColor(Color.parseColor("#66ff33"));A.setEnabled(false);B.setEnabled(false);C.setEnabled(false);D.setEnabled(false);finished=true;correct=true;}
                else {Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())-50));A.setEnabled(false);B.setEnabled(false);C.setEnabled(false);D.setEnabled(false);B.setBackgroundColor(Color.parseColor("#ff0099"));finished=true;correct=false;}
                if(finished==true&&otherFinished==true){
                    ;
                    try {
                        g.run(FindOp.roomUrl+"next/");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.button3:
                if(key.equals("C")){
                    Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())+nowScore));
                    C.setBackgroundColor(Color.parseColor("#66ff33"));
                    A.setEnabled(false);
                    B.setEnabled(false);
                    C.setEnabled(false);
                    D.setEnabled(false);
                    finished=true;correct=true;
                }
                else {
                    Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())-50));
                    A.setEnabled(false);
                    B.setEnabled(false);
                    C.setEnabled(false);
                    D.setEnabled(false);
                    C.setBackgroundColor(Color.parseColor("#ff0099"));
                    finished=true;correct=false;
                }
                if(finished==true&&otherFinished==true){
                    i=0;
                    try {
                        g.run(FindOp.roomUrl+"next/");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.button4:
                if(key.equals("D")){Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())+nowScore));D.setBackgroundColor(Color.parseColor("#66ff33"));A.setEnabled(false);B.setEnabled(false);C.setEnabled(false);D.setEnabled(false);finished=true;correct=true;}
                else {Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())-50));A.setEnabled(false);B.setEnabled(false);C.setEnabled(false);D.setEnabled(false);D.setBackgroundColor(Color.parseColor("#ff0099"));finished=true;correct=false;}
                if(finished==true&&otherFinished==true) {
                    i = 0;
                    try {
                        g.run(FindOp.roomUrl+"next/");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.textView:
                if(gameOver==true){
                    Intent intentGamepage = new Intent();
                    intentGamepage.setClass(gamePage.this, mainpage.class);
                    startActivity(intentGamepage);
                    finish();
                }


        }
    }
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            time.setText(msg.arg1 + "");
            startTime();
        };
    };

    private void waiting(){
        Problem.setText("结算中");
    }
    public void startTime() {
        RoundText.setText("第"+(Round+1)+"回合"+'\n'+"共"+roundNumber+"回合");
        timer = new Timer();
        progressBar.setProgress((int)i);
        //结果判断
        if(Round==3){
            if(Integer.parseInt(Score1.getText().toString())>Integer.parseInt(Score2.getText().toString()))
                Problem.setText("游戏结束,你的分数是："+Score1.getText().toString()+'\n'+"对手的分数是:"+Score2.getText().toString()+'\n'+"你赢了！"+'\n'+"你获得100金币"+'\n'+"(点击此处返回)");
            if(Integer.parseInt(Score1.getText().toString())==Integer.parseInt(Score2.getText().toString()))
                Problem.setText("游戏结束,你的分数是："+Score1.getText().toString()+'\n'+"对手的分数是:"+Score2.getText().toString()+'\n'+"平局！"+'\n'+"你获得50金币"+'\n'+"(点击此处返回)");
            if(Integer.parseInt(Score1.getText().toString())<Integer.parseInt(Score2.getText().toString()))
                Problem.setText("游戏结束,你的分数是："+Score1.getText().toString()+'\n'+"对手的分数是:"+Score2.getText().toString()+'\n'+"你输了！"+'\n'+"你获得0金币"+'\n'+"(点击此处返回)");
            A.setEnabled(false);
            B.setEnabled(false);
            C.setEnabled(false);
            D.setEnabled(false);
            gameOver=true;
            return;
        }
        //重置下一回合
        if(i<=0){
            A.setEnabled(false);
            B.setEnabled(false);
            C.setEnabled(false);
            D.setEnabled(false);
            A.setBackgroundColor(Color.parseColor("#C0C0C0"));
            B.setBackgroundColor(Color.parseColor("#C0C0C0"));
            C.setBackgroundColor(Color.parseColor("#C0C0C0"));
            D.setBackgroundColor(Color.parseColor("#C0C0C0"));

            if(waitingMode==true)Problem.setText("结算中，请稍等...");
            else if(waitingMode==false){
                A.setEnabled(true);
                B.setEnabled(true);
                C.setEnabled(true);
                D.setEnabled(true);
                Problem.setText(question.problem[Round+1]);
                A.setText(question.aAnswer[Round+1]);
                B.setText(question.bAnswer[Round+1]);
                C.setText(question.cAnswer[Round+1]);
                D.setText(question.dAnswer[Round+1]);
                key=question.rightAnswer[Round+1];
            }
        }
        //计时
        task = new TimerTask() {
            @Override
            public void run() {
                if (i > 0) {  //加入判断不能小于0
                    i=i-0.5;
                    if (finished==false){
                        FormBody formBody = new FormBody.Builder()
                                .add("name", registerPage.getPlayer())
                                .add("process", "no")
                                .build();
                        try {
                            String info = p.post(FindOp.roomUrl + "judge/", formBody);

                            if (info == "no") {
                                otherFinished = false;
                            } else {
                                Score2.setText(info);
                                otherFinished = true;
                            }
                        }catch (Exception e){}
                    }
                    else {
                        FormBody formBody = new FormBody.Builder()
                                .add("name", registerPage.getPlayer())
                                .add("process", Score1.getText().toString())
                                .build();
                        try {
                            String info = p.post(FindOp.roomUrl + "judge/", formBody);
                            if (info == "no") {
                                otherFinished = false;
                            } else {
                                Score2.setText(info);
                                otherFinished = true;
                            }
                        }catch (Exception e){}
                    }
                    Message message = mHandler.obtainMessage();
                    message.arg1 = (int)i;
                    mHandler.sendMessage(message);
                }
                else if(waitingMode==true&&i<=0){
                    if(key.equals("A")){A.setBackgroundColor(Color.parseColor("#ffff99"));}
                    else if(key.equals("B"))B.setBackgroundColor(Color.parseColor("#ffff99"));
                    else if(key.equals("C"))C.setBackgroundColor(Color.parseColor("#ffff99"));
                    else if(key.equals("D"))D.setBackgroundColor(Color.parseColor("#ffff99"));
                    i=3;
                    time.setText("3");
                    startTime();
                    waitingMode=false;
                    //waiting();
                }
                else if(waitingMode==false&&i<=0){
                    i=20;
                    time.setText("20");
                    startTime();
                    waitingMode=true;
                    Round++;
                }
            }
        };
        timer.schedule(task, 500);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (((keyCode == KeyEvent.KEYCODE_BACK) ||
                (keyCode == KeyEvent.KEYCODE_HOME))
                && event.getRepeatCount() == 0) {
            dialog_Exit(gamePage.this);
        }
        return false;

        //end onKeyDown
    }

    public static void dialog_Exit(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确定要退出吗?\n" +
                "退出后你将无法再进入对战且对手直接获得胜利。");
        builder.setTitle("提示");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        android.os.Process.killProcess(android.os.Process
                                .myPid());
                    }
                });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
}
