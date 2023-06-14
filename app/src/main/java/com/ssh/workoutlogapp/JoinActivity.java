package com.ssh.workoutlogapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    SQLiteDatabase db;
    MySQLiteOpenHelperUser helper;

    String id, pw, name, hp, addr, birth, gender; // 입력값을 변수에 저장해서 insert처리할 변수

    Button btnJoin, btnCancel, btnIdCheck, btnHpCheck;

    EditText etId, etPw, etPwCheck, etHp, etName, etAddr;

    // DatePicker date_picker;

    boolean isIdCheck = false;

    boolean isHpCheck = false;

    String TAG = "회원가입";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //db생성
        helper = new MySQLiteOpenHelperUser(
                JoinActivity.this, //현재 화면의 context
                "user.db", //파일명
                null, //커서 팩토리
                1); //버전 번호

        Toolbar toolbar = findViewById(R.id.exerciseListToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setTitle("");

        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnIdCheck = (Button) findViewById(R.id.btnIdCheck);
        btnHpCheck = (Button) findViewById(R.id.btnHpCheck);

        etId = (EditText) findViewById(R.id.etId);
        etPw = (EditText) findViewById(R.id.etPw);
        etPwCheck = (EditText) findViewById(R.id.etPwCheck);
        etHp = (EditText) findViewById(R.id.etHp);
        etName = (EditText) findViewById(R.id.etName);
        etAddr = (EditText) findViewById(R.id.etAddr);
        btnJoin.setOnClickListener(mClick);
        btnCancel.setOnClickListener(mClick);
        btnIdCheck.setOnClickListener(mClick);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
                //  showPasswordDialog();
            }
        });


        btnIdCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findId = etId.getText().toString().trim();

                if (findId.equals("")) {
                    Toast.makeText(JoinActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbIdCheck(findId);


            }
        });

        btnHpCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findHp = etHp.getText().toString().trim();

                if (findHp.equals("")) {
                    Toast.makeText(JoinActivity.this, "연락처를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHpCheck(findHp);


            }
        });


    }

    public void showPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
        builder.setTitle("비밀번호 입력");
        final EditText passwordEditText = new EditText(JoinActivity.this);
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(passwordEditText);

        builder.setPositiveButton("확인", (dialog, which) -> {
            String password = passwordEditText.getText().toString();
            if (checkPassword(password, password)) {
                // 비밀번호가 일치하는 경우, 회원 정보 수정 화면을 띄웁니다.
                Intent intent = new Intent(JoinActivity.this, EditActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(JoinActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("취소", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public boolean checkPassword(String inputPassword, String originalPassword) {
        if (inputPassword.equals(originalPassword)) {
            return true;
        } else {
            return false;
        }
    }


//    public boolean dbIdCheck(String inputId) {
//        db = helper.getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM user", null);
//
//        try {
//            boolean isIdDuplicate = false;
//
//            while (c.moveToNext()) {
//                String id = c.getString(1);
//
//                if (inputId.equals(id)) {
//                    Toast.makeText(this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
//                    isIdDuplicate = true;
//                    break;
//                }
//            }
//
//            if (!isIdDuplicate) {
//                Toast.makeText(this, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
//            }
//
//            btnIdCheck.setEnabled(!isIdDuplicate);
//            etId.setEnabled(!isIdDuplicate);
//
//            return isIdDuplicate;
//        } finally {
//            c.close();
//            db.close();
//        }
//    }

    public boolean dbIdCheck(String inputId) {

        db = helper.getReadableDatabase(); //db객체를 가져온다 읽기전용
        Cursor c = db.rawQuery("SELECT * FROM user", null);
        boolean isIdCheck = true; // isIdCheck 변수를 초기화하여 사용

        while (c.moveToNext()) {
            String id = c.getString(1); // id만 가져옴

            if (inputId.equals(id)) {
                Toast.makeText(this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                c.close();
                db.close();
                return true;
            }
        }

        Toast.makeText(this, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
        btnIdCheck.setEnabled(!isIdCheck);
        etId.setEnabled(!isIdCheck);
        c.close();
        db.close();
        return false;
    }

    public boolean dbHpCheck(String inputHp) {

        db = helper.getReadableDatabase(); //db객체를 가져온다 읽기전용
        Cursor c = db.rawQuery("SELECT * FROM user", null);
        boolean isHpCheck = true; // isHpCheck 변수를 초기화하여 사용

        while (c.moveToNext()) {
            String hp = c.getString(4); // hp만 가져옴

            if (inputHp.equals(hp)) {
                Toast.makeText(this, "중복된 연락처입니다.", Toast.LENGTH_SHORT).show();
                c.close();
                db.close();
                return true;
            }
        }

        Toast.makeText(this, "등록가능한 연락처입니다.", Toast.LENGTH_SHORT).show();
        btnHpCheck.setEnabled(!isHpCheck);
        etHp.setEnabled(!isHpCheck);
        c.close();
        db.close();
        return false;
    }

//    public boolean dbIdCheck(String inputId) {
//        db = helper.getReadableDatabase(); //db객체를 가져온다 읽기전용
//        Cursor c = db.rawQuery("SELECT * FROM user", null);
//        isIdCheck = true;
//        while (c.moveToNext()) {
//            int idx = c.getInt(0);
//            String id = c.getString(1);
//            String pw = c.getString(2);
//            String name = c.getString(3);
//            String hp = c.getString(4);
//            String birth = c.getString(5);
//            String gender = c.getString(6);
//            String addr = c.getString(7);
//
//            if (inputId.equals(id)) {
//                Toast.makeText(this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
//                return true;
//
//            } else {
//                Toast.makeText(this, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
//                btnIdCheck.setEnabled(false);
//                etId.setEnabled(false);
//                return false;
//            }
//        }
//        c.close();
//        db.close();
//
//        return true;
//    }

    View.OnClickListener mClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnJoin:


                    //공백체크
                    if (etId.getText().toString().equals("")) {
                        Toast.makeText(JoinActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //공백없이 입력이 되었을 경우 변수에 저장
                    id = etId.getText().toString();

                    if (etPw.getText().toString().equals("")) {
                        Toast.makeText(JoinActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if (etPwCheck.getText().toString().equals("")) {
                        Toast.makeText(JoinActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if (etPwCheck.getText().toString().equals(etPw.getText().toString())) {
                        //공백없이 입력이 되었을 경우 변수에 저장
                        pw = etPw.getText().toString();
                    } else if (!etPwCheck.getText().toString().equals(etPw.getText().toString())) {
                        Toast.makeText(JoinActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if (etName.getText().toString().equals("")) {
                        Toast.makeText(JoinActivity.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    //공백없이 입력이 되었을 경우 변수에 저장
                    name = etName.getText().toString();

                    if (etHp.getText().toString().equals("")) {
                        Toast.makeText(JoinActivity.this, "연락처를 입력하세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    //공백없이 입력잘되었을경우 변수에 저장
                    hp = etHp.getText().toString();

                    if (etAddr.getText().toString().equals("")) {
                        Toast.makeText(JoinActivity.this, "주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    //공백없이 입력이 되었을 경우 변수에 저장
                    addr = etAddr.getText().toString();


                    if (isIdCheck) {
                        // 중복확인 안 함
                        Toast.makeText(getApplicationContext(), "아이디를 중복확인 해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } else {

                    }

                    if (isHpCheck) {
                        // 중복확인 안 함
                        Toast.makeText(getApplicationContext(), "연락처를 중복확인 해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } else {

                    }

                    RadioGroup genderRadioGroup = findViewById(R.id.gender_radio_group);
                    int selectedId = genderRadioGroup.getCheckedRadioButtonId();

                    if (selectedId == -1) {
                        Toast.makeText(getApplicationContext(), "성별을 선택하세요", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
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


                        //db에 삽입
                        insert(id, pw, name, hp, birth, gender, addr);
                        Intent join = new Intent(JoinActivity.this, LoginActivity.class);
                        startActivity(join);
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
        }


        //db 메소드 처리
        public void insert(String id, String pw, String name, String hp, String birth, String gender, String addr) {
            SQLiteDatabase db = helper.getWritableDatabase(); //db 객체를 얻어옴, 쓰기 가능

            //값들을 컨트롤하기 위한 클래스 생성
            ContentValues values = new ContentValues();

            //db.insert의 매개변수인 values가 contentValues의 변수
            //데이터의 삽입은 put으로 함
            values.put("id", id);
            values.put("pw", pw);
            values.put("name", name);
            values.put("hp", hp);
            values.put("birth", birth);
            values.put("gender", gender);
            values.put("addr", addr);

            db.insert("user", null, values); //테이블, 널컬럼핵, 테이터(널컬럽핵=디폴트)

            //tip : 마우스를 db.insert에 올리면 매개변수가 어떤 것이 와야 하는지 알 수 있다.
            db.close(); // db 종료
            Toast.makeText(getApplicationContext(), id + " 로 회원가입 완료했습니다.", Toast.LENGTH_LONG).show();

            Log.d(TAG, "아이디: " + id + " / " + "비밀번호: " + pw + " / " + "이름: " + name + " / " + "연락처: " + hp + " / " + "생년월일: " + birth + "/" + "성별: " + gender + " / " + "주소: " + addr + " db저장");
            //RecyclerViewUser.selectUserList();
        }
    };


}
