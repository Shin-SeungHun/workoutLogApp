//package com.ssh.workoutlogapp;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class RecyclerViewWorkout extends AppCompatActivity implements WorkoutAdapter.MyRecyclerViewClickListener {
//    static ArrayList<WorkoutLog> workoutList = new ArrayList<>();
//    static WorkoutAdapter adapter = new WorkoutAdapter(workoutList);
//
//    static int i = 0;
//    static RecyclerView recyclerView;
//    static SQLiteDatabase db;
//    static MySQLiteOpenHelperWorkout helper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recyclerview_workout);
//
//        //데이베이스 생성.
//        helper = new MySQLiteOpenHelperWorkout(
//                RecyclerViewWorkout.this, // 현재 화면의 context
//                "workout.db", // 파일명
//                null, // 커서 팩토리
//                1); // 버전 번호
//
//
//        recyclerView = findViewById(R.id.recyclerView);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
////        for (int i=0; i<20; i++) {
////            dataList.add(new Ex28ItemData("홍길동-"+i,"0101234567"+i,"대전 "+i+"번지","남자"+i+"호",20+i));
////        }
//        //첫화면에 디비읽어서 리사이클러뷰에 뿌리기...
//       // selectWorkoutList();
//
//        //버튼 이벤트처리
//        findViewById(R.id.btnAdd).setOnClickListener(mClick);
//        findViewById(R.id.btnEdit).setOnClickListener(mClick);
//        findViewById(R.id.btnDel).setOnClickListener(mClick);
//
//        adapter.setOnClickListener(this);
//    }
//
//    View.OnClickListener mClick = new View.OnClickListener() {
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.btnAdd:
//                    Intent intentAdd = new Intent(RecyclerViewWorkout.this, WorkoutMainActivity.class);
//                    startActivity(intentAdd);
//                    break;
//                case R.id.btnEdit:
//                    Intent intentEdit = new Intent(RecyclerViewWorkout.this, EditActivity.class);
//                    startActivity(intentEdit);
//                    break;
//                case R.id.btnDel:
//                    Intent intentDel = new Intent(RecyclerViewWorkout.this, DelActivity.class);
//                    startActivity(intentDel);
//                    break;
//            }
//        }
//    };
//
//
//
//
//
//
//    public void onItemLongClicked(int position) {
//        Toast.makeText(getApplicationContext(), "추가화면으로이동됩니다.", Toast.LENGTH_SHORT).show();
//        Intent intentAdd = new Intent(RecyclerViewWorkout.this, MyPageActivity.class);
//        startActivity(intentAdd);
//    }
//
//    @Override
//    public void onItemClicked(int position) {
//
//    }
//
//    @Override
//    public void onTitleClicked(int position) {
//
//    }
//
//    @Override
//    public void onContentClicked(int position) {
//
//    }
//
//    @Override
//    public void onButtonClicked(int position) {
//
//    }
//
//
////    public static void selectWorkoutList() {
////
////        adapter.workout.clear();
////
////        db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
////        Cursor c = db.rawQuery("SELECT * FROM workout", null);
////
////        while (c.moveToNext()) {
////
////            int idx = c.getInt(0);
////            String date = c.getString(1);
////            String name = c.getString(2);
////            String kg = c.getString(3);
////            String sets = c.getString(4);
////            String reps = c.getString(5);
////
////
////            workoutList.add(new WorkoutLog(date, name, kg, sets, reps));
////
////        }
////        c.close();
////        db.close();
////        recyclerView.setAdapter(adapter);
////
////
////    }
//
//}