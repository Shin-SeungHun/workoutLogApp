package com.ssh.workoutlogapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WorkoutMainActivity extends AppCompatActivity implements WorkoutAdapter.MyRecyclerViewClickListener{



    static ArrayList<WorkoutLog> dataList = new ArrayList<>();
    static WorkoutAdapter adapter = new WorkoutAdapter(dataList);

    static int i = 0;
    static RecyclerView recyclerView;

    static SQLiteDatabase db;
    static MySQLiteOpenHelperWorkout helper;

    ItemTouchHelper helper2;
    CalendarView calendarView;
    TextView tvToday;
    String tvTodayText;
    Button btnAdd;
    private Toolbar toolbar;
    private Toolbar toolbar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_main);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperWorkout(
                WorkoutMainActivity.this, // 현재 화면의 context
                "workout.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호


        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(this);

        //ItemTouchHelper 생성
        helper2 = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        //RecyclerView에 ItemTouchHelper 붙이기
        helper2.attachToRecyclerView(recyclerView);

        //첫화면에 디비읽어서 리사이클러뷰에 뿌리기...
        selectWorkoutList();

        toolbar2 = findViewById(R.id.workout_toolbar);
        setSupportActionBar(toolbar2);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(false);//뒤로가기
        getSupportActionBar().setTitle("");

        toolbar = findViewById(R.id.memo_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setTitle("");


        tvToday = findViewById(R.id.tvToday);
        calendarView = findViewById(R.id.calendarView);

        //날짜변환
        DateFormat formatter = new SimpleDateFormat(("yyyy-M-dd"));
        Date date = new Date(calendarView.getDate());
        tvToday.setText(formatter.format(date));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String day;
                day = year + "-" + (month + 1) + "-" + dayOfMonth;
                tvToday.setText(day);
            }
        });

        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent workout = new Intent(WorkoutMainActivity.this, RecyclerViewExercise.class);
                String tvTodayText = tvToday.getText().toString();
                workout.putExtra("tvTodayText", tvTodayText);
                startActivity(workout);

            }
        });


//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
//        {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
//            {
//                diaryTextView.setVisibility(View.VISIBLE);
//                save_Btn.setVisibility(View.VISIBLE);
//                contextEditText.setVisibility(View.VISIBLE);
//                textView2.setVisibility(View.INVISIBLE);
//                cha_Btn.setVisibility(View.INVISIBLE);
//                del_Btn.setVisibility(View.INVISIBLE);
//                diaryTextView.setText(String.format("%d / %d / %d", year, month + 1, dayOfMonth));
//                contextEditText.setText("");
//                checkDay(year, month, dayOfMonth);
//            }
//        });


//        btnMemo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
//                WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                        WindowManager.LayoutParams.WRAP_CONTENT,
//                        WindowManager.LayoutParams.WRAP_CONTENT,
//                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
//                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                        PixelFormat.TRANSLUCENT
//                );
//
//                View floatingView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.floating_view, null);
//                windowManager.addView(floatingView, params);
//
////                 메모장 띄우기
//                Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_TITLE, "memo.txt");
//                startActivityForResult(intent, 1);
//            }
//        });
    }

    //   메뉴 리소스 XML의 내용을 앱바(App Bar)에 반영
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.memo_toolbar, menu);
//        getMenuInflater().inflate(R.menu.workout_toolbar, menu);
//        Menu menu2 = ((Toolbar) findViewById(R.id.workout_toolbar)).getMenu();
//        getMenuInflater().inflate(R.menu.workout_toolbar, menu2);
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 메인 툴바 메뉴 생성
        getMenuInflater().inflate(R.menu.memo_toolbar, menu);

//        // 서브 툴바 메뉴 생성
//        MenuInflater inflater = new MenuInflater(this);
//        inflater.inflate(R.menu.workout_toolbar, toolbar2.getMenu());
//
//        // 서브 툴바에서 메인 툴바의 메뉴 아이템 제거
//        MenuItem mainItem1 = menu.findItem(R.id.btnMemo);
//       // MenuItem mainItem2 = menu.findItem(R.id.action_main2);
//        toolbar2.getMenu().removeItem(mainItem1.getItemId());
//      //  toolbarSub.getMenu().removeItem(mainItem2.getItemId());
        return true;
    }

    //앱바(App Bar)에 표시된 액션 또는 오버플로우 메뉴가 선택되면
    //액티비티의 onOptionsItemSelected() 메서드가 호출
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnMemo:
                Intent memo = new Intent(WorkoutMainActivity.this, RecyclerViewMemo.class);
                startActivity(memo);
                return true;
            case R.id.memoAdd:
//                Intent intent = new Intent(WorkoutMainActivity.this, MyDialogActivity.class);
//                 String tvTodayText = tvToday.getText().toString();
//
//                intent.putExtra("tvTodayText", tvTodayText);
//                startActivity(intent);
                Intent memoAdd = new Intent(WorkoutMainActivity.this, MemoAddActivity.class);
// tvToday의 텍스트를 인텐트하여 다른 액티비티로 전달
                tvTodayText = tvToday.getText().toString();

                memoAdd.putExtra("tvTodayText", tvTodayText);

                startActivity(memoAdd);
                return true;
            case R.id.AllDel:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void selectWorkoutList() {

        adapter.workoutLogs.clear();

        db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
        Cursor c = db.rawQuery("SELECT * FROM workout", null);

        while (c.moveToNext()) {

            int idx = c.getInt(0);
            String date = c.getString(1);
            String name = c.getString(2);
            String kg = c.getString(3);
            String sets = c.getString(4);
            String reps = c.getString(5);


            dataList.add(new WorkoutLog(idx, date, name, kg, sets, reps));

        }
        c.close();
        db.close();

    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateClicked(int position) {
        Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onKgClicked(int position) {
        Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRepsClicked(int position) {
        Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSetsClicked(int position) {
        Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNameClicked(int position) {
        Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
    }
}