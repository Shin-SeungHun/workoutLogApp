package com.ssh.workoutlogapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DelActivity extends AppCompatActivity {
    TextView tvName, tvHp, tvBirth, tvGender, tvAddr;
    EditText etId, etPw, etPwCheck;

    Button btnDel, btnFind, btnCancel;
    //전달할 변수들
    String id, pw, name, hp, birth, gender, addr;
    static SQLiteDatabase db;
    static MySQLiteOpenHelperUser helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del);
        //데이베이스 생성.
        helper = new MySQLiteOpenHelperUser(
                DelActivity.this, // 현재 화면의 context
                "user.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        Toolbar toolbar = findViewById(R.id.exerciseListToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setTitle("");

        etId = (EditText) findViewById(R.id.etId);
        etPw = (EditText) findViewById(R.id.etPw);
        etPwCheck = (EditText) findViewById(R.id.etPwCheck);
        tvName = (TextView) findViewById(R.id.tvName);
        tvHp = (TextView) findViewById(R.id.tvHp);
        tvAddr = (TextView) findViewById(R.id.tvAddr);
        tvBirth = (TextView)findViewById(R.id.tvBirth);
        tvGender = (TextView)findViewById(R.id.tvGender);

        btnFind = (Button) findViewById(R.id.btnFind);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(DelActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findId = etId.getText().toString().trim();

                if (findId.equals("")) {
                    Toast.makeText(DelActivity.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
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
                    tvName.setText(name);
                    tvHp.setText(hp);

                    tvAddr.setText(addr);
                    tvBirth.setText(birth);
                    tvGender.setText(gender);

                }
                }
                c.close();
                db.close();
            }


//                for (int i = 0; i < RecyclerViewUser.adapter.getItemCount(); i++) {
//                    //Log.d("아이템값체크","아아템:"+Ex25FriendsListActivity.adapter.items.get(i).getName());
//                    if (findId.equals(RecyclerViewUser.adapter.user.get(i).getId())) {
//                        //값자리 초기화
//                        etId.setText(RecyclerViewUser.adapter.user.get(i).getId());
//                        tvName.setText(RecyclerViewUser.adapter.user.get(i).getName());
//                        tvHp.setText(RecyclerViewUser.adapter.user.get(i).getHp());
//                        tvBirth.setText(RecyclerViewUser.adapter.user.get(i).getBirth());
//                        tvGender.setText(RecyclerViewUser.adapter.user.get(i).getGender());
//                        tvAddr.setText(RecyclerViewUser.adapter.user.get(i).getAddr());
//
//                    }
//                }



        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String findId = etId.getText().toString();
                if (findId.equals("")) {
                    Toast.makeText(DelActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etPw.getText().toString().equals("")) {
                    Toast.makeText(DelActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etPwCheck.getText().toString().equals("")) {
                    Toast.makeText(DelActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etPwCheck.getText().toString().equals(etPw.getText().toString())) {

                } else if (!etPwCheck.getText().toString().equals(etPw.getText().toString())) {
                    Toast.makeText(DelActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < RecyclerViewUser.adapter.getItemCount(); i++) {
                    //Log.d("아이템값체크","아아템:"+Ex25FriendsListActivity.adapter.items.get(i).getName());
                    if (findId.equals(RecyclerViewUser.adapter.user.get(i).getId())) {
                        RecyclerViewUser.adapter.user.remove(i);
                        break;
                    }
                }
                //메인에 있는 리스트뷰에 변화가 생겨서 갱신.
                RecyclerViewUser.adapter.notifyDataSetChanged();

                //값자리 초기화
                etId.setText("");
                etPw.setText("");
                etPwCheck.setText("");
                tvName.setText("");
                tvBirth.setText("");
                tvGender.setText("");
                tvHp.setText("");
                tvAddr.setText("");


                Toast.makeText(DelActivity.this, "회원탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                delete(findId);

                Intent login = new Intent(DelActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });

    }

    public void delete(String id) {
        db = helper.getWritableDatabase();
        db.delete("user", "id='" + id + "'", null);
        Log.d("db", id + "가 정상적으로 삭제 되었습니다.");
        Log.d("회원탈퇴", "아이디: " + id + " / " + "비밀번호: " + pw + " / " + "이름: " + name + " / " + "연락처: " + hp + " / " + "생년월일: " + birth + "/" + "성별: " + gender + " / " + "주소: " + addr + " db저장");

        Toast.makeText(getApplicationContext(), id + "의 정보가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        db.close();
    }

}