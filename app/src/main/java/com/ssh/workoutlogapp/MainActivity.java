package com.ssh.workoutlogapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SubItemAdapter.MyRecyclerViewClickListener {

    Button btn1, btn2, btn3, btn4, btn5;

    int[] ex = {R.drawable.p1, R.drawable.p2, R.drawable.p3,
            R.drawable.p4, R.drawable.p5, R.drawable.p6,
            R.drawable.p7, R.drawable.p8, R.drawable.p9, R.drawable.p10, R.drawable.p11,
            R.drawable.p12, R.drawable.p13, R.drawable.p14,
            R.drawable.p15, R.drawable.p16, R.drawable.p17, R.drawable.p18, R.drawable.p19,
            R.drawable.p20, R.drawable.p21, R.drawable.physical,
            R.drawable.physical, R.drawable.physical, R.drawable.physical};

    String[] list = {"어깨통증, 5분만 따라해보세요! [스트레칭 솔루션]", "허리디스크, 허리통증에 좋은 장요근 스트레칭법", "연골연화증, 무릎에 좋은 스트레칭법! #대퇴직근", "문고리만 있으면 됩니다! 굽은등에 매우 시원해요! #광배근 스트레칭", "두통에 좋은 경판상근 스트레칭 #splenius cervicis, 목널판근", "바로 효과가 나타나는 골반 교정 루틴 [골반전방경사] 12주 루틴", "거북목 교정법 [3개월 교정루틴] (Made in Australia) 빡빡이 아저씨의 체형교정루틴-1 [피지컬갤러리]", "일자목VS거북목 체형교정법", "50만명이 효과를 본 라운드숄더 교정루틴 A (by 호주물리치료사) [피지컬갤러리]", "안면 비대칭, 골반 비대칭을 잡아주는 호주물리치료사의 골반 교정 루틴 (골반 측굴 편)", "아무도 알려주지 않던 어깨충돌증후군의 핵심 원리, 재활 운동법", "손목이 뻐근할 때 굉장히 시원합니다! [손목터널증후군 마사지 재활]", "현직 물리치료사가 알려주는 단계별 발목 재활 운동법", "허리에서 뚜둑 소리가 난다면? (척추분리증 재활)", "숄더패킹이 중요한건 알겠는데...", "왜 달리기만 하면 아플까?", "뇌과학자가 말하는 근력운동을 꼭 해야하는 이유", "대포알 삼각근을 위한 최고의 프레스는???", "이제 달리기 시작합시다!", "수면위생, 좋은 수면이란 무엇인가", "새로운 기회의 창, 스트레스", "준비중...", "준비중...", "준비중...", "준비중..."};

    String[] list2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};


    ArrayList<SubItem> dataList = new ArrayList<>();
    final SubItemAdapter adapter = new SubItemAdapter(dataList);
    static int i = 0;

    Button btnWorkout;

    private Toolbar toolbar;

    RecyclerView recyclerView;

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);


        btn1.setOnClickListener(mClick);
        btn2.setOnClickListener(mClick);
        btn3.setOnClickListener(mClick);
        btn4.setOnClickListener(mClick);
        btn5.setOnClickListener(mClick);

        btnWorkout = (Button) findViewById(R.id.btnWorkout);
        btnWorkout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent workout = new Intent(MainActivity.this, WorkoutMainActivity.class);
                startActivity(workout);
                //finish();
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < 25; i++) {
            dataList.add(new SubItem(ex[i], list[i], list2[i]));
        }


        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(this);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("                     운동일지");

        // 액션바 객체
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //뒤로가기버튼 이미지 적용
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);


        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.myPage:
                        menuItem.setChecked(false);
                        drawerLayout.closeDrawers();
//                        Intent mypage = new Intent(MainActivity.this, MyPageActivity.class);
                        Intent mypage = new Intent(MainActivity.this, MypageActivity.class);
                        startActivity(mypage);
                        break;
                    //return true;

                    case R.id.userEdit:
                        menuItem.setChecked(false);

                        drawerLayout.closeDrawers();
                        Intent edit = new Intent(MainActivity.this, EditActivity.class);
                        startActivity(edit);
                        break;
                    // return true;

                    case R.id.userDel:
                        menuItem.setChecked(false);

                        Intent del = new Intent(MainActivity.this, DelActivity.class);
                        startActivity(del);
                        drawerLayout.closeDrawers();
                        break;
                    //  return true;
                    case R.id.userLogout:
                        menuItem.setChecked(false);

                        Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(logout);
                        drawerLayout.closeDrawers();
                        break;
                    //  return true;

                }

                return false;
            }
        });


    }

    View.OnClickListener mClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // 클릭된 버튼에 따라 표시할 아이템을 필터링하여 새로운 리스트에 저장
            List<SubItem> filteredList = new ArrayList<>();
            switch (v.getId()) {
                case R.id.btn1:
                    for (int i = 0; i < 5; i++) {
                        filteredList.add(new SubItem(ex[i], list[i], list2[i]));
                    }
                    break;
                case R.id.btn2:
                    for (int i = 5; i < 10; i++) {
                        filteredList.add(new SubItem(ex[i], list[i], list2[i]));
                    }
                    break;
                case R.id.btn3:
                    for (int i = 10; i < 15; i++) {
                        filteredList.add(new SubItem(ex[i], list[i], list2[i]));
                    }
                    break;
                case R.id.btn4:
                    for (int i = 15; i < 20; i++) {
                        filteredList.add(new SubItem(ex[i], list[i], list2[i]));
                    }
                    break;
                case R.id.btn5:
                    for (int i = 20; i < 25; i++) {
                        filteredList.add(new SubItem(ex[i], list[i], list2[i]));
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


    private void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //메뉴 선택시 네비게이션 호출
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onItemClicked(int position) {
//         Toast.makeText(getApplicationContext(), "Item : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTitleClicked(int position) {
//         Toast.makeText(getApplicationContext(), "Title : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContentClicked(int position) {
//         Toast.makeText(getApplicationContext(), "Content : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageViewClicked(int position) {
//         Toast.makeText(getApplicationContext(), "Image : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClicked(int position) {

    }
}