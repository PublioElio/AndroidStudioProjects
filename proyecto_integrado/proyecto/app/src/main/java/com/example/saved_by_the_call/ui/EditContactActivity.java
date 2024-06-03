package com.example.saved_by_the_call.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.cp.Contact;
import com.example.saved_by_the_call.cp.FakeCallsProvider;

public class EditContactActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_contact);

        final int contactId = (int) getIntent().getLongExtra("contact_id", -1);
        final ImageView imgViewEditContact = findViewById(R.id.imgViewEditContact);
        final TextView txtViewEditContactName = findViewById(R.id.txtViewContactName);
        final TextView txtViewEditContactPhone = findViewById(R.id.txtViewContactPhone);
        final Button btnEditContact = findViewById(R.id.btnEditContact);
        final Button btnDeleteContact = findViewById(R.id.btnDeleteContact);

        if (contactId == -1) {
            Toast.makeText(this, "Problema al acceder al contacto seleccionado",
                    Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Contact contact = getContactById(contactId);
            if (contact == null) {
                Toast.makeText(this, "Problema al acceder al contacto seleccionado",
                        Toast.LENGTH_SHORT).show();
                finish();
            } else {
                txtViewEditContactName.setText(contact.getName());
                txtViewEditContactPhone.setText(contact.getPhone());
                Glide.with(this)
                        .load(contact.getImg())
                        .error(R.drawable.contact_def_img)
                        .placeholder(R.drawable.contact_def_img)
                        .into(imgViewEditContact);
                btnDeleteContact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditContactActivity.this);
                        builder.setMessage("¿Estás seguro de que quieres borrar este contacto?").setPositiveButton("Sí", (dialog, which) -> {
                            ContentResolver contentResolver = getContentResolver();
                            contentResolver.delete(FakeCallsProvider.CONTENT_URI_CONTACTS,
                                    "_id_contact = ?", new String[]{String.valueOf(contactId)});
                        }).setNegativeButton("No", (dialog, which) -> {
                        }).show();
                    }
                });

            }
        }

    }

    /**
     * This method queries the contact by its id.
     *
     * @param contactId id of the contact
     * @return contact
     */
    private Contact getContactById(int contactId) {
        Contact contact = null;
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor;
        try {
            cursor = contentResolver.query(FakeCallsProvider.CONTENT_URI_CONTACTS,
                    null, "_id_contact = ?", new String[]{String.valueOf(contactId)}, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_ID);
                    int nameIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_NAME);
                    int phoneIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_PHONE);
                    int imgIndex = cursor.getColumnIndex(FakeCallsProvider.Contacts.COL_IMG);
                    contact = new Contact(cursor.getInt(idIndex), cursor.getString(nameIndex),
                            cursor.getString(phoneIndex), cursor.getString(imgIndex));
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contact;
    }
}