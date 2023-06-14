package com.ssh.workoutlogapp;



import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MemoAddActivity extends AppCompatActivity {

    SQLiteDatabase db;
    MySQLiteOpenHelperMemo helper;

    String date, content;
    TextView tvToday;
    EditText etMemo;
    Button btnCancel, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_add);

        //db생성
        helper = new MySQLiteOpenHelperMemo(
                MemoAddActivity.this, //현재 화면의 context
                "memo.db", //파일명
                null, //커서 팩토리
                1); //버전 번호

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);

        tvToday = (TextView) findViewById(R.id.tvToday);
        etMemo = (EditText) findViewById(R.id.etMemo);
// 다른 액티비티에서 인텐트로 전달된 데이터 추출
        String tvTodayText = getIntent().getStringExtra("tvTodayText");
        tvToday.setText(tvTodayText);

        //날짜변환
//        DateFormat formatter = new SimpleDateFormat(("yyyy-MM-dd"));
//        Date date = new Date(calendarView.getDate());
//        tvToday.setText(formatter.format(date));
//
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                String day;
//                day = year + "-" + (month + 1) + "-" + dayOfMonth;
//                tvToday.setText(day);
//            }
//        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MemoAddActivity.this, WorkoutMainActivity.class);
                startActivity(login);
                finish();

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //공백체크
                if (etMemo.getText().toString().equals("")) {
                    Toast.makeText(MemoAddActivity.this, "메모를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                date = tvTodayText;
                //공백없이 입력이 되었을 경우 변수에 저장
                content = etMemo.getText().toString();

                insert(date, content);
                Intent memo = new Intent(MemoAddActivity.this, WorkoutMainActivity.class);

                startActivity(memo);
                finish();

            }
        });


    }

    public void insert(String date, String content) {
        SQLiteDatabase db = helper.getWritableDatabase(); //db 객체를 얻어옴, 쓰기 가능

        //값들을 컨트롤하기 위한 클래스 생성
        ContentValues values = new ContentValues();

        //db.insert의 매개변수인 values가 contentValues의 변수
        //데이터의 삽입은 put으로 함
        values.put("date", date);
        values.put("content", content);


        db.insert("memo", null, values); //테이블, 널컬럼핵, 테이터(널컬럽핵=디폴트)

        //tip : 마우스를 db.insert에 올리면 매개변수가 어떤 것이 와야 하는지 알 수 있다.
        db.close(); // db 종료
        Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();

        Log.d("memo", "날짜" + date + "메모:" + content);
        // RecyclerViewMemo.selectMemoList();
    }

}