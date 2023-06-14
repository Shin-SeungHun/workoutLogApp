package com.ssh.workoutlogapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {


    EditText etId, etPw, etPwCheck, etName, etHp, etAddr;


    Button btnEdit, btnFind, btnCancel;
    //전달할 변수들
    String id, pw, name, hp, birth, gender, addr;


    static SQLiteDatabase db;
    static MySQLiteOpenHelperUser helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        //데이베이스 생성.
        helper = new MySQLiteOpenHelperUser(
                EditActivity.this, // 현재 화면의 context
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
        etHp = (EditText) findViewById(R.id.etHp);
        etName = (EditText) findViewById(R.id.etName);
        etAddr = (EditText) findViewById(R.id.etAddr);


        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnFind = (Button) findViewById(R.id.btnFind);
        btnCancel= (Button)findViewById(R.id.btnCancel);

//        gender.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                if (isChecked) {
//                    tvSex.setText("여자");
//                    selectSex = "여자";
//                } else {
//                    tvSex.setText("남자");
//                    selectSex = "남자";
//                }
//            }
//        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(EditActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findId = etId.getText().toString().trim();
                if (findId.equals("")) {
                    Toast.makeText(EditActivity.this, "아이디를 입력해주세오.", Toast.LENGTH_SHORT).show();
                    return;
                }
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
                    if (findId.equals(id)) {
                        etName.setText(name);
                        etHp.setText(hp);
                        //tvSex.setText(hp);
//                        if (gender.equals("남자")) {
//                            gender.setChecked(false);
//                        } else {
//                            gender.setChecked(true);
//                        }
                        etAddr.setText(addr);

                        // 생년월일 정보를 저장할 변수를 선언한다.
                        DatePicker date_picker = findViewById(R.id.date_picker);

// DatePicker를 초기화한다.
                        date_picker.updateDate(2000, 0, 1); // 초기 값으로 2000년 1월 1일을 설정한다.

// 가져온 생년월일 정보를 변수에 저장하고 DatePicker를 설정한다.
                        if (birth != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            Date birthDate = null;
                            try {
                                birthDate = sdf.parse(birth);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(birthDate);
                            int year = cal.get(Calendar.YEAR);
                            int month = cal.get(Calendar.MONTH);
                            int day = cal.get(Calendar.DAY_OF_MONTH);
                            date_picker.updateDate(year, month, day);
                        }

                        RadioGroup genderRadioGroup = findViewById(R.id.gender_radio_group);
                        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
                        // 성별 정보를 저장할 변수를 선언한다.
                        RadioButton male_radio_button = findViewById(R.id.male_radio_button);
                        RadioButton female_radio_button = findViewById(R.id.female_radio_button);
                        // RadioButton otherRadioButton = findViewById(R.id.otherRadioButton);

                        // 라디오 버튼을 초기화한다.
                        male_radio_button.setChecked(false);
                        female_radio_button.setChecked(false);
                        // otherRadioButton.setChecked(false);
                        if (gender.equals("남자")) {
                            male_radio_button.setChecked(true);
                        } else if (gender.equals("여자")) {
                            female_radio_button.setChecked(true);
                        }
                        break;
                    }

                }
                c.close();
                db.close();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etId.getText().toString().trim();
                String findId = etId.getText().toString().trim();
                String pw = etPw.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String hp = etHp.getText().toString().trim();
                String addr = etAddr.getText().toString().trim();
//                String findName = etName.getText().toString().trim();

                if (etId.getText().toString().equals("")) {
                    Toast.makeText(EditActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etPw.getText().toString().equals("")) {
                    Toast.makeText(EditActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etPwCheck.getText().toString().equals("")) {
                    Toast.makeText(EditActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etPwCheck.getText().toString().equals(etPw.getText().toString())) {

                } else if (!etPwCheck.getText().toString().equals(etPw.getText().toString())) {
                    Toast.makeText(EditActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etName.getText().toString().equals("")) {
                    Toast.makeText(EditActivity.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (etHp.getText().toString().equals("")) {
                    Toast.makeText(EditActivity.this, "연락처를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (etAddr.getText().toString().equals("")) {
                    Toast.makeText(EditActivity.this, "주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioGroup genderRadioGroup = findViewById(R.id.gender_radio_group);
                int selectedId = genderRadioGroup.getCheckedRadioButtonId();

                if (selectedId == R.id.male_radio_button) {
                    gender = "남자";

                } else if (selectedId == R.id.female_radio_button) {
                    gender = "여자";
                }

                DatePicker datePicker = findViewById(R.id.date_picker);

                // DatePicker에서 선택한 년, 월, 일을 가져옴
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();

                birth = year + "-" + month + "-" + day;

                //리스트뷰 어댑터에 처리하는부분...
                for (int i = 0; i < RecyclerViewUser.adapter.getItemCount(); i++) {
                    //Log.d("아이템값체크","아아템:"+Ex25FriendsListActivity.adapter.items.get(i).getName());
                    if (findId.equals(RecyclerViewUser.adapter.user.get(i).getId())) {

                        //값자리 초기화
                        RecyclerViewUser.adapter.user.get(i).setId(id);
                        RecyclerViewUser.adapter.user.get(i).setPw(pw);
                        RecyclerViewUser.adapter.user.get(i).setName(name);
                        RecyclerViewUser.adapter.user.get(i).setHp(hp);
                        RecyclerViewUser.adapter.user.get(i).setBirth(birth);
                        RecyclerViewUser.adapter.user.get(i).setGender(gender);
                        RecyclerViewUser.adapter.user.get(i).setAddr(addr);

                        if ("남자".equals(RecyclerViewUser.adapter.user.get(i).getGender())) {
                            RecyclerViewUser.adapter.user.get(i).setGender(gender);
                        } else {
                            RecyclerViewUser.adapter.user.get(i).setGender(gender);
                        }

                        //디비에 수정요청
                        update(id, pw, name, hp, birth, gender, addr);
                    }
                }

                //값자리 초기화
                etName.setText("");
                etHp.setText("");
                etAddr.setText("");
                Log.d("회원정보수정", "아이디: " + id + " / " + "비밀번호: " + pw + " / " + "이름: " + name + " / " + "연락처: " + hp + " / " + "생년월일: " + birth + "/" + "성별: " + gender + " / " + "주소: " + addr + " db저장");

                Toast.makeText(EditActivity.this, "회원정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                //메인에 있는 리스트뷰에 변화가 생겨서 갱신.
                RecyclerViewUser.adapter.notifyDataSetChanged();

                Intent main = new Intent(EditActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });
    }

    // update
    public void update(String id, String pw, String name, String hp, String birth, String
            gender, String addr) {
        db = helper.getWritableDatabase(); //db 객체를 얻어온다. 쓰기가능

        ContentValues values = new ContentValues();

        values.put("pw", pw);
        values.put("name", name);
        values.put("hp", hp);
        values.put("birth", birth);
        values.put("gender", gender);
        values.put("addr", addr);

        db.update("user", values, "id='" + id + "'", null);
        db.close();
       // Log.d("회원정보수정", "아이디: " + id + " / " + "비밀번호: " + pw + " / " + "이름: " + name + " / " + "연락처: " + hp + " / " + "생년월일: " + birth + "/" + "성별: " + gender + " / " + "주소: " + addr + " db저장");

        Toast.makeText(getApplicationContext(), id + "의 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
        Log.d("회원정보수정", "아이디: " + id + " / " + "비밀번호: " + pw + " / " + "이름: " + name + " / " + "연락처: " + hp + " / " + "생년월일: " + birth + "/" + "성별: " + gender + " / " + "주소: " + addr + " db저장");

        //수정완료후 초기화
        etId.setText("");
        etPw.setText("");
        etPwCheck.setText("");
        etName.setText("");
        etHp.setText("");
        etAddr.setText("");

    }
}