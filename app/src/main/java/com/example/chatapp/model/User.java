package com.example.chatapp.model;

public class User {


    String id;
    String name;
    String email;
    String pass;
    String img;

    public User() {
    }

    ;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }
}
