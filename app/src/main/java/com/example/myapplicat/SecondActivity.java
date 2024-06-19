package com.example.myapplicat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private TextView textViewTimeDifference;
    private Button btnReturn, cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("");
        getSupportActionBar().hide();

        timePicker = findViewById(R.id.timePicker);
        textViewTimeDifference = findViewById(R.id.textViewTimeDifference);
        cal = findViewById(R.id.cal);
        btnReturn = findViewById(R.id.btnReturn);

        // "Return" 버튼 클릭 시 현재 액티비티 종료
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // "Calculate" 버튼 클릭 시 시간 차이 계산 메서드 호출
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTimeDifference();
            }
        });
    }

    // 시간 차이를 계산하고 결과를 TextView에 표시하는 메서드
    private void calculateTimeDifference() {
        // 현재 시간 가져오기
        Calendar currentTime = Calendar.getInstance();
        int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentTime.get(Calendar.MINUTE);

        // TimePicker에서 선택한 시간 가져오기
        int selectedHour = timePicker.getHour();
        int selectedMinute = timePicker.getMinute();

        // 차이 계산하기
        int hourDifference;
        int minuteDifference;

        if (selectedHour >= currentHour) {
            hourDifference = selectedHour - currentHour;
        } else {
            hourDifference = 24 - (currentHour - selectedHour);
        }

        if (selectedMinute >= currentMinute) {
            minuteDifference = selectedMinute - currentMinute;
        } else {
            minuteDifference = 60 - (currentMinute - selectedMinute);
            if (hourDifference > 0) {
                hourDifference--;
            }
        }

        // TextView에 결과 표시하기
        textViewTimeDifference.setText("잠자리 가는 시간: " + hourDifference + "시간 " + minuteDifference + "분");
    }
}
