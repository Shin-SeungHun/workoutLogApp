//package com.ssh.workoutlogapp;
//
//import android.content.Context;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class RecyclerViewMain extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //===== 테스트를 위한 더미 데이터 생성 ===================
//        ArrayList<String> testDataSet = new ArrayList<>();
//        for (int i = 0; i<20; i++) {
//            testDataSet.add("TEST DATA" + i);
//        }
//        //========================================================
//
//        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
//
//        //--- LayoutManager는 아래 3가지중 하나를 선택하여 사용 ---
//        // 1) LinearLayoutManager()
//        // 2) GridLayoutManager()
//        // 3) StaggeredGridLayoutManager()
//        //---------------------------------------------------------
////        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
////        recyclerView1.setLayoutManager(linearLayoutManager);  // LayoutManager 설정
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView1.setLayoutManager(linearLayoutManager);
//
//        MainAdapter customAdapter = new MainAdapter(testDataSet);
//        recyclerView1.setAdapter(customAdapter); // 어댑터 설정
//    }
//}
