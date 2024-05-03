package com.example.saved_by_the_call.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.ui.form_elements.DatePickerFragment;

public class NewCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_call);

        final EditText datePickerNewCall = findViewById(R.id.datePickerNewCall);
        // final TimePicker timePicker = findViewById(R.id.timePickerNewCall);

        datePickerNewCall.setOnClickListener(view -> showDatePickerDialog(datePickerNewCall));
    }

    private void showDatePickerDialog(EditText datePickerNewCall) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((view, year, month, day) -> {
            datePickerNewCall.setText(formatDate(day, month + 1, year));
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private String formatDate(int day, int month, int year) {
        String formattedDay = (day <= 9) ? "0" + day : String.valueOf(day);
        String formattedMonth = (month <= 9) ? "0" + month : String.valueOf(month);
        return formattedDay + "/" + formattedMonth + "/" + year;
    }
}
