package com.ssh.workoutlogapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewMemo extends AppCompatActivity implements MemoAdapter.MyRecyclerViewClickListener {
    static ArrayList<Memo> memoList = new ArrayList<>();
    static MemoAdapter adapter = new MemoAdapter(memoList);

    static int i = 0;
    static RecyclerView recyclerView;
    static SQLiteDatabase db;
    static MySQLiteOpenHelperMemo helper;

    ItemTouchHelper helper2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_memo);

        Toolbar toolbar = findViewById(R.id.memoListToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setTitle("                   메모 리스트");
        //데이베이스 생성.
        helper = new MySQLiteOpenHelperMemo(
                RecyclerViewMemo.this, // 현재 화면의 context
                "memo.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호


        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(adapter);
//        for (int i=0; i<20; i++) {
//            dataList.add(new Ex28ItemData("홍길동-"+i,"0101234567"+i,"대전 "+i+"번지","남자"+i+"호",20+i));
//        }
        recyclerView.setAdapter(adapter);

        //첫화면에 디비읽어서 리사이클러뷰에 뿌리기...
        selectMemoList();

        //버튼 이벤트처리
//        findViewById(R.id.btnAdd).setOnClickListener(mClick);
//        findViewById(R.id.btnEdit).setOnClickListener(mClick);
//        findViewById(R.id.btnDel).setOnClickListener(mClick);

        adapter.setOnClickListener(this);

        //ItemTouchHelper 생성
        helper2 = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        //RecyclerView에 ItemTouchHelper 붙이기
        helper2.attachToRecyclerView(recyclerView);

    }

    View.OnClickListener mClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.btnAdd:
//                    Intent intentAdd = new Intent(RecyclerViewMemo.this, WorkoutMainActivity.class);
//                    startActivity(intentAdd);
//                    break;
//                case R.id.btnEdit:
//                    Intent intentEdit = new Intent(RecyclerViewMemo.this, EditActivity.class);
//                    startActivity(intentEdit);
//                    break;
//                case R.id.btnDel:
//                    Intent intentDel = new Intent(RecyclerViewMemo.this, DelActivity.class);
//                    startActivity(intentDel);
//                    break;
            }
        }
    };

    @Override
    public void onContentClicked(int position) {
        Toast.makeText(getApplicationContext(), "내용 : " + position, Toast.LENGTH_SHORT).show();
    }




    public void onItemLongClicked(int position) {
        Toast.makeText(getApplicationContext(), "추가화면으로이동됩니다.", Toast.LENGTH_SHORT).show();
        Intent intentAdd = new Intent(RecyclerViewMemo.this, MyPageActivity_.class);
        startActivity(intentAdd);
    }


    public static void selectMemoList() {

        adapter.memos.clear();

        db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
        Cursor c = db.rawQuery("SELECT * FROM memo", null);

        while (c.moveToNext()) {

            int idx = c.getInt(0);
            String date = c.getString(1);
            String content = c.getString(2);


            memoList.add(new Memo(idx, date, content));

        }
        c.close();
        db.close();



    }

}