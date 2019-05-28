package com.example.user.el;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;


public class gamePage extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView1;
    private ImageView imageView2;
    private ProgressBar progressBar;
    private EditText editText;
    private int i=0;
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
    private char key='A';
    private boolean waitingMode=true;
    private TextView RoundText;
    private static final int roundNumber=3;
    public  gamePage(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        //Problem.setText(Problem);                         //输入问题
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
        imageView1.setOnClickListener(this);
        time = (TextView) findViewById(R.id.textTime);
        RoundText.setText("第"+(Round+1)+"回合"+'\n'+"共"+roundNumber+"回合");
        i=20;
        //key='A'or'B'or'C'or'D'
        //A.setText(ProblemA);
        //B.setText(ProblemB);
        //C.setText(ProblemC);
        //D.setText(ProblemD);
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
                if(key=='A'){Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())+nowScore));A.setBackgroundColor(Color.parseColor("#66ff33"));A.setEnabled(false);B.setEnabled(false);C.setEnabled(false);D.setEnabled(false);break;}
                else {Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())-50));A.setEnabled(false);A.setBackgroundColor(Color.parseColor("#ff0099"));break;}
            case R.id.button2:
                if(key=='B'){Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())+nowScore));B.setBackgroundColor(Color.parseColor("#66ff33"));A.setEnabled(false);B.setEnabled(false);C.setEnabled(false);D.setEnabled(false);break;}
                else {Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())-50));B.setEnabled(false);B.setBackgroundColor(Color.parseColor("#ff0099"));break;}
            case R.id.button3:
                if(key=='C'){Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())+nowScore));C.setBackgroundColor(Color.parseColor("#66ff33"));A.setEnabled(false);B.setEnabled(false);C.setEnabled(false);D.setEnabled(false);break;}
                else {Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())-50));C.setEnabled(false);C.setBackgroundColor(Color.parseColor("#ff0099"));break;}
            case R.id.button4:
                if(key=='D'){Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())+nowScore));D.setBackgroundColor(Color.parseColor("#66ff33"));A.setEnabled(false);B.setEnabled(false);C.setEnabled(false);D.setEnabled(false);break;}
                else {Score1.setText(String.valueOf(Integer.parseInt(Score1.getText().toString())-50));D.setEnabled(false);D.setBackgroundColor(Color.parseColor("#ff0099"));break;}
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
        progressBar.setProgress(i);
        //结果判断
        if(Round==3){
            if(Integer.parseInt(Score1.getText().toString())>Integer.parseInt(Score2.getText().toString()))
                Problem.setText("游戏结束,你的分数是："+Score1.getText().toString()+'\n'+"对手的分数是:"+Score2.getText().toString()+'\n'+"你赢了！");
            if(Integer.parseInt(Score1.getText().toString())==Integer.parseInt(Score2.getText().toString()))
                Problem.setText("游戏结束,你的分数是："+Score1.getText().toString()+'\n'+"对手的分数是:"+Score2.getText().toString()+'\n'+"平局！");
            if(Integer.parseInt(Score1.getText().toString())<Integer.parseInt(Score2.getText().toString()))
                Problem.setText("游戏结束,你的分数是："+Score1.getText().toString()+'\n'+"对手的分数是:"+Score2.getText().toString()+'\n'+"你输了！");
            A.setEnabled(false);
            B.setEnabled(false);
            C.setEnabled(false);
            D.setEnabled(false);
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
            //Problem.setText(Problem);                         //输入问题
            //key='A'or'B'or'C'or'D'
            //A.setText(ProblemA);
            //B.setText(ProblemB);
            //C.setText(ProblemC);
            //D.setText(ProblemD);
            if(waitingMode==true)Problem.setText("结算中，请稍等...");
            else if(waitingMode==false){
                A.setEnabled(true);
                B.setEnabled(true);
                C.setEnabled(true);
                D.setEnabled(true);
                Problem.setText("*****问题内容******");
            }

        }
        //计时
        task = new TimerTask() {
            @Override
            public void run() {
                if (i > 0) {  //加入判断不能小于0
                    i--;
                    Message message = mHandler.obtainMessage();
                    message.arg1 = i;
                    mHandler.sendMessage(message);
                }
                else if(waitingMode==true&&i<=0){
                    if(key=='A'){A.setBackgroundColor(Color.parseColor("#ffff99"));}
                    else if(key=='B')B.setBackgroundColor(Color.parseColor("#ffff99"));
                    else if(key=='C')C.setBackgroundColor(Color.parseColor("#ffff99"));
                    else if(key=='D')D.setBackgroundColor(Color.parseColor("#ffff99"));
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
        timer.schedule(task, 1000);
    }
}
