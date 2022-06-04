package com.example.chatapp.model;

public class SinhVien {
    private String name, lop;
    private int age;

    public SinhVien(int age) {
        this.age = age;
    }

    public SinhVien(String name, String lop, int age) {
        this.name = name;
        this.lop = lop;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
