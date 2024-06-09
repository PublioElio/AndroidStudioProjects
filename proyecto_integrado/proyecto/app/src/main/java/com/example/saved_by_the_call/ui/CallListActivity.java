package com.example.saved_by_the_call.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.cp.Call;
import com.example.saved_by_the_call.cp.FakeCallsProvider;
import com.example.saved_by_the_call.ui.adapters.CallsAdapter;
import com.example.saved_by_the_call.ui.top_menu.TopMenu;

import java.util.ArrayList;

public class CallListActivity extends AppCompatActivity {
    private CallsAdapter callsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_call_list);

        final Toolbar toolbar = findViewById(R.id.toolbar_top_menu);
        final ListView listViewCallList = findViewById(R.id.listViewCallList);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TopMenu.disableToolbarTitle(toolbar);


        ArrayList<Call> calls = enterData();
        callsAdapter = new CallsAdapter(this, calls);
        listViewCallList.setAdapter(callsAdapter);
    }

    /**
     * This method fills the list of calls.
     *
     * @return list of calls
     */
    private ArrayList<Call> enterData() {
        ArrayList<Call> callsList = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(
                FakeCallsProvider.CONTENT_URI_CALLS,
                null,
                null,
                null,
                null
        );
        if (cursor != null) {
            addCallsToCallsList(cursor, callsList);
            cursor.close();
        }
        return callsList;
    }

    /**
     * This method adds the calls to the contact list.
     *
     * @param cursor    cursor
     * @param callsList list of calls
     */
    private static void addCallsToCallsList(Cursor cursor, ArrayList<Call> callsList) {
        int idIndex = cursor.getColumnIndex(FakeCallsProvider.Calls.COL_ID);
        int nameIndex = cursor.getColumnIndex(FakeCallsProvider.Calls.COL_NAME);
        int contactIdIndex = cursor.getColumnIndex(FakeCallsProvider.Calls.COL_CONTACT);
        int dateIndex = cursor.getColumnIndex(FakeCallsProvider.Calls.COL_DATE);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            long contactId = cursor.getLong(contactIdIndex);
            String date = cursor.getString(dateIndex);

            Call call = new Call(id, name, contactId, date);
            callsList.add(call);
        }
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

    /**
     * This method refreshes the list view.
     */
    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Call> calls = enterData();
        callsAdapter.clear();
        callsAdapter.addAll(calls);
        callsAdapter.notifyDataSetChanged();
    }

}
