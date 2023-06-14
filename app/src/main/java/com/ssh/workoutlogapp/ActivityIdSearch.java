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

public class ActivityIdSearch extends AppCompatActivity {


    static SQLiteDatabase db;
    static MySQLiteOpenHelperUser helper;

    EditText etHp;
    TextView tvId1, tvId2;


    Button btnOk, btnCancel, btnHpSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_search);

        Toolbar toolbar = findViewById(R.id.exerciseListToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setTitle("");

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperUser(
                ActivityIdSearch.this, // 현재 화면의 context
                "user.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        etHp = (EditText) findViewById(R.id.etHp);
        tvId1 = (TextView) findViewById(R.id.tvId1);
        tvId2 = (TextView) findViewById(R.id.tvId2);

        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
        btnHpSearch = findViewById(R.id.btnHpSearch);

        btnHpSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findHp = etHp.getText().toString().trim();

                if (findHp.equals("")) {
                    Toast.makeText(ActivityIdSearch.this, "연락처를 입력하세요.", Toast.LENGTH_SHORT).show();
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
                        tvId2.setText(id);

                    }
                }
                etHp.setEnabled(false);
                btnHpSearch.setEnabled(false);
                c.close();
                db.close();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(ActivityIdSearch.this, LoginActivity.class);
                startActivity(login);
                finish();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login = new Intent(ActivityIdSearch.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });

    }
}