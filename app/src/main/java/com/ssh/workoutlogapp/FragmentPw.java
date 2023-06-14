package com.ssh.workoutlogapp;


import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentPw extends Fragment {


     SQLiteDatabase db;
     MySQLiteOpenHelperUser helper;





    TextView tvPw;
    EditText etId, etHp;


    Button btnOk, btnCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_pw, container, false);





        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {







            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login = new Intent(getActivity(), LoginActivity.class);
                startActivity(login);

            }
        });

        return view;
    }
}