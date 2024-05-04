package com.example.saved_by_the_call.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.ui.form_elements.DatePickerFragment;
import com.example.saved_by_the_call.ui.form_elements.TimePickerFragment;

public class NewCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_call);

        final EditText datePickerNewCall = findViewById(R.id.datePickerNewCall);
        final EditText timePickerNewCall = findViewById(R.id.timePickerNewCall);
        final Button addCallButton = findViewById(R.id.btnAddContact);
        final EditText

        datePickerNewCall.setOnClickListener(view -> showDatePickerDialog(datePickerNewCall));
        timePickerNewCall.setOnClickListener(view -> showTimePickerDialog(timePickerNewCall));
        addCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFields();
                checkFieldName();
                checkFieldContact();
                checkFieldDate();
                checkFieldTime();
            }
        });
    }

    private void showTimePickerDialog(EditText timePickerNewCall) {
        TimePickerFragment newFragment = TimePickerFragment.newInstance((view, hour, min) ->
                timePickerNewCall.setText(formatTime(hour, min)));
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private void showDatePickerDialog(EditText datePickerNewCall) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((view, year, month, day) ->
                datePickerNewCall.setText(formatDate(day, month + 1, year)));
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private String formatNumber(int num){
        return (num <= 9) ? "0" + num : String.valueOf(num);
    }

    private String formatDate(int day, int month, int year) {
        return formatNumber(day) + "/" + formatNumber(month) + "/" + year;
    }

    private String formatTime(int hour, int min) {
        return formatNumber(hour) + ":" + formatNumber(min);
    }
}
