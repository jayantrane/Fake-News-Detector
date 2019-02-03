package com.sj.newsfacter;

public class Message {

    //public String userName;
    public String article;
    public String summarized;
    public String relevant;
    public String fakeProb;
    public String approvers[];
    public String disapprovers[];
    public Message() {
    }

    public Message(String userName, String article) {
        //this.userName = userName;
        this.article = article;
        this.summarized = " ";
        this.relevant = " ";
        this.fakeProb = " ";
    }

    public Message(String userName, String article, String summarized, String relevant, String fakeProb) {
        this.userName = userName;
        this.article = article;
        this.summarized = summarized;
        this.relevant = relevant;
        this.fakeProb = fakeProb;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSummarized() {
        return summarized;
    }

    public void setSummarized(String summarized) {
        this.summarized = summarized;
    }

    public String getRelevant() {
        return relevant;
    }

    public void setRelevant(String relevant) {
        this.relevant = relevant;
    }

    public String getFakeProb() {
        return fakeProb;
    }

    public void setFakeProb(String fakeProb) {
        this.fakeProb = fakeProb;
    }
}
