package com.example.user.el;

/**
 * Created by user on 2019/5/27.
 */

public class shop {
    private int currentMoney = 2000;

    private void buyBook(int n) {
        int money = getMoney();
        if(n==1) {
            money = money - 300;
        }
        else if(n==2) {
            money = money - 200;
        }
        else if(n==3) {
            money = money - 250;
        }
        else {
            money = money;
        }
        currentMoney = money;
    }
    private int getMoney() {
        return currentMoney;
    }
    private void showMoney() {
        System.out.println(currentMoney);
    }
    private void main(String[] args) {
        buyBook(1);
        currentMoney = getMoney();
        showMoney();
        book bookOne = new book();
        bookOne.setBookName("<<Head First Java>>");
        bookOne.setBookPrice(200);
        bookOne.setBookText("This is a very good book!");
        System.out.println(bookOne.getBookPrice());
        System.out.println(bookOne.getBookText());
        System.out.println(bookOne.getBookName());
    }
}
