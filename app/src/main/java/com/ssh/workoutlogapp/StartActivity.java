package com.ssh.workoutlogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        findViewById(R.id.btnLogin).setOnClickListener(mClickListener);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent); //광고 실행 후 바로 다른 클래스로 넘어감.
                finish();
            }
        }, 3000); //3초 후 인트로 실행

    }

//    Button.OnClickListener mClickListener = new Button.OnClickListener() {
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.btnLogin:
//                    Intent login =
//                            new Intent(StartActivity.this, LoginActivity.class);
//                    startActivity(login);
//                    break;
//
//            }
//        }
//    };
}