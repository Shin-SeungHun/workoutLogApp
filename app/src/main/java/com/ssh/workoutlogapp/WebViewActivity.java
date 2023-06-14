package com.ssh.workoutlogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ssh.workoutlogapp.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webview = (WebView) findViewById(R.id.webview);

        //웹뷰 옵션 설정하기. ==========================================
        webview.setWebViewClient(new WebViewClient());  // 새 창 띄우기 않기
        webview.setWebChromeClient(new WebChromeClient());

        webview.getSettings().setJavaScriptEnabled(true); // 자바스크립트 사용여부
        // 인텐트로 전달된 URL 가져오기
        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra("url");

            // 전달된 URL을 WebView에 로드
            if (url != null) {
                webview.loadUrl(url);
            }
        }

    }
}