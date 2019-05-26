package com.example.user.el;

/**
 * Created by user on 2019/5/27.
 */

public class friend {
    private String[] noList;
    private String[] nameList;
    private String[] gradeList;
    //定义三个静态变量
    public void setList() {
        String Text = "1:luzhangchi:260;2:liuyulin:230;3:lzh:160";
        String[] Items = Text.split(";");
        noList = new String[Items.length];
        nameList = new String[Items.length];
        gradeList = new String[Items.length];
        for(int i=0;i<Items.length;i++) {
            String[] No = Items[i].split(":");
            noList[i] = No[0];
            nameList[i] = No[1];
            gradeList[i] = No[2];
        }
    }
    //存入数组的方法，得到序号，姓名，分数三个数组。
    public int getItemsLength() {
        String Text = "1:luzhangchi:260;2:liuyulin:230;3:lzh:160";
        String[] Items = Text.split(";");
        return Items.length;

    }
    //得到数组内元素的方法，输入数组名，索引，得到元素。为了你们能方便一点，也可以不用8.
    public String getList(String s,int n) {
        if(s=="noList") {
            return noList[n];
        }
        else if(s=="nameList") {
            return nameList[n];
        }
        else {
            return gradeList[n];
        }
    }

}
