package com.ssh.workoutlogapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentId extends Fragment {

    static SQLiteDatabase db;
     static MySQLiteOpenHelperUser helper;



    EditText etHp;
    TextView tvId;


    Button btnOk, btnCancel, btnHpSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_id, container, false);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperUser(
                getActivity(), // 현재 화면의 context
                "user.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호


        btnOk = view.findViewById(R.id.btnOk);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnHpSearch = view.findViewById(R.id.btnHpSearch);




        btnHpSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findHp = etHp.getText().toString().trim();

                if (findHp.equals("")) {
                    Toast.makeText(getActivity(), "연락처를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }


                db = helper.getReadableDatabase();
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
                    if (findHp.equals(hp)) {
                        tvId.setText(id);

                    }
                }
                c.close();
                db.close();
            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getActivity(), LoginActivity.class);
                startActivity(login);
                getActivity().finish();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login = new Intent(getActivity(), LoginActivity.class);
                startActivity(login);
                getActivity().finish();
            }
        });


        return view;
    }


}