package com.ssh.workoutlogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

public class Advertisement extends AppCompatActivity {

    ImageButton imgBtn1;
    String webViewResult; // 웹뷰 액티비티에서 결과를 저장할 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        imgBtn1 = findViewById(R.id.imgBtn1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Video1.class);
                startActivity(intent); //광고 실행 후 바로 다른 클래스로 넘어감.
                finish();
            }
        }, 3000); //3초 후 인트로 실행


        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지를 누르면 해당 페이지로 이동한다
                String url = "https://hdex.co.kr/";
                Intent intent = new Intent(Advertisement.this, WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent); // startActivity를 사용하여 웹뷰 액티비티 실행
                finish();
            }
        });

    }

    // 웹뷰 액티비티에서 결과를 공유하는 메서드
    public void setWebViewResult(String result) {
        webViewResult = result;
        // 웹뷰 액티비티에서 전달된 데이터를 처리하는 로직을 추가
        // 예를 들어, 결과를 화면에 표시하거나, 데이터를 다른 작업에 활용할 수 있음
    }

    @Override
    protected void onPause() {
        super.onPause();
        //  finish();
    }
}