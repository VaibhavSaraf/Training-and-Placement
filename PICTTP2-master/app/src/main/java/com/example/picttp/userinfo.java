package com.example.picttp;

import android.widget.EditText;

import java.util.ArrayList;

public class               userinfo {
    public String username,userphone,usermis,usermail,userpassword,usercpassword,bio,marks,projects,skills,lang,address,flag;

    public ArrayList <String> docu ;

    public userinfo() {     }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUsermis() {
        return usermis;
    }

    public void setUsermis(String usermis) {
        this.usermis = usermis;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsercpassword() {
        return usercpassword;
    }

    public void setUsercpassword(String usercpassword) {
        this.usercpassword = usercpassword;
    }

    public ArrayList<String> getDocu() {
        return docu;
    }

    public void setDocu(ArrayList<String> docu) {
        this.docu = docu;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public userinfo(String username, String userphone, String usermis, String usermail, String userpassword, String usercpassword, String bio, String marks, String projects, String skills, String lang, String address, String flag) {
        this.username = username;
        this.userphone = userphone;
        this.usermis = usermis;
        this.usermail = usermail;
        this.userpassword = userpassword;
        this.usercpassword = usercpassword;
        this.bio = bio;
        this.marks = marks;
        this.projects = projects;
        this.skills = skills;
        this.lang = lang;
        this.address = address;
        this.flag = flag;
    }
}
