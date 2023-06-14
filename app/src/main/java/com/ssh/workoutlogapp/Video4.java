package com.ssh.workoutlogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Objects;

public class Video4 extends AppCompatActivity {

    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video4);

        Toolbar toolbar = findViewById(R.id.video_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setTitle("");

//        Uri videoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.recovery);
//        video = (VideoView) findViewById(R.id.videoView);
//        video.setMediaController(new MediaController(this));
//        video.setVideoURI(videoUri);
    }
}