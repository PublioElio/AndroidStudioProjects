package com.example.examen_cp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.examen_cp.cp.ContactosProvider;
import com.example.examen_cp.listview.AdapterListView;
import com.example.examen_cp.listview.DatosListView;
import com.example.examen_cp.spinner.AdapterSpinner;
import com.example.examen_cp.spinner.DatosSpinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String MUST_FILL_BOTH = "Debe cumplimentar ambos campos";
    private int selectedAvatar; // Esta variable controla el avatar elegido del Spinner
    private int selectedContact = 0; // esta variable controla el contacto elegido del ListView
    private ListView listViewContactos;
    private Spinner spinnerAvatares;
    private TableLayout tableLayoutFormulario;
    private Button btnAdd;
    private Button btnModificar;
    private EditText editTextNombre;
    private EditText editTextTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        spinnerAvatares = findViewById(R.id.spinnerAvatares);
        listViewContactos = findViewById(R.id.listViewContactos);
        tableLayoutFormulario = findViewById(R.id.tableLayoutFormulario);
        btnAdd = findViewById(R.id.btnAdd);
        btnModificar = findViewById(R.id.btnModificar);
        final Button btnCancelar = findViewById(R.id.btnCancelar);
        final ImageButton imageButton = findViewById(R.id.imgBtnAddContactos);


        loadAvatars();
        showContactList();
        registerForContextMenu(listViewContactos);


        spinnerAvatares.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAvatar = ((DatosSpinner) parent.getItemAtPosition(position)).getAvatar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this,
                        "No ha seleccionado ningún avatar", Toast.LENGTH_SHORT).show();
            }
        });

        imageButton.setOnClickListener(view -> {
            clearEditText();
            spinnerAvatares.setSelection(0);
            tableLayoutFormulario.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
            btnModificar.setVisibility(View.GONE);
        });

        btnAdd.setOnClickListener(view -> addNewContact(editTextNombre, editTextTelefono));

        btnCancelar.setOnClickListener(view -> hideAll());

        btnModificar.setOnClickListener(view -> updateContact());

        listViewContactos.setOnItemClickListener((adaptador, view, position, id) -> selectedContact = ((DatosListView) adaptador.getItemAtPosition(position)).getId());
    }

    private void clearEditText() {
        editTextTelefono.setText("");
        editTextNombre.setText("");
    }

    private void hideAll() {
        tableLayoutFormulario.setVisibility(View.GONE);
        btnAdd.setVisibility(View.GONE);
        btnModificar.setVisibility(View.GONE);
    }

    private void updateContact() {
        String nombre = editTextNombre.getText().toString();
        String telefono = editTextTelefono.getText().toString();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(MainActivity.this, MUST_FILL_BOTH,
                    Toast.LENGTH_LONG).show();

        } else {
            ContentValues values = new ContentValues();
            values.put(ContactosProvider.Contactos.COL_NOMBRE, nombre);
            values.put(ContactosProvider.Contactos.COL_TELEFONO, telefono);
            values.put(ContactosProvider.Contactos.COL_AVATAR, selectedAvatar);

            ContentResolver cr = getContentResolver();
            cr.update(ContactosProvider.CONTENT_URI, values,
                    ContactosProvider.Contactos._ID + "=" + selectedContact, null);
            clearEditText();
            hideAll();
            showContactList();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        boolean resultado = false;
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(info != null){
            int position = info.position;
            DatosListView contacto = (DatosListView) listViewContactos.getItemAtPosition(position);
            selectedContact = contacto.getId();
            // Me he visto obligado a usar una estructura if-else porque el switch me daba un error que
            // no he logrado solventar: "Constant expression required"
            int id = item.getItemId();
            if (id == R.id.deleteContact) {
                resultado = deleteContact();
            } else if (id == R.id.updateContact) {
                resultado = showContactData(contacto);
            } else {
                resultado = super.onContextItemSelected(item);
            }
        }
        return resultado;
    }

    private boolean showContactData(DatosListView contacto) {
        editTextNombre.setText(contacto.getNombre());
        editTextTelefono.setText(contacto.getTelefono());
        int avatarContacto = contacto.getAvatar();
        int indiceAvatar = getAvatarIndex(avatarContacto);
        spinnerAvatares.setSelection(indiceAvatar);
        tableLayoutFormulario.setVisibility(View.VISIBLE);
        btnAdd.setVisibility(View.GONE);
        btnModificar.setVisibility(View.VISIBLE);
        return true;
    }

    private boolean deleteContact() {
        ContentResolver cr = getContentResolver();
        cr.delete(ContactosProvider.CONTENT_URI, ContactosProvider.Contactos._ID
                + "=" + selectedContact, null);
        hideAll();
        showContactList();
        return true;
    }

    private int getAvatarIndex(int avatarContacto) {
        AdapterSpinner adapter = (AdapterSpinner) spinnerAvatares.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            DatosSpinner datosSpinner = (DatosSpinner) adapter.getItem(i);
            if (datosSpinner.getAvatar() == avatarContacto) {
                return i;
            }
        }
        return 0;
    }

    private void addNewContact(EditText editTextNombre, EditText editTextTelefono) {
        String nombre = editTextNombre.getText().toString();
        String telefono = editTextTelefono.getText().toString();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(MainActivity.this, MUST_FILL_BOTH,
                    Toast.LENGTH_LONG).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(ContactosProvider.Contactos.COL_NOMBRE, nombre);
            values.put(ContactosProvider.Contactos.COL_TELEFONO, telefono);
            values.put(ContactosProvider.Contactos.COL_AVATAR, selectedAvatar);

            ContentResolver cr = getContentResolver();
            cr.insert(ContactosProvider.CONTENT_URI, values);

            clearEditText();
            showContactList();
        }
    }

    private void loadAvatars() {
        ArrayList<DatosSpinner> datos = new ArrayList<>();
        AdapterSpinner adaptador = new AdapterSpinner(this, datos);
        datos.add(new DatosSpinner(R.drawable.batman));
        datos.add(new DatosSpinner(R.drawable.capi));
        datos.add(new DatosSpinner(R.drawable.deadpool));
        datos.add(new DatosSpinner(R.drawable.furia));
        datos.add(new DatosSpinner(R.drawable.hulk));
        datos.add(new DatosSpinner(R.drawable.ironman));
        datos.add(new DatosSpinner(R.drawable.lobezno));
        datos.add(new DatosSpinner(R.drawable.spiderman));
        datos.add(new DatosSpinner(R.drawable.thor));
        datos.add(new DatosSpinner(R.drawable.wonderwoman));
        spinnerAvatares.setAdapter(adaptador);
    }

    private void showContactList() {
        String[] columnas = new String[]{
                ContactosProvider.Contactos._ID,
                ContactosProvider.Contactos.COL_NOMBRE,
                ContactosProvider.Contactos.COL_TELEFONO,
                ContactosProvider.Contactos.COL_AVATAR
        };
        Uri versionesUri = ContactosProvider.CONTENT_URI;
        ContentResolver cr = getContentResolver();

        try (Cursor cur = cr.query(versionesUri, columnas, null, null, null)) {
            if (cur != null && cur.moveToFirst()) {
                ArrayList<DatosListView> datos = new ArrayList<>();

                int columnaId = cur.getColumnIndex(ContactosProvider.Contactos._ID);
                int columnaNombre = cur.getColumnIndex(ContactosProvider.Contactos.COL_NOMBRE);
                int columnaTelefono = cur.getColumnIndex(ContactosProvider.Contactos.COL_TELEFONO);
                int columnaAvatar = cur.getColumnIndex(ContactosProvider.Contactos.COL_AVATAR);

                do {
                    int id = cur.getInt(columnaId);
                    String nombre = cur.getString(columnaNombre);
                    String telefono = cur.getString(columnaTelefono);
                    int avatar = cur.getInt(columnaAvatar);
                    DatosListView contacto = new DatosListView(id, nombre, telefono, avatar);
                    datos.add(contacto);
                } while (cur.moveToNext());

                AdapterListView adapter = new AdapterListView(this, datos);
                listViewContactos.setAdapter(adapter);
            }
        } catch (Exception e) {
            Log.e("ERROR", "Error al leer el cursor: " + e.getMessage());
        }
    }

}