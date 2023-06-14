package com.ssh.workoutlogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutLogActivity extends AppCompatActivity {
    SQLiteDatabase db;
    MySQLiteOpenHelperWorkout helper;
    TextView tvName, tvDate;

    Button btnSave, btnCancel;
    String date, name, sets, kg, reps; // 입력값을 변수에 저장해서 insert처리할 변수

    String tvTodayText;

    NumberPicker kgPicker, setPicker, countPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_log);

        //db생성
        helper = new MySQLiteOpenHelperWorkout(
                WorkoutLogActivity.this, //현재 화면의 context
                "workout.db", //파일명
                null, //커서 팩토리
                1); //버전 번호


        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        tvDate = (TextView) findViewById(R.id.tvDate);
        tvName = (TextView) findViewById(R.id.tvName);

        kgPicker = findViewById(R.id.kgPicker);
        setPicker = findViewById(R.id.setPicker);
        countPicker = findViewById(R.id.countPicker);

        // kgPicker 초기값 설정
        kgPicker.setMinValue(0);
        kgPicker.setMaxValue(200);
        kgPicker.setValue(0);

        // 값을 변경할 때 리스너를 추가할 수도 있습니다.
        kgPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // 값이 변경될 때 호출됩니다.
                Log.d("NumberPicker", "Selected value: " + newVal);
            }
        });


// setPicker 초기값 설정
        setPicker.setMinValue(0);
        setPicker.setMaxValue(100);
        setPicker.setValue(0);

        // 값을 변경할 때 리스너를 추가할 수도 있습니다.
        setPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // 값이 변경될 때 호출됩니다.
                Log.d("NumberPicker", "Selected value: " + newVal);
            }
        });

// countPicker 초기값 설정
        countPicker.setMinValue(0);
        countPicker.setMaxValue(100);
        countPicker.setValue(0);

        // 값을 변경할 때 리스너를 추가할 수도 있습니다.
        countPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // 값이 변경될 때 호출됩니다.
                Log.d("NumberPicker", "Selected value: " + newVal);
            }
        });

        // kgPicker에서 선택한 값 가져오기
        int kgValue = kgPicker.getValue();
        // setsPicker에서 선택한 값 가져오기
        int setsValue = setPicker.getValue();
        // countPicker에서 선택한 값 가져오기
        int countValue = countPicker.getValue();

        sets = String.valueOf(setsValue);
        kg = String.valueOf(kgValue);
        reps = String.valueOf(countValue);

//        etKg = (EditText) findViewById(R.id.etKg);
//        etSets = (EditText) findViewById(R.id.etSets);
//        etReps = (EditText) findViewById(R.id.etReps);

        btnSave.setOnClickListener(mClick);
        btnCancel.setOnClickListener(mClick);

        // 다른 액티비티에서 인텐트로 전달된 데이터 추출
        tvTodayText = getIntent().getStringExtra("tvTodayText");
        tvName.setText(tvTodayText);

        Toolbar toolbar = findViewById(R.id.exerciseListToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setTitle("");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent list = new Intent(WorkoutLogActivity.this, RecyclerViewExercise.class);
                startActivity(list);
                finish();

            }
        });

    }


    View.OnClickListener mClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnSave:


                    //공백체크
//                    if (etKg.getText().toString().equals("")) {
//                        Toast.makeText(WorkoutLogActivity.this, "중량을 입력해주세요.", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                    //공백없이 입력이 되었을 경우 변수에 저장
//                    kg = etKg.getText().toString();
//
//                    if (etSets.getText().toString().equals("")) {
//                        Toast.makeText(WorkoutLogActivity.this, "세트 수를 입력해주세요.", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                    sets = etSets.getText().toString();
//
//                    if (etReps.getText().toString().equals("")) {
//                        Toast.makeText(WorkoutLogActivity.this, "반복횟수를 입력해주세요.", Toast.LENGTH_SHORT).show();
//                        break;
//                    }

                    //공백없이 입력이 되었을 경우 변수에 저장
//                    reps = etReps.getText().toString();


                    DatePicker datePicker = findViewById(R.id.date_picker);

                    // DatePicker에서 선택한 년, 월, 일을 가져옴
                    int year = datePicker.getYear();
                    int month = datePicker.getMonth() + 1;
                    int day = datePicker.getDayOfMonth();

                    date = year + "-" + month + "-" + day;

                    tvTodayText = getIntent().getStringExtra("tvTodayText");
                    name = tvTodayText;

                    // kgPicker에서 선택한 값 가져오기
                    int kgValue = kgPicker.getValue();
                    // setsPicker에서 선택한 값 가져오기
                    int setsValue = setPicker.getValue();
                    // countPicker에서 선택한 값 가져오기
                    int countValue = countPicker.getValue();

                    sets = String.valueOf(setsValue);
                    kg = String.valueOf(kgValue);
                    reps = String.valueOf(countValue);

                    // db에 삽입
                    insert(date, name, kg, sets, reps);


                    Intent main = new Intent(WorkoutLogActivity.this, WorkoutMainActivity.class);
                    startActivity(main);
                    finish();
                    break;

                //회원가입 후 가입정보 확인
//                    Intent intentJoinOk = new Intent(Ex24SQLiteJoinActivity.this, JoinOkActivity.class);
//                    intentJoinOk.putExtra("id", id);
//                    intentJoinOk.putExtra("pw", pw);
//                    intentJoinOk.putExtra("name", name);
//                    intentJoinOk.putExtra("hp", hp);
//                    intentJoinOk.putExtra("addr", addr);
//                    startActivity(intentJoinOk);
//                    finish();
//                    break;

//                        case R.id.btnCancel:
//                            Intent login = new Intent(JoinActivity.this, LoginActivity.class);
//                            startActivity(login);
//                            finish();
//                            break;
            }

        }


        public void insert(String date, String name, String sets, String kg, String reps) {
            SQLiteDatabase db = helper.getWritableDatabase(); //db 객체를 얻어옴, 쓰기 가능

            //값들을 컨트롤하기 위한 클래스 생성
            ContentValues values = new ContentValues();

            //db.insert의 매개변수인 values가 contentValues의 변수
            //데이터의 삽입은 put으로 함
            values.put("date", date);
            values.put("name", name);
            values.put("kg", kg);
            values.put("sets", sets);
            values.put("reps", reps);


            db.insert("workout", null, values); //테이블, 널컬럼핵, 테이터(널컬럽핵=디폴트)

            //tip : 마우스를 db.insert에 올리면 매개변수가 어떤 것이 와야 하는지 알 수 있다.
            db.close(); // db 종료

            //Toast.makeText(getApplicationContext(), date + "일에 운동:" + name + "추가", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "운동이 추가되었습니다.", Toast.LENGTH_LONG).show();

            Log.d("운동일지", "일자: " + date + " 일에 운동: " + name + " / " + kg + " kg" + " / " + sets + " sets" + " / " + reps + " reps " + " db저장");
            //RecyclerViewUser.selectUserList();
        }
    };


}