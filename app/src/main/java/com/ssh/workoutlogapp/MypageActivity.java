package com.ssh.workoutlogapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MypageActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private MyPage1 fragmentA;
    private Mypage2 fragmentB;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex29_fragment);

        Toolbar toolbar = findViewById(R.id.exerciseListToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setTitle("");

        fragmentManager = getSupportFragmentManager();

        fragmentA = new MyPage1();
        fragmentB = new Mypage2();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentA).commitAllowingStateLoss();

        findViewById(R.id.btn_fragmentA).setOnClickListener(mClick);
        findViewById(R.id.btn_fragmentB).setOnClickListener(mClick);
    }
    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            transaction = fragmentManager.beginTransaction();

            switch(v.getId())
            {
                case R.id.btn_fragmentA:
                    transaction.replace(R.id.frameLayout, fragmentA).commitAllowingStateLoss();
                    break;
                case R.id.btn_fragmentB:
                    transaction.replace(R.id.frameLayout, fragmentB).commitAllowingStateLoss();
                    
                    break;
            }

        }
    };


}