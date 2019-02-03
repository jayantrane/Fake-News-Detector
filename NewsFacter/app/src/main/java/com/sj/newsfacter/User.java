package com.sj.newsfacter;

public class User {

    public String name;
    public String balance;
    public String msgCount;

    public User() {
    }

    public User(String name, String balance, String msgCount) {
        this.name = name;
        this.balance = balance;
        this.msgCount = msgCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }
}
