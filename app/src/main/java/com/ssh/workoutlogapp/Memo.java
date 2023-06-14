package com.ssh.workoutlogapp;

public class Memo {

    String date, content;

    int id;

    public Memo() {
    }

    public Memo(int id, String date, String content) {
        this.id = id;
        this.date = date;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", id=" + id +
                '}';
    }
}
