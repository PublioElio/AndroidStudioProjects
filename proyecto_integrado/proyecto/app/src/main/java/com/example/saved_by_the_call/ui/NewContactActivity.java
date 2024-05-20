package com.example.saved_by_the_call.ui;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.cp.FakeCallsProvider;
import com.example.saved_by_the_call.ui.top_menu.TopMenu;

public class NewContactActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_contact);

        // Get the fields
        final EditText contactName = findViewById(R.id.edTxtContactName);
        final EditText contactPhone = findViewById(R.id.edTxtContactPhone);
        final TextView addContactImg = findViewById(R.id.edTxtContactImg);
        final Button btnAddContact = findViewById(R.id.btnAddNewContact);

        final Toolbar toolbar = findViewById(R.id.toolbar_top_menu);
        setSupportActionBar(toolbar);

        // Image picker
        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(),
                uri -> {
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        addContactImg.setText(R.string.hint_contact_added_img);
                    } else {
                        Toast.makeText(NewContactActivity.this,
                                R.string.toast_no_img_selected,
                                Toast.LENGTH_SHORT).show();
                    }
                });

        // Add contact image
        addContactImg.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(NewContactActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                pickImg();
            } else {
                ActivityCompat.requestPermissions(NewContactActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
            }
        });

        // Add contact button
        btnAddContact.setOnClickListener(view -> {
            final TextView txtViewContactFormInfo = findViewById(R.id.txtViewContactFormInfo);
            if (checkFields(contactName, contactPhone)) {
                // TODO: CREAR LA CONTACTO EN LA BBDD CON IMAGEN
                if (createNewContact(contactName.getText().toString(), contactPhone.getText().toString())) {
                    Toast.makeText(getApplicationContext(),
                            R.string.toast_contact_created_confirmation, Toast.LENGTH_SHORT).show();
                    txtViewContactFormInfo.setTextColor(ContextCompat.getColor(view.getContext(),
                            R.color.black));
                    txtViewContactFormInfo.setText(R.string.asterisk);
                    resetFields(contactName, contactPhone, addContactImg);
                }
            } else {
                txtViewContactFormInfo.setTextColor(ContextCompat.getColor(view.getContext(),
                        R.color.red));
                txtViewContactFormInfo.setText(R.string.warning_new_contact_incorrect_fields);
            }
        });

    }

    // TODO: MODIFICAR EL MÉTODO PARA QUE TAMBIÉN SE PUEDA AÑADIR UNA IMAGEN
    /**
     * This method creates a new contact in the database.
     *
     * @param name  contact name
     * @param phone contact phone
     * @return true if the contact is created
     */
    private boolean createNewContact(String name, String phone) {
        boolean contactCreated = false;

        ContentValues values = new ContentValues();
        values.put(FakeCallsProvider.Contacts.COL_NAME, name);
        values.put(FakeCallsProvider.Contacts.COL_PHONE, phone);
        //values.put(FakeCallsProvider.Contacts.COL_IMG, img);

        ContentResolver cr = getContentResolver();
        Uri newContactUri = cr.insert(FakeCallsProvider.CONTENT_URI_CONTACTS, values);
        if (newContactUri != null) {
            contactCreated = true;
        }

        return contactCreated;
    }

    /**
     * This method resets the fields to their default values.
     *
     * @param contactName   contact name EditText field
     * @param contactPhone  contact phone EditText field
     * @param addContactImg contact image TextView field
     */
    private void resetFields(EditText contactName, EditText contactPhone, TextView addContactImg) {
        contactName.setText("");
        contactPhone.setText("");
        addContactImg.setText(R.string.hint_upload_img);
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
     * This method checks that all fields are correctly filled out, displaying/hiding error messages
     * in the corresponding fields.
     *
     * @param contactName  contact name EditText field
     * @param contactPhone contact phone EditText field
     * @return true if all fields are correct
     */
    private boolean checkFields(EditText contactName, EditText contactPhone) {

        // Warnings for each field
        final TextView edTxtContactNameWarning = findViewById(R.id.edTxtNewContactNameWarning);
        final TextView edTxtContactPhoneWarning = findViewById(R.id.edTxtNewContactPhoneWarning);

        boolean allFieldsCorrect = true;

        if (!checkFieldName(contactName)) {
            edTxtContactNameWarning.setVisibility(View.VISIBLE);
            allFieldsCorrect = false;
        } else {
            edTxtContactNameWarning.setVisibility(View.INVISIBLE);
        }

        if (!checkFieldPhone(contactPhone)) {
            edTxtContactPhoneWarning.setVisibility(View.VISIBLE);
            allFieldsCorrect = false;
        } else {
            edTxtContactPhoneWarning.setVisibility(View.INVISIBLE);
        }
        return allFieldsCorrect;
    }

    /**
     * This method checks that the name is at least 3 characters long, not empty and
     * not repeated in the database.
     *
     * @param contactName name EditText field
     * @return true if the field is correct
     */
    private boolean checkFieldName(EditText contactName) {
        String name = contactName.getText().toString();
        boolean correctName = !name.isEmpty() &&
                contactName.getText().toString().length() >= 3;
        if (correctName) {
            correctName = checkRepeatedName(name);
        }
        return correctName;
    }

    /**
     * This method checks if the name is already in the database.
     *
     * @param name name to check
     * @return true if the name is not repeated
     */
    private boolean checkRepeatedName(String name) {
        boolean validName = false;
        ContentResolver cr = getContentResolver();
        String[] projection = {FakeCallsProvider.Contacts.COL_NAME};
        String selection = FakeCallsProvider.Contacts.COL_NAME + " = ?";
        String[] selectionArgs = {name};
        Cursor cursor = cr.query(FakeCallsProvider.CONTENT_URI_CONTACTS, projection, selection,
                selectionArgs,null);
        if (cursor != null) {
            validName = cursor.getCount() == 0;
            cursor.close();
        }
        return validName;
    }

    /**
     * This method checks that the phone number is at least 3 characters long, not empty and
     * contains only digits.
     *
     * @param contactPhone phone number EditText field
     * @return true if the field is correct
     */
    private boolean checkFieldPhone(EditText contactPhone) {
        String phone = contactPhone.getText().toString();
        return !phone.isEmpty() && phone.length() >= 3 && allAreNumbers(phone);
    }

    /**
     * This method checks if all the characters in a string are numbers.
     *
     * @param s string to check
     * @return true if all characters are numbers
     */
    public static boolean allAreNumbers(String s) {
        return s.chars().allMatch(Character::isDigit);
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
