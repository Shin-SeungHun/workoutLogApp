package com.ssh.workoutlogapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentLayout extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private FragmentId fragmentId;
    private FragmentPw fragmentPw;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Toolbar toolbar = findViewById(R.id.exerciseListToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setTitle("");

        fragmentManager = getSupportFragmentManager();

        fragmentId = new FragmentId();
        fragmentPw = new FragmentPw();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout1, fragmentId).commitAllowingStateLoss();

        findViewById(R.id.btn_fragmentId).setOnClickListener(mClick);
        findViewById(R.id.btn_fragmentPw).setOnClickListener(mClick);

    }
    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            transaction = fragmentManager.beginTransaction();

            switch(v.getId())
            {
                case R.id.btn_fragmentId:
                    transaction.replace(R.id.frameLayout1, fragmentId).commitAllowingStateLoss();
                    break;
                case R.id.btn_fragmentPw:

                    transaction.replace(R.id.frameLayout1, fragmentPw).commitAllowingStateLoss();
                    
                    break;
            }

        }
    };


}