package com.ssh.workoutlogapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class RecyclerViewUser extends AppCompatActivity implements UserAdapter.MyRecyclerViewClickListener {
    static ArrayList<User> userList = new ArrayList<>();
    static UserAdapter adapter = new UserAdapter(userList);

    static int i = 0;
    static RecyclerView recyclerView;
    static SQLiteDatabase db;
    static MySQLiteOpenHelperUser helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_user);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperUser(
                RecyclerViewUser.this, // 현재 화면의 context
                "user.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        Toolbar toolbar = findViewById(R.id.userListToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);//뒤로가기
        //getSupportActionBar().setTitle("");

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
      //  recyclerView.setAdapter(adapter);
//        for (int i=0; i<20; i++) {
//            dataList.add(new Ex28ItemData("홍길동-"+i,"0101234567"+i,"대전 "+i+"번지","남자"+i+"호",20+i));
//        }
        //첫화면에 디비읽어서 리사이클러뷰에 뿌리기...
        selectUserList();

        //버튼 이벤트처리
        findViewById(R.id.btnAdd).setOnClickListener(mClick);
        findViewById(R.id.btnEdit).setOnClickListener(mClick);
        findViewById(R.id.btnDel).setOnClickListener(mClick);

        adapter.setOnClickListener(this);
    }

    View.OnClickListener mClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAdd:
                    Intent intentAdd = new Intent(RecyclerViewUser.this, MainActivity.class);
                    startActivity(intentAdd);
                    break;
                case R.id.btnEdit:
                    Intent intentEdit = new Intent(RecyclerViewUser.this, EditActivity.class);
                    startActivity(intentEdit);
                    break;
                case R.id.btnDel:
                    Intent intentDel = new Intent(RecyclerViewUser.this, DelActivity.class);
                    startActivity(intentDel);
                    break;
            }
        }
    };

    @Override
    public void onIdClicked(int position) {
        Toast.makeText(getApplicationContext(), "아이디 : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPwClicked(int position) {
        Toast.makeText(getApplicationContext(), "비밀번호 : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNameClicked(int position) {
        Toast.makeText(getApplicationContext(), "이름 : " + position, Toast.LENGTH_SHORT).show();
    }

    public void onHpClicked(int position) {
        Toast.makeText(getApplicationContext(), "연락처 : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBirthClicked(int position) {
        Toast.makeText(getApplicationContext(), "생년월일 : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGenderClicked(int position) {
        Toast.makeText(getApplicationContext(), "성별 : " + position, Toast.LENGTH_SHORT).show();
    }

    public void onAddrClicked(int position) {
        Toast.makeText(getApplicationContext(), "주소 : " + position, Toast.LENGTH_SHORT).show();
    }


    public void onItemLongClicked(int position) {
        Toast.makeText(getApplicationContext(), "추가화면으로이동됩니다.", Toast.LENGTH_SHORT).show();
        Intent intentAdd = new Intent(RecyclerViewUser.this, MyPageActivity_.class);
        startActivity(intentAdd);
    }


    public static void selectUserList() {

        adapter.user.clear();

        db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
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

            userList.add(new User(id, pw, name, hp, birth, gender, addr));



        }
        c.close();
        db.close();
        recyclerView.setAdapter(adapter);


    }

}