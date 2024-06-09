package com.example.saved_by_the_call.ui;

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

        callsAdapter = new CallsAdapter(this, new ArrayList<>());
        listViewCallList.setAdapter(callsAdapter);

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
        refreshListView();
    }

    /**
     * This method refreshes the list view.
     */
    private void refreshListView() {
        callsAdapter.clear();
        callsAdapter.notifyDataSetChanged();
    }
}
