package com.ssh.workoutlogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPwSearch extends AppCompatActivity {

    static SQLiteDatabase db;
    static MySQLiteOpenHelperUser helper;

    EditText etHp, etId;

    TextView tvPw;

    Button btnOk, btnCancel, btnSearch, btnIdSearch, btnHpSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_search);

        Toolbar toolbar = findViewById(R.id.exerciseListToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setTitle("");

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperUser(
                ActivityPwSearch.this, // 현재 화면의 context
                "user.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        etHp = (EditText) findViewById(R.id.etHp);
        etId = (EditText) findViewById(R.id.etId);
        tvPw = (TextView) findViewById(R.id.tvPw);

        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
        btnSearch = findViewById(R.id.btnSearch);
        btnIdSearch = findViewById(R.id.btnIdSearch);
        btnHpSearch = findViewById(R.id.btnHpSearch);

        btnHpSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findHp = etHp.getText().toString().trim();

                if (findHp.equals("")) {
                    Toast.makeText(ActivityPwSearch.this, "연락처를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }


                db = helper.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM user", null);

                while (c.moveToNext()) {
                    int idx = c.getInt(0);
                    String id = c.getString(1);
                    String pw = c.getString(2);
                    String name = c.getString(3);
                    String hp = c.getString(4);
                    String birth = c.getString(5);
                    String gender = c.getString(6);
                    String addr = c.getString(7);
                    if (findHp.equals(hp)) {

                    }
                }
                etHp.setEnabled(false);
                btnHpSearch.setEnabled(false);
                c.close();
                db.close();
            }
        });

        btnIdSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findId = etId.getText().toString().trim();

                if (findId.equals("")) {
                    Toast.makeText(ActivityPwSearch.this, " 아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }


                db = helper.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM user", null);

                while (c.moveToNext()) {
                    int idx = c.getInt(0);
                    String id = c.getString(1);
                    String pw = c.getString(2);
                    String name = c.getString(3);
                    String hp = c.getString(4);
                    String birth = c.getString(5);
                    String gender = c.getString(6);
                    String addr = c.getString(7);
                    if (findId.equals(id)) {

                    }
                }
                etId.setEnabled(false);
                btnIdSearch.setEnabled(false);
                c.close();
                db.close();
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findHp = etHp.getText().toString().trim();
                String findId = etId.getText().toString().trim();

                if (findHp.equals("")) {
                    Toast.makeText(ActivityPwSearch.this, "연락처를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (findId.equals("")) {
                    Toast.makeText(ActivityPwSearch.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean pwSearch = false;

                pwSearch = dbPwSearch(findHp, findId);

                if (pwSearch == true) {

                } else {
                    Toast.makeText(ActivityPwSearch.this, "찾기에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(ActivityPwSearch.this, LoginActivity.class);
                startActivity(login);
                finish();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login = new Intent(ActivityPwSearch.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });


    }

    public boolean dbPwSearch(String findHp, String findId) {
        db = helper.getReadableDatabase(); //db객체를 가져온다 읽기전용
        Cursor c = db.rawQuery("SELECT * FROM user", null);

        while (c.moveToNext()) {
            int idx = c.getInt(0);
            String id = c.getString(1);
            String pw = c.getString(2);
            String name = c.getString(3);
            String hp = c.getString(4);
            String birth = c.getString(5);
            String gender = c.getString(6);
            String addr = c.getString(7);

            if (findHp.equals(hp)) {
                if (findId.equals(id)) {

                    tvPw.setText(pw);

                    c.close();
                    db.close();


                    return true;
                }
            }

        }

        c.close();
        db.close();

        return false;
    }
}