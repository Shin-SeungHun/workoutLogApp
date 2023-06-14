package com.ssh.workoutlogapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Mypage2 extends Fragment {

    SharedPreferences pref;          // 프리퍼런스
    SharedPreferences.Editor editor; // 에디터

    TextView tvGrade;

    float bench = 0, dead = 0, squat = 0;

    String gender, grade;

    EditText etBench, etDead, etSquat;

    Button btnSave, btnCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ex29_fragment_b, container, false);

        // 1. Shared Preference 초기화
        // pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor = pref.edit();

        bench = pref.getFloat("bench", 0);   // int 불러오기 (저장해둔 값 없으면 초기값인 0으로 불러옴)
        dead = pref.getFloat("dead", 0);   // int 불러오기 (저장해둔 값 없으면 초기값인 0으로 불러옴)
        squat = pref.getFloat("squat", 0);   // int 불러오기 (저장해둔 값 없으면 초기값인 0으로 불러옴)
        grade = pref.getString("grade", "");
        etBench = view.findViewById(R.id.etBench);
        etDead = view.findViewById(R.id.etDead);
        etSquat = view.findViewById(R.id.etSquat);
        tvGrade = view.findViewById(R.id.tvGrade);

        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancel);

        // 4. 앱을 새로 켜면 이전에 저장해둔 값이 표시됨
        etBench.setText(String.valueOf(bench));
        etDead.setText(String.valueOf(dead));
        etSquat.setText(String.valueOf(squat));
        tvGrade.setText(String.valueOf(grade));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bench = Float.parseFloat(etBench.getText().toString());
                dead = Float.parseFloat(etDead.getText().toString());
                squat = Float.parseFloat(etSquat.getText().toString());


                float total = bench + dead + squat;

                if (total <= 100) {
                    tvGrade.setText("등급: 초보, 삼대: "+total);
                } else if (total >= 300 && total < 400) {
                    tvGrade.setText("등급: 중급, 삼대: "+total);
                } else if (total >= 400 && total < 500) {
                    tvGrade.setText("등급: 중상급, 삼대: "+total);

                } else if (total >= 500) {
                    tvGrade.setText("등급: 고급, 삼대: "+total);
                }

                grade = tvGrade.getText().toString();
                editor.putFloat("dead", dead);
                editor.putFloat("bench", bench);
                editor.putFloat("squat", squat);
                editor.putString("gender", gender);
                editor.putString("grade", grade);

                editor.commit(); // 저장
                Toast.makeText(getActivity(), "저장되었습니다.", Toast.LENGTH_SHORT).show();


            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getActivity(), MainActivity.class);
                startActivity(main);

            }
        });

        return view;
    }
}