package com.example.ejercicio_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtengo los elementos del layout
        final EditText editTextNombre = findViewById(R.id.editTextNombre);
        final EditText editTextApellidos = findViewById(R.id.editTextApellidos);
        final Button btnInsertar = findViewById(R.id.btnIns);
        final Button btnConsultar = findViewById(R.id.btnCons);
        final Button btnBorrar = findViewById(R.id.btnBorrar);
        final Button btnActualizar = findViewById(R.id.btnAct);
        final ListView listViewDibujantes = findViewById(R.id.listViewDibujantes);

        ArrayList<Dibujante> dibujantes = new ArrayList<>();

        // Abrimos la base de datos en modo escritura
        DibujantesBBDD dibujantesBBDD = new DibujantesBBDD(this, "BDDibujantes", null, 1);
        SQLiteDatabase db = dibujantesBBDD.getWritableDatabase();

        if (db != null) {
            Log.i("BBDD", "Se ha creado la base de datos correctamente");
            // Insertar datos
            btnInsertar.setOnClickListener(view -> {
                String nombre = editTextNombre.getText().toString();
                String apellidos = editTextApellidos.getText().toString();

                ContentValues registro = new ContentValues();
                registro.put("nombre", nombre);
                registro.put("apellidos", apellidos);
                if (db.insert("Dibujantes", null, registro) < 0) {
                    Toast.makeText(MainActivity.this, "ERROR INSERTANDO REGISTRO", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "REGISTRO INSERTADO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                    resetearVista(listViewDibujantes, editTextNombre, editTextApellidos);
                }
            });

            // Consultar datos
            btnConsultar.setOnClickListener(view -> {
                selectedPosition = -1;
                dibujantes.clear();
                introducirDatos(dibujantes, db);
                Adaptador adaptador = new Adaptador(MainActivity.this, dibujantes);
                listViewDibujantes.setAdapter(adaptador);
                resetearVista(listViewDibujantes, editTextNombre, editTextApellidos);
                listViewDibujantes.setVisibility(View.VISIBLE);
            });
        }

        // Borramos datos
        btnBorrar.setOnClickListener(view -> {
            if (selectedPosition >= 0) {
                String codigoParaBorrar = String.valueOf(dibujantes.get(selectedPosition).getId());
                String whereClause = "codigo = ?";
                String[] whereArgs = {codigoParaBorrar};

                int numEntradasBorradas = db.delete("Dibujantes", whereClause, whereArgs);

                if (numEntradasBorradas > 0) {
                    Toast.makeText(MainActivity.this, "ENTRADA BORRADA CORRECTAMENTE", Toast.LENGTH_LONG).show();
                    resetearVista(listViewDibujantes, editTextNombre, editTextApellidos);
                } else {
                    Toast.makeText(MainActivity.this, "NO SE ENCONTRÓ NINGUNA ENTRADA CON EL CÓDIGO PROPORCIONADO", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "ERROR. CÓDIGO NO VÁLIDO", Toast.LENGTH_LONG).show();
            }
        });

        btnActualizar.setOnClickListener(view -> {
            if (selectedPosition >= 0) {
                try {
                    // Cogemos los datos introducidos por el usuario
                    String codigo = String.valueOf(dibujantes.get(selectedPosition).getId());
                    String nombre = editTextNombre.getText().toString();
                    String apellidos = editTextApellidos.getText().toString();

                    int numRowsUpdated = db.update("Dibujantes",
                            createContentValues(nombre, apellidos),
                            "codigo=?",
                            new String[]{codigo});

                    if (numRowsUpdated > 0) {
                        Toast.makeText(MainActivity.this, "ENTRADA ACTUALIZADA CORRECTAMENTE", Toast.LENGTH_LONG).show();
                        resetearVista(listViewDibujantes, editTextNombre, editTextApellidos);
                    } else {
                        Toast.makeText(MainActivity.this, "NO SE ENCONTRÓ NINGUNA ENTRADA CON EL CÓDIGO ESPECIFICADO", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "ERROR AL ACTUALIZAR LA ENTRADA: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    throw new RuntimeException(e);
                }

            }
        });


        listViewDibujantes.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position; // Guardar la posición seleccionada

            if (selectedPosition >= 0) {
                String codigo = String.valueOf(dibujantes.get(selectedPosition).getId());
                Cursor cursor = db.rawQuery("SELECT nombre, apellidos FROM Dibujantes WHERE codigo = " + codigo, null);
                if (cursor.moveToFirst()) {

                    int nombreIndex = cursor.getColumnIndex("nombre");
                    int apellidosIndex = cursor.getColumnIndex("apellidos");

                    if (nombreIndex != -1 && apellidosIndex != -1) {
                        String nombre = cursor.getString(nombreIndex);
                        String apellidos = cursor.getString(apellidosIndex);
                        editTextNombre.setText(nombre);
                        editTextApellidos.setText(apellidos);
                    }
                }
                cursor.close();
            }
        });


    }

    private static void introducirDatos(ArrayList<Dibujante> dibujantes, SQLiteDatabase db) {
        Cursor miCursor = db.rawQuery("SELECT codigo, nombre, apellidos FROM Dibujantes", null);
        if (miCursor.moveToFirst()) {
            do {
                int id = miCursor.getInt(0);
                String nombre = miCursor.getString(1);
                String apellidos = miCursor.getString(2);
                dibujantes.add(new Dibujante(id, nombre, apellidos));
            } while (miCursor.moveToNext());
            miCursor.close();
        }
    }

    private static void resetearVista(ListView listViewDibujantes, EditText editTextNombre, EditText editTextApellidos) {
        listViewDibujantes.setVisibility(View.GONE);
        editTextNombre.setText("");
        editTextApellidos.setText("");
    }

    private ContentValues createContentValues(String nombre, String apellidos) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellidos", apellidos);
        return values;
    }
}