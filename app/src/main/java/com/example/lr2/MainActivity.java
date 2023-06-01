package com.example.lr2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText editTextStartDate, editTextStartHour, editTextStartMinute;
    private EditText editTextEndDate, editTextEndHour, editTextEndMinute;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextStartDate = findViewById(R.id.editTextStartDate);
        editTextStartHour = findViewById(R.id.editTextStartHour);
        editTextStartMinute = findViewById(R.id.editTextStartMinute);
        editTextEndDate = findViewById(R.id.editTextEndDate);
        editTextEndHour = findViewById(R.id.editTextEndHour);
        editTextEndMinute = findViewById(R.id.editTextEndMinute);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTimeDifference();
            }
        });
    }

    private void calculateTimeDifference() {
        String startDateString = editTextStartDate.getText().toString();
        String startHourString = editTextStartHour.getText().toString();
        String startMinuteString = editTextStartMinute.getText().toString();
        String endDateString = editTextEndDate.getText().toString();
        String endHourString = editTextEndHour.getText().toString();
        String endMinuteString = editTextEndMinute.getText().toString();

        if (TextUtils.isEmpty(startDateString) || TextUtils.isEmpty(startHourString) || TextUtils.isEmpty(startMinuteString) ||
                TextUtils.isEmpty(endDateString) || TextUtils.isEmpty(endHourString) || TextUtils.isEmpty(endMinuteString)) {
            textViewResult.setText("Введите все поля.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date startDate = dateFormat.parse(startDateString + " " + startHourString + ":" + startMinuteString);
            Date endDate = dateFormat.parse(endDateString + " " + endHourString + ":" + endMinuteString);

            long diffInMilliseconds = endDate.getTime() - startDate.getTime();
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds);
            long hours = diffInMinutes / 60;
            long minutes = diffInMinutes % 60;

            String result = "Разница: " + hours + " ч " + minutes + " мин";
            textViewResult.setText(result);
        } catch (ParseException e) {
            e.printStackTrace();
            textViewResult.setText("Неверный формат даты или времени.");
        }
    }
}