package com.example.user.el;
import okhttp3.FormBody;

import java.io.IOException;
import java.util.Scanner;

public class Register {
    static String url = "http://shuotu.vip/el/";
    static String loginName = "";
    static String loginPassword = "";

    public static void main (String[] avgs) throws IOException, InterruptedException {
        System.out.println("你妈的 快点给老子注册！ 用户名为2-10位 密码为6-16位");
        register();
    }
    public static void register()throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.print("账号： ");
        loginName = scan.next();
        System.out.print("密码： ");
        loginPassword = scan.next();
        System.out.print("重复你的密码： ");
        String re_loginPassword = scan.next();
        scan.close();
        if (loginName.length()>10 || loginName.length()<2){
            System.out.println("你的用户名不符合规范！");
        }
        if (loginPassword.length()>16 || loginPassword.length()<2){
            System.out.println("你的密码不符合规范！");
        }
        if (!loginPassword.equals(re_loginPassword)){
            System.out.println("两次密码不一致！");
        }
        Post p = new Post();
        FormBody formBody = new FormBody.Builder()
                .add("login_name",loginName)
                .add("login_password",loginPassword)
                .build();
        String loginCode = p.post(url+"register/", formBody);
        if (loginCode.equals("0")) {
            System.out.println("注册成功！");
        } else if (loginCode.equals("1")) {
            System.out.println("用户名已注册！");
        }
    }
}
