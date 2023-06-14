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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyPage1 extends Fragment {

    SharedPreferences pref;          // 프리퍼런스
    SharedPreferences.Editor editor; // 에디터

    TextView tvState;

    int age;
    float height = 0, weight = 0, bodyFat = 0;
    String gender, state;
    EditText etAge, etHeight, etWeight, etBodyFat;
    Button btnSave, btnCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ex29_fragment_a, container, false);


// 1. Shared Preference 초기화
       // pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor = pref.edit();

        // 2. 저장해둔 값 불러오기 ("식별값", 초기값) -> 식별값과 초기값은 직접 원하는 이름과 값으로 작성.
        age = pref.getInt("age", 0);
        height = pref.getFloat("height", 0);   // int 불러오기 (저장해둔 값 없으면 초기값인 0으로 불러옴)
        weight = pref.getFloat("weight", 0);   // int 불러오기 (저장해둔 값 없으면 초기값인 0으로 불러옴)
        bodyFat = pref.getFloat("bodyFat", 0); // float 불러오기
        gender = pref.getString("gender", "");
        state = pref.getString("state", "");

        // 3. 레이아웃 변수 초기화
        tvState = view.findViewById(R.id.tvState);
        etAge = view.findViewById(R.id.etAge);
        etHeight = view.findViewById(R.id.etHeight);
        etWeight = view.findViewById(R.id.etWeight);
        etBodyFat = view.findViewById(R.id.etBodyFat);

        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancel);

        // 4. 앱을 새로 켜면 이전에 저장해둔 값이 표시됨
        etAge.setText(String.valueOf(age));
        etHeight.setText(String.valueOf(height));
        etWeight.setText(String.valueOf(weight));
        etBodyFat.setText(String.valueOf(bodyFat));
        tvState.setText(String.valueOf(state));



        RadioGroup genderRadioGroup = view.findViewById(R.id.gender_radio_group);
        genderRadioGroup.check(R.id.male_radio_button);  // 기본값으로 "남자" 선택
        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
        selectedId = genderRadioGroup.getCheckedRadioButtonId();  // 선택된 라디오 버튼 id 가져오기
        if (selectedId == -1) {
            // 선택된 라디오 버튼이 없을 때 기본값으로 설정
            genderRadioGroup.check(R.id.male_radio_button);
            selectedId = R.id.male_radio_button;  // 선택된 라디오 버튼 id 다시 가져오기
        }
        if (selectedId == R.id.male_radio_button) {
            gender = "male";
        } else if (selectedId == R.id.female_radio_button) {
            gender = "woman";
        }

        String savedGender = gender;  // 이전에 저장한 성별 값을 가져옴
        if (savedGender.equals("female")) {
            genderRadioGroup.check(R.id.female_radio_button); // 이전에 저장한 값이 "여자"일 경우 "여자" 선택
        } else {
            genderRadioGroup.check(R.id.male_radio_button);   // 기본값으로 "남자" 선택
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                age = Integer.parseInt(etAge.getText().toString());
                height = Float.parseFloat(etHeight.getText().toString());
                weight = Float.parseFloat(etWeight.getText().toString());
                bodyFat = Float.parseFloat(etBodyFat.getText().toString());



                if (gender.equals("male")) { //남자일 경우
                    if (bodyFat <= 7.0) {
                        tvState.setText("일반적으로 피트니스 모델들이 유지하는 체지방률");

                    } else if (bodyFat > 7.0 && bodyFat <= 12.0) {
                        tvState.setText("모델들이 유지하는 체지방률");

                    } else if (bodyFat > 12.0 && bodyFat <= 16.0) {
                        tvState.setText("보통 일반인들이 도전하는 체지방률");

                    } else if (bodyFat > 16.0 && bodyFat <= 24.0) {
                        tvState.setText("남자 평균 체지방률로 건장한 느낌의 몸매");

                    } else if (bodyFat > 24.0 && bodyFat <= 30.0) {
                        tvState.setText("배가 나오고 몸이 무거워지는 통통해 보이는 체지방률");

                    } else {
                        tvState.setText("뚱뚱해 보이는 몸매로 건강상에도 문제가 생기는 체지방률");

                    }
                } else if (gender.equals("woman")) { //여자일 경우
                    if (bodyFat <= 11.0) {
                        tvState.setText("여성 바디빌더의 체지방률로 단순 운동만으로 나올 수 없는 근육질 몸매");

                    } else if (bodyFat > 11.0 && bodyFat <= 15.0) {
                        tvState.setText("여자 피트니스 모델들의 체지방률로 단순 운동만으로는 도달하기 어려운 정도");

                    } else if (bodyFat > 15.0 && bodyFat <= 19.0) {
                        tvState.setText("연예인와 모델들이 유지하는 체지방률로 마른 느낌이 드는 몸매");

                    } else if (bodyFat > 19.0 && bodyFat <= 24.0) {
                        tvState.setText("일반인들이 목표로 가질만한 체지방률로 실제로 봤을 때 가장 예쁜 몸매");

                    } else if (bodyFat > 24.0 && bodyFat <= 30.0) {
                        tvState.setText("건강상으로는 가장 이상적인 체지방률이나 접히는 부분이 생기고 배가 나오는 몸매");

                    } else {
                        tvState.setText("보이는 것과 건강상에 모두 문제가 있는 몸매");

                    }
                } else { //성별이 입력되지 않은 경우

                    return;
                }

                state = tvState.getText().toString();
                editor.putInt("age", age);
                editor.putFloat("height", height);
                editor.putFloat("weight", weight);
                editor.putFloat("bodyFat", bodyFat);

                editor.putString("gender", gender);
                editor.putString("state", state);

                editor.commit(); // 저장
                 Toast.makeText(getActivity(), "저장되었습니다.", Toast.LENGTH_SHORT).show();


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getActivity(), MainActivity.class);
                startActivity(main);
                    getActivity().finish();
            }
        });


        return view;
    }


}