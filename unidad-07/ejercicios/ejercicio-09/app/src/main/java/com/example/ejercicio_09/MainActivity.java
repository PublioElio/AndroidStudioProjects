package com.example.ejercicio_09;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio_09.cp.VersionesProvider;
import com.example.ejercicio_09.gridViewElements.Adapter;
import com.example.ejercicio_09.gridViewElements.Datos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Datos datoSeleccionado;
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

        if (insertarDatosEnDB()) {

            miGridView.setOnItemClickListener((adapterView, view, position, id) -> {
                // Obtener el dato seleccionado en el GridView
                datoSeleccionado = (Datos) adapterView.getItemAtPosition(position);
            });

            btnMostrar.setOnClickListener(view -> {
                mostrarDatos(miGridView);
                miGridView.setVisibility(View.VISIBLE);
            });

            btnAgregar.setOnClickListener(view -> {
                miGridView.setVisibility(View.INVISIBLE);
                String fechaLanzamiento = editTextFechaLanzamiento.getText().toString();
                String version = editTextVersion.getText().toString();

                if (!fechaLanzamiento.isEmpty() && !version.isEmpty()) {
                    ContentResolver contentResolver = getContentResolver();
                    ContentValues values = new ContentValues();
                    values.put(VersionesProvider.Versiones.COL_NOMBRE, version);
                    values.put(VersionesProvider.Versiones.COL_YEAR, fechaLanzamiento);
                    Uri nuevoRegistroUri = contentResolver.insert(VersionesProvider.CONTENT_URI, values);
                    if (nuevoRegistroUri != null) {
                        editTextFechaLanzamiento.setText("");
                        editTextVersion.setText("");
                        Log.i("ACTUALIZACIÓN", "Nuevo campo insertado correctamente en la base de datos");
                        Toast.makeText(MainActivity.this, "Nueva versión insertada correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("ERROR", "Error insertando datos");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Por favor, rellene ambos campos", Toast.LENGTH_SHORT).show();
                }
            });

            btnEliminar.setOnClickListener(view -> {
                if (datoSeleccionado != null) {
                    int idSeleccionado = datoSeleccionado.getId();
                    Uri uriEliminar = Uri.withAppendedPath(VersionesProvider.CONTENT_URI, String.valueOf(idSeleccionado));
                    int filasEliminadas = getContentResolver().delete(uriEliminar, null, null);
                    if (filasEliminadas > 0) {
                        Log.i("ACTUALIZACIÓN", "Campo eliminado de la base de datos");
                        Toast.makeText(MainActivity.this, "Elemento eliminado correctamente", Toast.LENGTH_SHORT).show();
                        miGridView.setVisibility(View.INVISIBLE);
                        datoSeleccionado = null;
                    } else {
                        Log.e("ERROR", "Error elemento no eliminado de la base de datos");
                        Toast.makeText(MainActivity.this, "Error al eliminar el elemento", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Por favor, seleccione un elemento para eliminar", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Log.e("ERROR", "Ocurrió un error creando la base de datos");
        }
    }

    private void mostrarDatos(GridView miGridView) {
        ArrayList<Datos> datos = listarDB();
        Adapter miAdaptador = new Adapter(this, datos);
        miGridView.setAdapter(miAdaptador);
    }

    private ArrayList<Datos> listarDB() {
        ArrayList<Datos> lista = new ArrayList<>();
        String[] columnas = new String[]{
                VersionesProvider.Versiones._ID,
                VersionesProvider.Versiones.COL_NOMBRE,
                VersionesProvider.Versiones.COL_YEAR};
        Uri versionesUri = VersionesProvider.CONTENT_URI;
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(versionesUri,
                columnas,
                null,
                null,
                null);
        assert cur != null; // He incluído esta línea de código por un warning que me daba el IDE sobre una posible null Pointer Exception
        if (cur.moveToFirst()) {
            int id;
            String nombre;
            int year;
            int colId = cur.getColumnIndex(VersionesProvider.Versiones.COL_ID);
            int colNombre = cur.getColumnIndex(VersionesProvider.Versiones.COL_NOMBRE);
            int colyear = cur.getColumnIndex(VersionesProvider.Versiones.COL_YEAR);
            do {
                id = cur.getInt(colId);
                nombre = cur.getString(colNombre);
                year = cur.getInt(colyear);
                lista.add(new Datos(id, nombre, year));
            } while (cur.moveToNext());
        }
        cur.close();
        return lista;
    }

    private boolean insertarDatosEnDB() {
        int cantidadDatosInsertados = 0;
        String[] versionesAndroid = {
                "Android 1.0", "Android 1.1", "Android 1.5 Cupcake", "Android 1.6 Donut",
                "Android 2.0 Eclair", "Android 2.2 Froyo", "Android 2.3 Gingerbread",
                "Android 4.0 Ice Cream Sandwich", "Android 4.4 KitKat", "Android 5.0 Lollipop"
        };
        int[] anosLanzamiento = {
                2008, 2009, 2009, 2009, 2009, 2010, 2010, 2011, 2013, 2014
        };

        // Obtener el ContentResolver
        ContentResolver contentResolver = getContentResolver();
        // Eliminar los datos existentes en la base de datos
        contentResolver.delete(VersionesProvider.CONTENT_URI, null, null);

        // Insertar los nuevos datos en la base de datos
        for (int i = 0; i < versionesAndroid.length; i++) {
            ContentValues values = new ContentValues();
            values.put(VersionesProvider.Versiones.COL_NOMBRE, versionesAndroid[i]);
            values.put(VersionesProvider.Versiones.COL_YEAR, anosLanzamiento[i]);
            contentResolver.insert(VersionesProvider.CONTENT_URI, values);
            cantidadDatosInsertados = i;
        }
        return cantidadDatosInsertados == 9;
    }
}

