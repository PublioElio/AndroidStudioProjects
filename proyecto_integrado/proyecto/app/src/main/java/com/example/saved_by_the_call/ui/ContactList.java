package com.example.saved_by_the_call.ui;

import android.content.ContentResolver;
import android.database.Cursor;
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
import com.example.saved_by_the_call.cp.FakeCallsProvider;
import com.example.saved_by_the_call.ui.adapters.ContactsAdapter;
import com.example.saved_by_the_call.ui.top_menu.TopMenu;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {

    private ListView listViewContactList;
    private ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_list);

        final Button btnSearchContact = findViewById(R.id.btnSearchContact);
        final Toolbar toolbar = findViewById(R.id.toolbar_top_menu);
        listViewContactList= findViewById(R.id.listViewContactList);
        setSupportActionBar(toolbar);

        contactsAdapter = new ContactsAdapter(this, new ArrayList<>());
        listViewContactList.setAdapter(contactsAdapter);


        btnSearchContact.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                ArrayList<Contact> contacts = enterData();

                // Actualizar los datos del adaptador y notificarle del cambio
                contactsAdapter.clear();
                contactsAdapter.addAll(contacts);
                contactsAdapter.notifyDataSetChanged();

            }
        });

    }

    private ArrayList enterData() {
        ArrayList<Contact> contactList = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(
                FakeCallsProvider.CONTENT_URI_CONTACTS,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            int idIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_ID);
            int nameIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_NAME);
            int phoneIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_PHONE);
            int imgIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_IMG);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String phone = cursor.getString(phoneIndex);
                String img = cursor.getString(imgIndex);

                Contact contact = new Contact(id, name, phone, img);
                contactList.add(contact);
            }
            cursor.close();
        }
        return contactList;
    }


//    contacts.add(new Contact(1, "John Doe", "123456789", "test"));
//    contacts.add(new Contact(2, "Jane Doe", "987654321", "test"));



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