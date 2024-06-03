package com.example.saved_by_the_call.ui;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.cp.Contact;
import com.example.saved_by_the_call.cp.FakeCallsProvider;

public class EditContactActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_contact);

        final int contactId = (int) getIntent().getLongExtra("contact_id", -1);
        // Fields to show the contact.
        final ImageView currentContactImg = findViewById(R.id.imgViewEditContact);
        final TextView currentContactName = findViewById(R.id.txtViewContactName);
        final TextView currentContactPhone = findViewById(R.id.txtViewContactPhone);
        // Fields to edit the contact.
        final EditText newContactName = findViewById(R.id.edTxtNewContactName);
        final EditText newContactPhone = findViewById(R.id.edTxtNewContactPhone);
        final TextView newContactImg = findViewById(R.id.edTxtNewContactImg);
        final Button btnModifyContact = findViewById(R.id.btnModifyContact);
        final Button btnDeleteContact = findViewById(R.id.btnDeleteContact);

        // First, we check if the contact id is valid.
        if (contactId == -1) {
            Toast.makeText(this, R.string.error_accessing_contact_id,
                    Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // If the contact id is valid, we get the contact by its id.
            Contact contact = getContactById(contactId);
            if (contact == null) {
                Toast.makeText(this, R.string.error_accessing_contact_id,
                        Toast.LENGTH_SHORT).show();
                finish();
            } else {
                // If the query is successful, we set the contact data to the view.
                setContactData(contact, currentContactName, currentContactPhone,
                        currentContactImg);

                // Image picker
                pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(),
                        uri -> {
                            if (uri != null) {
                                Log.d("PhotoPicker", "Selected URI: " + uri);
                                newContactImg.setText(R.string.hint_contact_added_img);

                                selectedImageUri = uri;
                                final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION |
                                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
                                getContentResolver().takePersistableUriPermission(selectedImageUri,
                                        takeFlags);
                            } else {
                                Toast.makeText(EditContactActivity.this,
                                        R.string.toast_no_img_selected,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                // Add new contact image click listener.
                newContactImg.setOnClickListener(view -> {
                    if (ContextCompat.checkSelfPermission(EditContactActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        pickImg();
                    } else {
                        ActivityCompat.requestPermissions(EditContactActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });

                // Click listener for delete button.
                btnDeleteContact.setOnClickListener(view -> {
                    confirmDeleteContact(contactId);
                });

                // Click listener for edit button.
                btnModifyContact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Contact newContactData = new Contact(contactId,
                                newContactName.getText().toString() != null ? newContactName.getText().toString() : contact.getName(),
                                newContactPhone.getText().toString() != null ? newContactPhone.getText().toString() : contact.getPhone(),
                                selectedImageUri != null ? selectedImageUri.toString() : contact.getImg());
                        if (modifyContact(newContactData)) {
                            setContactData(contact, currentContactName, currentContactPhone,
                                    currentContactImg);
                            resetFields(newContactName, newContactPhone, newContactImg);
                        }
                    }
                });

            }
        }

    }

    private void resetFields(EditText newContactName, EditText newContactPhone, TextView newContactImg) {

    }

    private boolean modifyContact(Contact newContactData) {
        return false;
    }


    /**
     * This method launches the image picker.
     */
    private void pickImg() {
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
    }

    /**
     * This method shows an alert dialog to confirm the deletion of the contact.
     *
     * @param contactId id of the contact
     */
    private void confirmDeleteContact(int contactId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                EditContactActivity.this,
                R.style.AlertDialogCustom).setTitle(R.string.alert_dialog_title_upper);
        builder.setMessage(R.string.alert_verify_delete_contact_msg)
                .setPositiveButton(R.string.btn_ok, (dialog, which) -> {
                    ContentResolver contentResolver = getContentResolver();
                    Uri deleteUri = Uri.withAppendedPath(FakeCallsProvider.CONTENT_URI_CONTACTS, String.valueOf(contactId));
                    int rowsDeleted = contentResolver.delete(deleteUri, null, null);
                    if (rowsDeleted > 0) {
                        Toast.makeText(EditContactActivity.this,
                                R.string.toast_contact_deleted_confirmation,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditContactActivity.this,
                                R.string.error_deleting_contact, Toast.LENGTH_SHORT).show();
                        Log.e("EditContactActivity",
                                R.string.error_deleting_contact_related_uri + String.valueOf(deleteUri));
                    }
                    finish();
                }).setNegativeButton(R.string.btn_no, (dialog, which) -> {
                }).show();
    }

    /**
     * This method sets the contact data to the view.
     *
     * @param contact                 contact
     * @param txtViewEditContactName  TextView
     * @param txtViewEditContactPhone TextView
     * @param imgViewEditContact      ImageView
     */
    private void setContactData(Contact contact, TextView txtViewEditContactName,
                                TextView txtViewEditContactPhone, ImageView imgViewEditContact) {
        txtViewEditContactName.setText(contact.getName());
        txtViewEditContactPhone.setText(contact.getPhone());
        Glide.with(this)
                .load(contact.getImg())
                .error(R.drawable.contact_def_img)
                .placeholder(R.drawable.contact_def_img)
                .into(imgViewEditContact);
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
            Log.e("EditContactActivity", getString(R.string.error_accessing_to_database_and_get_contact));
        }
        return contact;
    }
}
