package com.ssh.workoutlogapp;

public class User {
    String id;
    String pw;
    String name;
    String hp;
    String gender;
    String birth;
    String addr;

    public User() {
    }

    public User(String id, String pw, String name, String hp, String birth, String gender, String addr) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.hp = hp;
        this.birth = birth;
        this.gender = gender;
        this.addr = addr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", hp='" + hp + '\'' +
                ", gender='" + gender + '\'' +
                ", birth='" + birth + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
