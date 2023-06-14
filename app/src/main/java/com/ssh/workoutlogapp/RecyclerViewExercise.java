package com.ssh.workoutlogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExercise extends AppCompatActivity implements ItemAdapter.MyRecyclerViewClickListener {
    RecyclerView recyclerView;

    TextView tvToday;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    int[] ex = {R.drawable.e1, R.drawable.e2, R.drawable.e3,
            R.drawable.e4, R.drawable.e5, R.drawable.e6,
            R.drawable.e7, R.drawable.e8, R.drawable.e9, R.drawable.e10, R.drawable.e11,
            R.drawable.e12, R.drawable.e13, R.drawable.e14,
            R.drawable.e15, R.drawable.e16, R.drawable.e17, R.drawable.e18, R.drawable.physical,
            R.drawable.physical, R.drawable.physical,
            R.drawable.e23, R.drawable.e24, R.drawable.e25};



    String[] list = {"풀업", "랫풀다운", "루마니안 데드리프트", "푸시업", "벤치 프레스", "딥스", "숄더 프레스", "사이드 레터럴 레이즈", "벤트 오버 레이즈", "스쿼트", "컨벤셔널 데드리프트", "런지", "바벨 컬", "덤벨 컬", "해머 컬", "라잉 트라이셉스 익스텐션", "덤벨 킥백", "덤벨 오버헤드 익스텐션", "데드버그", "버드독", "행잉 레그레이즈", "런닝", "싸이클", "스텝밀",};
    ArrayList<Item> dataList = new ArrayList<>();
    final ItemAdapter adapter = new ItemAdapter(dataList);
    static int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_exercise);

        Toolbar toolbar = findViewById(R.id.exerciseListToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setTitle("");

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);

        btn1.setOnClickListener(mClick);
        btn2.setOnClickListener(mClick);
        btn3.setOnClickListener(mClick);
        btn4.setOnClickListener(mClick);
        btn5.setOnClickListener(mClick);
        btn6.setOnClickListener(mClick);
        btn7.setOnClickListener(mClick);
        btn8.setOnClickListener(mClick);

        tvToday = (TextView) findViewById(R.id.tvToday);
        // 다른 액티비티에서 인텐트로 전달된 데이터 추출
        String tvTodayText = getIntent().getStringExtra("tvTodayText");
        tvToday.setText(tvTodayText);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < 3; i++) {
            dataList.add(new Item(ex[i], list[i]));
        }

        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(this);
    }

    View.OnClickListener mClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // 클릭된 버튼에 따라 표시할 아이템을 필터링하여 새로운 리스트에 저장
            List<Item> filteredList = new ArrayList<>();
            switch (v.getId()) {
                case R.id.btn1:
                    for (int i = 0; i < 3; i++) {
                        filteredList.add(new Item(ex[i], list[i]));
                    }
                    break;
                case R.id.btn2:
                    for (int i = 3; i < 6; i++) {
                        filteredList.add(new Item(ex[i], list[i]));
                    }
                    break;
                case R.id.btn3:
                    for (int i = 6; i < 9; i++) {
                        filteredList.add(new Item(ex[i], list[i]));
                    }
                    break;
                case R.id.btn4:
                    for (int i = 9; i < 12; i++) {
                        filteredList.add(new Item(ex[i], list[i]));
                    }
                    break;
                case R.id.btn5:
                    for (int i = 12; i < 15; i++) {
                        filteredList.add(new Item(ex[i], list[i]));
                    }
                    break;
                case R.id.btn6:
                    for (int i = 15; i < 18; i++) {
                        filteredList.add(new Item(ex[i], list[i]));
                    }
                    break;
                case R.id.btn7:
                    for (int i = 18; i < 21; i++) {
                        filteredList.add(new Item(ex[i], list[i]));
                    }
                    break;
                case R.id.btn8:
                    for (int i = 21; i <24; i++) {
                        filteredList.add(new Item(ex[i], list[i]));
                    }
                    break;
            }

            // 필터링된 결과를 어댑터에 전달하여 리사이클러뷰 갱신
            // 어댑터의 데이터 리스트를 갱신하여 필터링된 데이터를 적용
            dataList.clear(); // 기존 데이터 리스트를 비움
            dataList.addAll(filteredList); // 필터링된 데이터를 추가
            adapter.notifyDataSetChanged(); // 어댑터에 데이터가 갱신되었음을 알림
        }
    };
//    위 코드에서는 버튼 클릭 이벤트가 발생하면 해당 버튼에 따라 필터링된 아이템들을 새로운 리스트에 저장하고, 그 리스트를 어댑터에 전달하여 리사이클러뷰를 갱신하는 방식으로 동작합니다. adapter.set() 메서드는 어댑터에 새로운 데이터를 설정하는 메서드로, 이를 호출함으로써 리사이클러뷰가 갱신됩니다. 필터링된 아이템들만을 어댑터에 전달하면, 해당 버튼에 있는 아이템들만이 리사이클러뷰에 표시될 것입니다.


    @Override
    public void onItemClicked(int position) {
//        Toast.makeText(getApplicationContext(), "Item : " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTitleClicked(int position) {
//        Toast.makeText(getApplicationContext(), "Title : " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onImageViewClicked(int position) {
//        Toast.makeText(getApplicationContext(), "Image : " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onButtonClicked(int position) {

    }
}