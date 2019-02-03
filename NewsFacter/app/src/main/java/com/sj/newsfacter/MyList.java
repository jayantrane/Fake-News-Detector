package com.sj.newsfacter;

public class MyList {
    private String head;
    private String desc;

    //constructor initializing values
    public MyList(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }

    //getters
    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}