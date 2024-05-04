package com.example.saved_by_the_call.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.ui.form_elements.DatePickerFragment;
import com.example.saved_by_the_call.ui.form_elements.TimePickerFragment;

public class NewCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_call);

        // Activity elements
        final EditText callName = findViewById(R.id.edTxtCallName);
        final EditText contactName = findViewById(R.id.edTxtCallContactName);
        final EditText datePickerNewCall = findViewById(R.id.datePickerNewCall);
        final EditText timePickerNewCall = findViewById(R.id.timePickerNewCall);
        final Button addCallButton = findViewById(R.id.btnAddContact);

        // launches DatePicker on a Dialog
        datePickerNewCall.setOnClickListener(view -> showDatePickerDialog(datePickerNewCall));

        // launches TimePicker on a Dialog
        timePickerNewCall.setOnClickListener(view -> showTimePickerDialog(timePickerNewCall));

        addCallButton.setOnClickListener(view -> {
            final TextView txtViewCallFormInfo = findViewById(R.id.txtViewCallFormInfo);
            if (checkFields(callName, contactName, datePickerNewCall, timePickerNewCall)) {
                Toast.makeText(getApplicationContext(), R.string.call_created_confirmation_msg,
                        Toast.LENGTH_SHORT).show();
                txtViewCallFormInfo.setTextColor(ContextCompat.getColor(view.getContext(),
                        R.color.black));
            } else
                txtViewCallFormInfo.setTextColor(ContextCompat.getColor(view.getContext(),
                        R.color.red));
        });
    }

    /**
     * This method checks that all fields are correctly filled out, displaying/hiding error messages
     * in the corresponding fields.
     * @param callName call name EditText field
     * @param contactName contact name EditText field
     * @param datePickerNewCall date EditText field
     * @param timePickerNewCall time EditText field
     * @return true if all fields are correct
     */
    private boolean checkFields(EditText callName, EditText contactName, EditText datePickerNewCall,
                                EditText timePickerNewCall) {

        TextView fieldCallNameWarning = findViewById(R.id.edTxCallNameWarning);
        TextView fieldCallContactNameWarning = findViewById(R.id.edTxCallContactNameWarning);
        TextView fieldCallDateWarning = findViewById(R.id.edTxCallDateWarning);
        TextView fieldCallTimeWarning = findViewById(R.id.edTxCallTimeWarning);
        boolean allFieldsCorrect = true;

        if (!checkFieldName(callName)) {
            fieldCallNameWarning.setVisibility(View.VISIBLE);
            allFieldsCorrect = false;
        } else {
            fieldCallNameWarning.setVisibility(View.INVISIBLE);
        }

        if (!checkFieldContact(contactName)) {
            fieldCallContactNameWarning.setVisibility(View.VISIBLE);
            allFieldsCorrect = false;
        } else {
            fieldCallContactNameWarning.setVisibility(View.INVISIBLE);
        }

        if (!checkFieldDate(datePickerNewCall)) {
            fieldCallDateWarning.setVisibility(View.VISIBLE);
            allFieldsCorrect = false;
        } else {
            fieldCallDateWarning.setVisibility(View.INVISIBLE);
        }

        if (!checkFieldTime(timePickerNewCall)) {
            fieldCallTimeWarning.setVisibility(View.VISIBLE);
            allFieldsCorrect = false;
        } else{
            fieldCallTimeWarning.setVisibility(View.INVISIBLE);
        }

        return allFieldsCorrect;
    }

    private boolean checkFieldName(EditText callName) {
        return String.valueOf(callName.getText()).length() >= 3;
    }

    // TODO: MODIFICAR ESTE MÉTODO PARA QUE COMPRUEBE LA BBDD BUSCANDO EL CONTACTO
    private boolean checkFieldContact(EditText contactName) {
        return String.valueOf(contactName.getText()).length() >= 3;
    }

    private boolean checkFieldDate(EditText datePickerNewCall) {
        return String.valueOf(datePickerNewCall.getText()).matches("\\d{2}/\\d{2}/\\d{4}");
    }

    private boolean checkFieldTime(EditText timePickerNewCall) {
        return String.valueOf(timePickerNewCall.getText()).matches("\\d{2}:\\d{2}");
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

    private String formatNumber(int num) {
        return (num <= 9) ? "0" + num : String.valueOf(num);
    }

    private String formatDate(int day, int month, int year) {
        return formatNumber(day) + "/" + formatNumber(month) + "/" + year;
    }

    private String formatTime(int hour, int min) {
        return formatNumber(hour) + ":" + formatNumber(min);
    }
}
