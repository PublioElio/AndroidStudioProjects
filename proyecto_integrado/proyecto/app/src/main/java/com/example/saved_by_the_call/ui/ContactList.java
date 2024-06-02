package com.example.saved_by_the_call.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.cp.Contact;
import com.example.saved_by_the_call.cp.FakeCallsProvider;
import com.example.saved_by_the_call.ui.adapters.ContactsAdapter;
import com.example.saved_by_the_call.ui.top_menu.TopMenu;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {
    private ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_list);

        final Button btnSearchContact = findViewById(R.id.btnSearchContact);
        final Toolbar toolbar = findViewById(R.id.toolbar_top_menu);
        final EditText edTxtSearchContactName = findViewById(R.id.edTxtSearchContactName);
        final ListView listViewContactList = findViewById(R.id.listViewContactList);
        setSupportActionBar(toolbar);

        contactsAdapter = new ContactsAdapter(this, new ArrayList<>());
        listViewContactList.setAdapter(contactsAdapter);

        btnSearchContact.setOnClickListener(view -> {
            ArrayList<Contact> contacts = enterData(edTxtSearchContactName.getText().toString());
            contactsAdapter.clear();
            contactsAdapter.addAll(contacts);
            contactsAdapter.notifyDataSetChanged();

        });

    }

    /**
     * This method queries the contacts.
     *
     * @param name name of the contact
     * @return list of contacts
     */
    private ArrayList<Contact> enterData(String name) {
        ArrayList<Contact> contactList = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor;
        if (!name.isEmpty()) {
            cursor = notEmptyNameQuery(name, contentResolver);
        } else {
            cursor = emptyNameQuery(contentResolver);
        }
        if (cursor != null) {
            addContactsToContactList(cursor, contactList);
            cursor.close();
        }
        return contactList;
    }

    /**
     * This method adds the contacts to the contact list.
     *
     * @param cursor      cursor
     * @param contactList list of contacts
     */
    private static void addContactsToContactList(Cursor cursor, ArrayList<Contact> contactList) {
        int idIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_ID);
        int nameIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_NAME);
        int phoneIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_PHONE);
        int imgIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_IMG);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(idIndex);
            String nameContact = cursor.getString(nameIndex);
            String phone = cursor.getString(phoneIndex);
            String img = cursor.getString(imgIndex);

            Contact contact = new Contact(id, nameContact, phone, img);
            contactList.add(contact);
        }
    }

    /**
     * This method queries the contacts with empty name.
     *
     * @param contentResolver content resolver
     * @return cursor
     */
    @Nullable
    private static Cursor emptyNameQuery(ContentResolver contentResolver) {
        Cursor cursor;
        cursor = contentResolver.query(
                FakeCallsProvider.CONTENT_URI_CONTACTS,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    /**
     * This method queries the contacts with not empty name.
     *
     * @param name            name of the contact
     * @param contentResolver content resolver
     * @return cursor
     */
    @Nullable
    private static Cursor notEmptyNameQuery(String name, ContentResolver contentResolver) {
        return contentResolver.query(
                FakeCallsProvider.CONTENT_URI_CONTACTS,
                null,
                FakeCallsProvider.Contacts.COL_NAME + " LIKE ?",
                new String[]{"%" + name + "%"},
                null
        );
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