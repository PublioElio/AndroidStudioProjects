package com.example.examen_final_adiaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.examen_final_adiaz.adaptador.Adaptador;
import com.example.examen_final_adiaz.adaptador.Datos;
import com.example.examen_final_adiaz.db.PeliculasDB;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private int peliculaSeleccionada = -1;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new
                Intent(MainActivity.this, ConfiguracionActivity.class));
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String NINGUNA_PELICULA_SELECCIONADA = "Ninguna película seleccionada";
        final String ERROR = "ERROR";
        final LinearLayout linearLayoutAdd = findViewById(R.id.linearLayoutAdd);
        final LinearLayout linearLayoutQuitar = findViewById(R.id.linearLayoutBorrar);
        final Button btnAdd = findViewById(R.id.btnAdd);
        final Button btnAddCancelar = findViewById(R.id.btnAddCancelar);
        final Button btnQuitar = findViewById(R.id.btnBorrar);
        final Button btnQuitarCancelar = findViewById(R.id.btnQuitarCancelar);
        final ListView tendencias = findViewById(R.id.listViewTendencias);
        final ListView miLista = findViewById(R.id.listViewMiLista);

        PeliculasDB peliculasDB = new PeliculasDB(this, "PeliculasDB", null, 1);
        SQLiteDatabase db = peliculasDB.getWritableDatabase();

        if (db != null) {
            cargarPeliculasDeBD(db);
            // Cargar tendencias
            cargarListaPeliculas(db, tendencias, 0);
            // Cargar MiLista
            cargarListaPeliculas(db, miLista, 1);
        }

        tendencias.setOnItemClickListener((parent, view, position, id) -> {
            peliculaSeleccionada = ((Datos) tendencias.getItemAtPosition(position)).getIndice();
            ocultarMostrarBotones(linearLayoutQuitar, linearLayoutAdd);
        });

        miLista.setOnItemClickListener((parent, view, position, id) -> {
            peliculaSeleccionada = ((Datos) miLista.getItemAtPosition(position)).getIndice();
            ocultarMostrarBotones(linearLayoutAdd, linearLayoutQuitar);
        });

        btnAddCancelar.setOnClickListener(view -> ocultarControlesAdd(linearLayoutAdd));

        btnQuitarCancelar.setOnClickListener(view -> ocultarControlesQuitar(linearLayoutQuitar, linearLayoutAdd));

        btnQuitar.setOnClickListener(view -> {
            if (peliculaSeleccionada >= 0) {
                String whereClause = "_id = ?";
                String[] whereArgs = {String.valueOf(peliculaSeleccionada)};
                assert db != null;
                if (db.delete("peliculas", whereClause, whereArgs) > 0) {
                    Log.i("Información", "Película eliminada de la lista");
                    ocultarControlesQuitar(linearLayoutQuitar, linearLayoutAdd);
                    cargarListaPeliculas(db, miLista, 1);
                } else {
                    Log.e(ERROR, "ERROR eliminando película de la lista");
                }
            } else {
                Log.i("Información", NINGUNA_PELICULA_SELECCIONADA);
            }
        });

        btnAdd.setOnClickListener(view -> {
            if (peliculaSeleccionada >= 0) {
                assert db != null;
                Cursor miCursor = db.rawQuery("SELECT foto,nombre FROM peliculas WHERE _id=" +
                        peliculaSeleccionada, null);
                if (miCursor.moveToFirst()) {
                    String nombre = miCursor.getString(1);
                    if (!buscarPeliculaPorTitulo(nombre, db)) {
                        insertarPeliculaEnMiLista(miLista, db, miCursor, nombre);
                    } else {
                        Log.e(ERROR, "ERROR la película ya se encuentra en la lista");
                    }
                }
            } else {
                Log.i("Información", NINGUNA_PELICULA_SELECCIONADA);
            }
            ocultarControlesAdd(linearLayoutAdd);
        });
    }

    private static boolean buscarPeliculaPorTitulo(String nombre, SQLiteDatabase db) {
        Cursor miCursor = db.rawQuery("SELECT * FROM peliculas WHERE nombre='" + nombre +
                "' AND lista=1", null);
        boolean encontrada = miCursor.moveToFirst();
        miCursor.close();
        return encontrada;
    }

    private void insertarPeliculaEnMiLista(ListView miLista, SQLiteDatabase db, Cursor miCursor, String nombre) {
        ContentValues registro = new ContentValues();
        registro.put("foto", miCursor.getInt(0));
        registro.put("nombre", nombre);
        registro.put("lista", 1);
        if (db.insert("peliculas", null, registro) > 0) {
            Log.i("BD info", "Película añadida correctamente");
            cargarListaPeliculas(db, miLista, 1);
        } else {
            Log.e("BD ERROR", "ERROR añadiendo película a la base de datos");
        }
    }

    private void ocultarControlesQuitar(LinearLayout linearLayoutQuitar, LinearLayout linearLayoutAdd) {
        linearLayoutQuitar.setVisibility(View.GONE);
        linearLayoutAdd.setVisibility(View.INVISIBLE);
        peliculaSeleccionada = -1;
    }

    private void ocultarControlesAdd(LinearLayout linearLayoutAdd) {
        linearLayoutAdd.setVisibility(View.INVISIBLE);
        peliculaSeleccionada = -1;
    }

    private static void ocultarMostrarBotones(LinearLayout layoutGone, LinearLayout
            layoutVisible) {
        layoutGone.setVisibility(View.GONE);
        layoutVisible.setVisibility(View.VISIBLE);
    }

    private void cargarPeliculasDeBD(SQLiteDatabase db) {
        db.execSQL("delete from peliculas;");
        db.execSQL("INSERT INTO peliculas(nombre,foto,lista) VALUES ('Alquimia de almas'," + R.drawable.alquimia + ",'0')");
        db.execSQL("INSERT INTO peliculas(nombre,foto,lista) VALUES ('Breaquing Bad'," + R.drawable.breaking + ",'0')");
        db.execSQL("INSERT INTO peliculas(nombre,foto,lista) VALUES ('Broadchurch'," + R.drawable.broadchurch + ",'0')");
        db.execSQL("INSERT INTO peliculas(nombre,foto,lista) VALUES ('Erased'," + R.drawable.erased + ",'0')");
        db.execSQL("INSERT INTO peliculas(nombre,foto,lista) VALUES ('The Haunting of Hill House'," + R.drawable.hill + ",'0')");
        db.execSQL("INSERT INTO peliculas(nombre,foto,lista) VALUES ('Holws Moving Castle'," + R.drawable.howl + ",'0')");
        db.execSQL("INSERT INTO peliculas(nombre,foto,lista) VALUES ('Lucifer'," + R.drawable.lucifer + ",'0')");
        db.execSQL("INSERT INTO peliculas(nombre,foto,lista) VALUES ('Stranger Things'," + R.drawable.stranger + ",'0')");
        db.execSQL("INSERT INTO peliculas(nombre,foto,lista) VALUES ('Supernatural'," + R.drawable.supernatural + ",'0')");
        db.execSQL("INSERT INTO peliculas(nombre,foto,lista) VALUES ('Attack on Titan'," + R.drawable.titanes + ",'0')");
    }

    private void cargarListaPeliculas(SQLiteDatabase db, ListView miLista, int lista) {
        ArrayList<Datos> datos = new ArrayList<>();
        Adaptador adaptador = new Adaptador(MainActivity.this, datos);
        String sentenciaSelect = "SELECT _id,foto,nombre FROM peliculas WHERE lista=" + lista + ";";
        Cursor miCursor = db.rawQuery(sentenciaSelect, null);
        if (miCursor.moveToFirst()) {
            do {
                int id = miCursor.getInt(0);
                int foto = miCursor.getInt(1);
                String nombre = miCursor.getString(2);
                datos.add(new Datos(id, foto, nombre));
            } while (miCursor.moveToNext());
            miCursor.close();
        }
        miLista.setAdapter(adaptador);
    }
}
