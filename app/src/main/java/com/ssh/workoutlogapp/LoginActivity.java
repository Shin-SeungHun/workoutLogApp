package com.ssh.workoutlogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase db;
    MySQLiteOpenHelperUser helper;
    EditText etId, etPw;

    TextView tvIdSearch, tvPwSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        helper = new MySQLiteOpenHelperUser(
                LoginActivity.this, //현재 화면의 내용
                "user.db", //파일명
                null, //커서 팩토리
                1); //버전 번호

        tvIdSearch = (TextView)findViewById(R.id.tvIdSearch);
        tvPwSearch = (TextView)findViewById(R.id.tvPwSearch);

        //아이디 비번 연동
        etId = (EditText) findViewById(R.id.etId);
        etPw = (EditText) findViewById(R.id.etPw);

        //버튼 연동
        findViewById(R.id.btnLogin).setOnClickListener(mClick);
        findViewById(R.id.btnJoin).setOnClickListener(mClick);
        findViewById(R.id.tvIdSearch).setOnClickListener(mClick);
        findViewById(R.id.tvPwSearch).setOnClickListener(mClick);
    }

    View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnLogin:
                    String id = etId.getText().toString(); //화면에 입력한 아이디 가져오기
                    String pw = etPw.getText().toString(); //화면에 입력한 비밀번호 가져오기
                    if (id.equals("")) {
                        Toast.makeText(LoginActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (pw.equals("")) {
                        Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    boolean loginCheck = false;
                    //디비 테이블에 id, pw 보내서 로그인 처리
                    loginCheck = dbLoginCheck(id, pw);

                    if (loginCheck == true) {
                        Intent login = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(login);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    break;

                case R.id.btnJoin:
                    Intent join = new Intent(LoginActivity.this, JoinActivity.class);
                    startActivity(join);
//                    Intent calendar = new Intent(LoginActivity.this, CalendarActivity.class);
//                    startActivity(calendar);
//                    Intent join = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(join);
//                    finish();
                    break;

                case R.id.tvIdSearch:
                    Intent idSearch = new Intent(LoginActivity.this, ActivityIdSearch.class);
                    startActivity(idSearch);
                    //finish();
                    break;

                    case R.id.tvPwSearch:
                    Intent pwSearch = new Intent(LoginActivity.this, ActivityPwSearch.class);
                    startActivity(pwSearch);
                    //finish();
                    break;
            }
        }
    };

    public boolean dbLoginCheck(String loginId, String loginPw) {
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

            if (loginId.equals(id)) {
                if (loginPw.equals(pw)) {
                    //아이디 비밀번호가 맞을경우 종료 후 true값 리턴
                    c.close();
                    db.close();
                    Toast.makeText(this, "로그인되었습니다.", Toast.LENGTH_SHORT).show();

                    return true;
                }
            }

        }

        c.close();
        db.close();

        return false;
    }
}