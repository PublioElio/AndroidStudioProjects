package com.example.ejercicio_09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.ejercicio_09.cp.VersionesProvider;
import com.example.ejercicio_09.gridViewElements.Adapter;
import com.example.ejercicio_09.gridViewElements.Datos;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnMostrar = findViewById(R.id.btnMostrar);
        final Button btnEliminar = findViewById(R.id.btnEliminar);
        final Button btnAgregar = findViewById(R.id.btnAgregar);
        final EditText editTextFechaLanzamiento = findViewById(R.id.editTextFechaLanzamiento);
        final EditText editTextVersion = findViewById(R.id.editTextVersion);
        final GridView miGridView = findViewById(R.id.miGrid);
        ArrayList<Datos> datos = new ArrayList<Datos>();

        Adapter miAdaptador = new Adapter(this, datos);
        miGridView.setAdapter(miAdaptador);

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        String[] columnas = new String[]{
                VersionesProvider.Versiones._ID,
                VersionesProvider.Versiones.COL_NOMBRE,
                VersionesProvider.Versiones.COL_YEAR};
        Uri clientesUri = VersionesProvider.CONTENT_URI;
        ContentResolver cr = getContentResolver();

        Cursor cur = cr.query(clientesUri,
                columnas,
                null,
                null,
                null);

        if (cur.moveToFirst()){
            String id;
            String nombre;
            String year;
            int colId = cur.getColumnIndex(VersionesProvider.Versiones.COL_ID);
            int colNombre = cur.getColumnIndex(VersionesProvider.Versiones.COL_NOMBRE);
            int colyear = cur.getColumnIndex(VersionesProvider.Versiones.COL_YEAR);
            txtResultados.setText("");
            do{
                id = cur.getString(colId);
                nombre = cur.getString(colNombre);
                year = cur.getString(colyear);
                txtResultados.append(id + " - " + nombre + " - " + year + "\n");
            }while (cur.moveToNext());
        }
    }
}