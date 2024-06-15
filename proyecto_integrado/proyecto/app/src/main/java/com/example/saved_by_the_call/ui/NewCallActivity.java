package com.example.saved_by_the_call.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.cp.FakeCallsProvider;
import com.example.saved_by_the_call.ui.form_elements.DatePickerFragment;
import com.example.saved_by_the_call.ui.form_elements.TimePickerFragment;
import com.example.saved_by_the_call.ui.toast_custom.ToastCustom;
import com.example.saved_by_the_call.ui.top_menu.TopMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        final Button btnAddNewCall = findViewById(R.id.btnAddNewCall);

        final Toolbar toolbar = findViewById(R.id.toolbar_top_menu);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TopMenu.disableToolbarTitle(toolbar);


        // launches DatePicker on a Dialog
        datePickerNewCall.setOnClickListener(view -> showDatePickerDialog(datePickerNewCall));

        // launches TimePicker on a Dialog
        timePickerNewCall.setOnClickListener(view -> showTimePickerDialog(timePickerNewCall));

        // Checks all fields and creates a new call in the database
        btnAddNewCall.setOnClickListener(view -> {
            final TextView txtViewCallFormInfo = findViewById(R.id.txtViewCallFormInfo);
            if (checkFields(callName, contactName, datePickerNewCall, timePickerNewCall)) {
                String name = String.valueOf(callName.getText());
                String contactNameText = String.valueOf(contactName.getText());
                String date = String.valueOf(datePickerNewCall.getText());
                String time = String.valueOf(timePickerNewCall.getText());
                if (createNewCall(name, contactNameText, date, time)) {
                    ToastCustom.showCustomToast(getApplicationContext(),
                            getString(R.string.toast_call_created_confirmation));
                    txtViewCallFormInfo.setTextColor(ContextCompat.getColor(view.getContext(),
                            R.color.black));
                    resetFields(callName, contactName, datePickerNewCall, timePickerNewCall);
                }
            } else
                txtViewCallFormInfo.setTextColor(ContextCompat.getColor(view.getContext(),
                        R.color.red));
        });
    }



    private boolean createNewCall(String name, String contactName, String date, String time) {
        boolean callCreated = false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        try {
            String dateTime = date + " " + time;
            Date callDate = sdf.parse(dateTime);
            if (callDate != null) {
                ContentValues values = new ContentValues();
                values.put(FakeCallsProvider.Calls.COL_NAME, name);
                values.put(FakeCallsProvider.Calls.COL_CONTACT, FakeCallsProvider.getContactByName(NewCallActivity.this, contactName).getId());
                values.put(FakeCallsProvider.Calls.COL_DATE, dateTime);

                ContentResolver cr = getContentResolver();
                Uri newContactUri = cr.insert(FakeCallsProvider.CONTENT_URI_CALLS, values);
                if (newContactUri != null) {
                    callCreated = true;
                }
            }
        } catch (ParseException e) {
            Log.e("NewCallActivity", String.valueOf(R.string.error_parsing_date_time), e);
        }
        return callCreated;
    }

    /**
     * This method resets all fields to their default values.
     *
     * @param callName          call name EditText field
     * @param contactName       contact name EditText field
     * @param datePickerNewCall date EditText field
     * @param timePickerNewCall time EditText field
     */
    private void resetFields(EditText callName, EditText contactName, EditText datePickerNewCall,
                             EditText timePickerNewCall) {
        callName.setText("");
        contactName.setText("");
        datePickerNewCall.setText(R.string.hint_choose_date);
        timePickerNewCall.setText(R.string.hint_choose_time);
    }

    /**
     * This method checks that all fields are correctly filled out, displaying/hiding error messages
     * in the corresponding fields.
     *
     * @param callName          call name EditText field
     * @param contactName       contact name EditText field
     * @param datePickerNewCall date EditText field
     * @param timePickerNewCall time EditText field
     * @return true if all fields are correct
     */
    private boolean checkFields(EditText callName, EditText contactName, EditText datePickerNewCall,
                                EditText timePickerNewCall) {

        // Warnings for each field
        final TextView fieldCallNameWarning = findViewById(R.id.edTxtCallNameWarning);
        final TextView fieldCallContactNameWarning = findViewById(R.id.edTxtCallContactNameWarning);
        final TextView fieldCallDateWarning = findViewById(R.id.edTxtCallDateWarning);
        final TextView fieldCallTimeWarning = findViewById(R.id.edTxtCallTimeWarning);
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
        } else {
            fieldCallTimeWarning.setVisibility(View.INVISIBLE);
        }

        return allFieldsCorrect;
    }

    /**
     * This method checks that the call name is at least 3 characters long and not empty.
     *
     * @param callName call name EditText field
     * @return true if the field is correct
     */
    private boolean checkFieldName(EditText callName) {
        return !callName.getText().toString().isEmpty() &&
                String.valueOf(callName.getText()).length() >= 3;
    }

    /**
     * This method checks that the contact name is at least 3 characters long and not empty.
     * Also, checks that the contact name exists in the database.
     *
     * @param contactName contact name EditText field
     * @return true if the field is correct
     */
    private boolean checkFieldContact(EditText contactName) {
        String name = String.valueOf(contactName.getText());
        boolean correctContact = !name.isEmpty() && name.length() >= 3;
        if (correctContact) {
            ContentResolver cr = getContentResolver();
            String[] projection = {FakeCallsProvider.Contacts.COL_NAME};
            String selection = FakeCallsProvider.Contacts.COL_NAME + " = ?";
            String[] selectionArgs = {name};
            Cursor cursor = cr.query(FakeCallsProvider.CONTENT_URI_CONTACTS, projection, selection,
                    selectionArgs, null);
            if (cursor != null) {
                correctContact = cursor.getCount() > 0;
                cursor.close();
            }
        }
        return correctContact;
    }

    /**
     * This method checks that the date is in the format dd/mm/yyyy.
     *
     * @param datePickerNewCall date EditText field
     * @return true if the field is correct
     */
    private boolean checkFieldDate(EditText datePickerNewCall) {
        return String.valueOf(datePickerNewCall.getText()).matches("\\d{2}/\\d{2}/\\d{4}");
    }

    /**
     * This method checks that the time is in the format hh:mm.
     *
     * @param timePickerNewCall time EditText field
     * @return true if the field is correct
     */
    private boolean checkFieldTime(EditText timePickerNewCall) {
        return String.valueOf(timePickerNewCall.getText()).matches("\\d{2}:\\d{2}");
    }

    /**
     * This method shows a TimePickerDialog.
     *
     * @param timePickerNewCall time EditText field
     */
    private void showTimePickerDialog(EditText timePickerNewCall) {
        TimePickerFragment newFragment = TimePickerFragment.newInstance((view, hour, min) ->
                timePickerNewCall.setText(formatTime(hour, min)));
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    /**
     * This method shows a DatePickerDialog.
     *
     * @param datePickerNewCall date EditText field
     */
    private void showDatePickerDialog(EditText datePickerNewCall) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((view, year, month, day) ->
                datePickerNewCall.setText(formatDate(day, month + 1, year)));
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * This method formats a number to a two-digit number.
     *
     * @param num number to format
     * @return formatted number
     */
    private String formatNumber(int num) {
        return (num <= 9) ? "0" + num : String.valueOf(num);
    }

    /**
     * This method formats a date to the format dd/mm/yyyy.
     *
     * @param day   day
     * @param month month
     * @param year  year
     * @return formatted date
     */
    private String formatDate(int day, int month, int year) {
        return formatNumber(day) + "/" + formatNumber(month) + "/" + year;
    }

    /**
     * This method formats a time to the format hh:mm.
     *
     * @param hour hour
     * @param min  minute
     * @return formatted time
     */
    private String formatTime(int hour, int min) {
        return formatNumber(hour) + ":" + formatNumber(min);
    }

    /**
     * This method creates the top menu.
     *
     * @param menu menu
     * @return true if the menu is created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        TopMenu.inflateMenu(inflater, menu, R.menu.top_menu);
        return true;
    }

    /**
     * This method handles the top menu options.
     *
     * @param item selected item
     * @return true if the option is handled
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return TopMenu.onOptionsItemSelected(this, item) ||
                super.onOptionsItemSelected(item);
    }
}
