package com.ssh.workoutlogapp;

public class WorkoutLog {
    String date;
    String name;
    String kg;
    String sets;
    String reps;
    int id;

    public WorkoutLog() {
    }

    public WorkoutLog(int id, String date, String name, String kg, String sets, String reps) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.kg = kg;
        this.sets = sets;
        this.reps = reps;


    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    @Override
    public String toString() {
        return "WorkoutLog{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", kg='" + kg + '\'' +
                ", sets='" + sets + '\'' +
                ", reps='" + reps + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
