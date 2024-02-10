package com.example.t7examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.t7examen2.AdaptadorListView.AdaptadorListView;
import com.example.t7examen2.AdaptadorListView.DatosListView;
import com.example.t7examen2.AdaptadorSpinner.AdaptadorSpinner;
import com.example.t7examen2.AdaptadorSpinner.DatosSpinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int avatarSelecionado=0;
    ListView listView;
    Spinner spinner;
    LinearLayout llayoutInvisible;
    Button btnModificar;
    Button btnCancelar;
    Button btnAñadir;
    int seleccionado=0;
    AdaptadorListView adaptadorListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);

        EditText etBuscarContactos=findViewById(R.id.etBuscarContactos);
        EditText etNombre=findViewById(R.id.etNombre);
        EditText etTelefono=findViewById(R.id.etTelefono);
        btnAñadir =findViewById(R.id.btnAñadir);
        btnCancelar =findViewById(R.id.btnCancelar);
        btnModificar = findViewById(R.id.btnModificar);
        llayoutInvisible =findViewById(R.id.llayoutInvisible);
        ImageButton imageButton=findViewById(R.id.imageButton);
        BD bd = new BD(this,"BD",null,1);
        SQLiteDatabase db = bd.getWritableDatabase();
        registerForContextMenu(listView);

        rellenarSpinner();
        listar();



        //--------------------------------------------------------------------------SPINNER-LISTENER
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                avatarSelecionado = ((DatosSpinner) parent.getItemAtPosition(position)).getAvatar();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //----------------------------------------------------------------------------MOSTRAR-LAYOUT
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llayoutInvisible.setVisibility(View.VISIBLE);
                btnAñadir.setVisibility(View.VISIBLE);
                btnCancelar.setVisibility(View.VISIBLE);
                btnModificar.setVisibility(View.GONE);
            }
        });



        //------------------------------------------------------------------------------BOTON-AÑADIR
        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etNombre.getText().toString().isEmpty() && !etTelefono.getText().toString().isEmpty()) {
                    ContentValues values = new ContentValues();
                    values.put(ProveedorContenido.Contactos.COL_NOMBRE, etNombre.getText().toString());
                    values.put(ProveedorContenido.Contactos.COL_TELEFONO, etTelefono.getText().toString());
                    values.put(ProveedorContenido.Contactos.COL_AVATAR, avatarSelecionado);

                    ContentResolver cr2 = getContentResolver();
                    cr2.insert(ProveedorContenido.CONTENT_URI, values);
                    etNombre.setText("");
                    etTelefono.setText("");
                }
                listar();
            }
        });



        //----------------------------------------------------------------------------BOTON-CANCELAR
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llayoutInvisible.setVisibility(View.GONE);
                btnAñadir.setVisibility(View.GONE);
                btnModificar.setVisibility(View.GONE);
                btnCancelar.setVisibility(View.GONE);
            }
        });



        //---------------------------------------------------------------------------BOTON-MODIFICAR
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(ProveedorContenido.Contactos.COL_NOMBRE, etNombre.getText().toString());
                values.put(ProveedorContenido.Contactos.COL_TELEFONO, etTelefono.getText().toString());
                values.put(ProveedorContenido.Contactos.COL_AVATAR, avatarSelecionado);

                ContentResolver cr2 = getContentResolver();
                cr2.update(ProveedorContenido.CONTENT_URI, values,ProveedorContenido.Contactos._ID + "=" + seleccionado, null);
                etNombre.setText("");
                etTelefono.setText("");

                listar();
            }
        });



        //------------------------------------------------------------------------SELECIONAR-LISTADO
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptador, View view, int position, long id) {
                seleccionado = ((DatosListView) adaptador.getItemAtPosition(position)).getIndice();
                Toast.makeText(getApplicationContext(), "Tabla selecionada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //----------------------------------------------------------------------------------CONTEXT-MENU
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }



    //-------------------------------------------------------------------------CONTEXT-MENU-LISTENER
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int position=info.position;
        DatosListView contacto=adaptadorListView.getItem(position);
        seleccionado= contacto.getIndice();


        switch (item.getItemId()) {
            case R.id.delete_item:
                ContentResolver cr = getContentResolver();
                cr.delete(ProveedorContenido.CONTENT_URI, ProveedorContenido.Contactos._ID + "=" + seleccionado, null);
                listar();
                return true;
            case R.id.update_item:
                llayoutInvisible.setVisibility(View.VISIBLE);
                btnAñadir.setVisibility(View.GONE);
                btnCancelar.setVisibility(View.VISIBLE);
                btnModificar.setVisibility(View.VISIBLE);
                listar();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }



    //-------------------------------------------------------------------------------RELLENAR-SPINER
    private void rellenarSpinner(){
        ArrayList<DatosSpinner> datos = new ArrayList<DatosSpinner>();
        AdaptadorSpinner adaptador = new AdaptadorSpinner(this,datos);
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



    //----------------------------------------------------------------------RELLENAR-LISTA-CONTACTOS
    private void listar(){
        String[] columnas = new String[]{
                ProveedorContenido.Contactos._ID,
                ProveedorContenido.Contactos.COL_NOMBRE,
                ProveedorContenido.Contactos.COL_TELEFONO,
                ProveedorContenido.Contactos.COL_AVATAR
        };
        Uri versionesUri = ProveedorContenido.CONTENT_URI;
        ContentResolver cr = getContentResolver();
        // Hacemos la consulta
        Cursor cur = cr.query(versionesUri,
                columnas,       // columnas a devolver
                null,           // condición de la consulta
                null,           // argumentos variables de la consulta
                null);          // orden de los resultados
        DatosListView objetoDato;

        ArrayList<DatosListView> datos = new ArrayList<DatosListView>();
        adaptadorListView = new AdaptadorListView(this,datos);
        if (cur != null) {
            if (cur.moveToFirst()) {
                int colId = cur.getColumnIndex(ProveedorContenido.Contactos._ID);
                int colNom = cur.getColumnIndex(ProveedorContenido.Contactos.COL_NOMBRE);
                int colTel = cur.getColumnIndex(ProveedorContenido.Contactos.COL_TELEFONO);
                int colAva=cur.getColumnIndex(ProveedorContenido.Contactos.COL_AVATAR);

                do {
                    int id = cur.getInt(colId);
                    String nombre = cur.getString(colNom);
                    String telefono=cur.getString(colTel);
                    int avatar = cur.getInt(colAva);
                    objetoDato = new DatosListView(id, nombre,telefono,avatar);
                    datos.add(objetoDato);
                } while (cur.moveToNext());
            }
        }
        listView.setAdapter(adaptadorListView);
    }
}