package com.example.saved_by_the_call;

import android.os.Bundle;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class NewCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_call);


        TimePicker timePicker = findViewById(R.id.timePickerNewCall);

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

    }
}
