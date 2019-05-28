package com.example.user.el;

/**
 * Created by user on 2019/5/28.
 */

public class waitEnemy extends Thread {
    private Thread t;
    public volatile boolean exit = false;
    public void run(){
        while (!exit);
    }
    public void start (){
        t.start ();
    }
}
