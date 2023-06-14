package com.ssh.workoutlogapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelperUser extends SQLiteOpenHelper {

    public MySQLiteOpenHelperUser(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        // SQLiteOpenHelper 가 최초 실행 되었을 때
        String sqlUser = "create table user (" +
                "idx integer primary key autoincrement, " +
                "id text, " +
                "pw text, " +
                "name text, " +
                "hp text, " +
                "birth text, " +
                "gender text, " +
                "addr text);";
        db.execSQL(sqlUser);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlUser = "drop table if exists user";
        db.execSQL(sqlUser);


        onCreate(db); // 테이블을 지웠으므로 다시 테이블을 만들어주는 과정
    }
}
