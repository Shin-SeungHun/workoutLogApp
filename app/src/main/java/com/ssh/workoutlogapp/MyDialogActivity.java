package com.ssh.workoutlogapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ssh.workoutlogapp.R;

public class MyDialogActivity extends AppCompatActivity {

    TextView tvToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_my_dialog);

        tvToday = (TextView) findViewById(R.id.tvToday);
// 다른 액티비티에서 인텐트로 전달된 데이터 추출
        String tvTodayText = getIntent().getStringExtra("tvTodayText");
        tvToday.setText(tvTodayText);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

       // builder.setTitle("여러 줄 입력창 제목");



        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        input.setSingleLine(false);
        input.setMaxLines(5);
        input.setLines(5);
        builder.setView(input);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInput = input.getText().toString();
                // 입력값을 사용하는 코드 작성
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.show();
    }


}