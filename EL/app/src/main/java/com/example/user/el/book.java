package com.example.user.el;

/**
 * Created by user on 2019/5/27.
 */

public class book{
    private String bookName;
    private int bookPrice;
    private String bookText;
    public void setBookName(String s) {
        bookName = s;
    }
    public void setBookPrice(int n) {
        bookPrice = n;
    }
    public void setBookText(String str) {
        bookText = str;
    }
    public String getBookText() {
        return bookText;
    }
    public int getBookPrice() {
        return bookPrice;
    }
    public String getBookName() {
        return bookName;
    }
}
