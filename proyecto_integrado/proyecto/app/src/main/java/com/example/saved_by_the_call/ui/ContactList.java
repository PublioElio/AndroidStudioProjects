package com.example.saved_by_the_call.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.cp.Contact;
import com.example.saved_by_the_call.ui.adapters.ContactsAdapter;
import com.example.saved_by_the_call.ui.top_menu.TopMenu;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_list);

        final Button btnSearchContact = findViewById(R.id.btnSearchContact);
        final Toolbar toolbar = findViewById(R.id.toolbar_top_menu);
        setSupportActionBar(toolbar);

        final ListView listViewContactList = findViewById(R.id.listViewContactList);
        ArrayList<Contact> contactData = new ArrayList<>();
        enterData(contactData);

        final ContactsAdapter contactsAdapter = new ContactsAdapter(this, contactData);
        listViewContactList.setAdapter(contactsAdapter);

        btnSearchContact.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

            enterData(contactData);

            }
        });

    }

    private static void enterData(ArrayList<Contact> contactData) {
        contactData.add(new Contact(1,"John Doe", "123456789", "test"));
        contactData.add(new Contact(2, "Jane Doe", "987654321", "test"));
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