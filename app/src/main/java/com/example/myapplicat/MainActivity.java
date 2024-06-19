package com.example.myapplicat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTime;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable timeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        textViewTime = findViewById(R.id.textViewTime);

        // 현재 시간을 표시하는 메서드 호출
        updateTime();

        // 1초마다 현재 시간 업데이트
        timeRunnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000); // 다시 1초 후에 실행
            }
        };
        handler.postDelayed(timeRunnable, 1000);

        Button buttonSleepAnalysis = findViewById(R.id.buttonSleepAnalysis);
        buttonSleepAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        Button buttonSetAlarm = findViewById(R.id.buttonSetAlarm);
        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    // 현재 시간을 업데이트하는 메서드
    private void updateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.KOREA);
        String currentTime = sdf.format(calendar.getTime());
        textViewTime.setText(currentTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 액티비티가 종료될 때 핸들러의 메시지 큐에서 Runnable을 제거합니다.
        handler.removeCallbacks(timeRunnable);
    }
}
