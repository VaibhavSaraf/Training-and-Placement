package com.example.picttp;

public class send_news {

    public String title1,subject1,des1;

    public send_news() {
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getDes1() {
        return des1;
    }

    public void setDes1(String des1) {
        this.des1 = des1;
    }

    public send_news(String title1, String subject1, String des1) {
        this.title1 = title1;
        this.subject1 = subject1;
        this.des1 = des1;
    }
}
