package com.example.user.el;

import java.io.IOException;
import okhttp3.FormBody;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;

/**
 * Created by Administrator on 2019/5/28.
 */
class Problem {
    public String problem[] = new String[6];
    public String aAnswer[] = new String[6];
    public String bAnswer[] = new String[6];
    public String cAnswer[] = new String[6];
    public String dAnswer[] = new String[6];
    public String rightAnswer[] = new String[6];
}
public class Test {
        static String url = "http://127.0.0.1:8000/el/";
        static String loginName = "123";
        static String loginPassword = "";
        static String[] Question;//问题
        static String[] aAnswer;//答案a
        static String[] bAnswer;//答案b
        static String[] cAnswer;//答案c
        static String[] dAnswer;//答案d
        static String[] RightAnswer;//正确答案

    public static void main (String[] avgs) throws IOException, InterruptedException{
        Get g = new Get();
        String room = g.run(url+"contest/find/");
        String roomUrl = url + "contest/" + "room" + room + "/";
        Post p = new Post();
        FormBody formBody = new FormBody.Builder()
                .add("name",loginName)
                .add("login_password",loginPassword)
                .build();
        while(true) {
            Thread.sleep(1000);
            String r = p.post(roomUrl + "join/", formBody);
            System.out.println(r);
            if(r.equals("1")){
                System.out.println("等待中");
            }
            else{
                System.out.println(r+"加入了游戏");
                break;
            }
        }
        String text = g.run(roomUrl + "getquestion/");
        String Items[] = text.split("///");
        Problem question = new Problem();
        for (int i = 0; i < Items.length; i++) {
            String[] items = Items[i].split("#");
            question.problem[i] = items[0];
            question.aAnswer[i] = items[1];
            question.bAnswer[i] = items[2];
            question.cAnswer[i] = items[3];
            question.dAnswer[i] = items[4];
            question.rightAnswer[i] = items[5];
        }
    }

}
