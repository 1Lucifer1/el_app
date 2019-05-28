package com.example.user.el;
import okhttp3.FormBody;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    static String url = "http://shuotu.vip/el/";
    static String loginName = "";
    static String loginPassword = "";
    public static void main (String[] avgs) throws IOException, InterruptedException {
        System.out.println("你妈的 快点给老子登录！");
        Scanner scan = new Scanner(System.in);
        System.out.print("账号： ");
        loginName = scan.next();
        System.out.print("密码： ");
        loginPassword = scan.next();
        scan.close();
        Post p = new Post();
        FormBody formBody = new FormBody.Builder()
                .add("login_name", loginName)
                .add("login_password", loginPassword)
                .build();
        String loginCode = p.post(url + "login/", formBody);
        if (loginCode.equals("0")) {
            System.out.println("登录成功！");
            System.out.println("你好  " + loginName + "！");
        } else if (loginCode.equals("2")) {
            System.out.println("找不到你的用户名呢");
        } else if (loginCode.equals("1")) {
            System.out.println("密码错了！");
        }
    }
    public static String login(String name, String password)throws IOException, InterruptedException{
        Post p = new Post();
        FormBody formBody = new FormBody.Builder()
                .add("login_name", name)
                .add("login_password", password)
                .build();
        String loginCode = p.post(url + "login/", formBody);
        return loginCode;
    }
}
