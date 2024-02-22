package com.example.examen_cp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

    private DatosSpinner avatarSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinnerAvatares = findViewById(R.id.spinnerAvatares);
        final ListView listViewContactos = findViewById(R.id.listViewContactos);
        final ImageButton imageButton = findViewById(R.id.imgBtnAddContactos);
        final TableLayout tableLayoutFormulario = findViewById(R.id.tableLayoutFormulario);
        final Button btnAdd = findViewById(R.id.btnAdd);
        final Button btnModificar = findViewById(R.id.btnModificar);
        final Button btnCancelar = findViewById(R.id.btnModificar);

        cargarAvatares(spinnerAvatares);
        mostrarListaContactos(listViewContactos);

        spinnerAvatares.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                avatarSeleccionado = (DatosSpinner) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this,
                        "No ha seleccionado ningún avatar",
                        Toast.LENGTH_SHORT).show();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableLayoutFormulario.setVisibility(View.VISIBLE);
                btnAdd.setVisibility(View.VISIBLE);
            }
        });

    }

    private void cargarAvatares(Spinner spinner) {
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
        spinner.setAdapter(adaptador);
    }

    private void mostrarListaContactos(ListView listViewContactos) {
        String[] columnas = new String[]{
                ContactosProvider.Contactos._ID,
                ContactosProvider.Contactos.COL_NOMBRE,
                ContactosProvider.Contactos.COL_TELEFONO,
                ContactosProvider.Contactos.COL_AVATAR
        };
        Uri versionesUri = ContactosProvider.CONTENT_URI;
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(versionesUri,
                columnas,
                null,
                null,
                null);
        DatosListView contacto;

        ArrayList<DatosListView> datos = new ArrayList<>();
        AdapterListView adapter = new AdapterListView(this, datos);
        if (cur != null) {
            if (cur.moveToFirst()) {
                int columnaId = cur.getColumnIndex(ContactosProvider.Contactos._ID);
                int columnaNombre = cur.getColumnIndex(ContactosProvider.Contactos.COL_NOMBRE);
                int columnaTelefono = cur.getColumnIndex(ContactosProvider.Contactos.COL_TELEFONO);
                int columnaAvatar = cur.getColumnIndex(ContactosProvider.Contactos.COL_AVATAR);

                do {
                    int id = cur.getInt(columnaId);
                    String nombre = cur.getString(columnaNombre);
                    String telefono = cur.getString(columnaTelefono);
                    int avatar = cur.getInt(columnaAvatar);
                    contacto = new DatosListView(id, nombre, telefono, avatar);
                    datos.add(contacto);
                } while (cur.moveToNext());
            }
        }
        listViewContactos.setAdapter(adapter);
    }

}