package com.example.user.el;

/**
 * Created by user on 2019/5/27.
 */

public class friend {
    public String number;
    public String name;
    public String grade;

    public friend(int number,String name, int grade){
        this.name = name;
        this.number = String.valueOf(number);
        this.grade = String.valueOf(grade);
    }

}
