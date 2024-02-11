package com.example.practica_exam_adiaz;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.practica_exam_adiaz.adaptador.Adaptador;
import com.example.practica_exam_adiaz.adaptador.Datos;
import com.example.practica_exam_adiaz.db.PeliculasDB;

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
        startActivity(new Intent(MainActivity.this, ConfiguracionActivity.class));
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final LinearLayout linearLayoutAdd = findViewById(R.id.linearLayoutAdd);
        final LinearLayout linearLayoutQuitar = findViewById(R.id.linearLayoutQuitar);
        final Button btnAdd = findViewById(R.id.btnAdd);
        final Button btnAddCancelar = findViewById(R.id.btnAddCancelar);
        final Button btnQuitar = findViewById(R.id.btnQuitar);
        final Button btnQuitarCancelar = findViewById(R.id.btnQuitarCancelar);
        final ListView tendencias = findViewById(R.id.listViewTendencias);
        final ListView miLista = findViewById(R.id.listViewMiLista);

        PeliculasDB peliculasDB = new PeliculasDB(this, "PeliculasDB", null, 1);
        SQLiteDatabase db = peliculasDB.getWritableDatabase();

        if (db != null) {
            insertarDatos(db);
            cargarTendencias(db, tendencias);
            cargarMiLista(db, miLista);
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
                    cargarMiLista(db, miLista);
                } else {
                    Log.e("ERROR", "ERROR eliminando película de la lista");
                }
            } else {
                Log.i("Información", "Ninguna película seleccionada");
            }
        });

        btnAdd.setOnClickListener(view -> {
            if (peliculaSeleccionada >= 0) {
                assert db != null;
                Cursor miCursor = db.rawQuery("SELECT portada,titulo FROM peliculas WHERE _id=" + peliculaSeleccionada, null);
                if (miCursor.moveToFirst()) {
                    String titulo = miCursor.getString(1);
                    if (!buscarPeliculaPorTitulo(titulo, db)) {
                        insertarPeliculaEnMiLista(miLista, db, miCursor, titulo);
                    } else {
                        Log.e("ERROR", "ERROR la película ya se encuentra en la lista");
                    }
                }
            } else {
                Log.i("Información", "Ninguna película seleccionada");
            }
            ocultarControlesAdd(linearLayoutAdd);
        });
    }

    private void ocultarControlesQuitar(LinearLayout linearLayoutQuitar, LinearLayout linearLayoutAdd) {
        linearLayoutQuitar.setVisibility(View.GONE);
        linearLayoutAdd.setVisibility(View.INVISIBLE);
        peliculaSeleccionada = -1;
    }


    private void insertarPeliculaEnMiLista(ListView miLista, SQLiteDatabase db, Cursor miCursor, String titulo) {
        ContentValues registro = new ContentValues();
        registro.put("portada", miCursor.getInt(0));
        registro.put("titulo", titulo);
        registro.put("lista", 1);
        if (db.insert("peliculas", null, registro) > 0) {
            Log.i("BD info", "Película añadida correctamente");
            cargarMiLista(db, miLista);
        } else {
            Log.e("BD ERROR", "ERROR añadiendo película a la base de datos");
        }
    }

    private static boolean buscarPeliculaPorTitulo(String titulo, SQLiteDatabase db) {
        Cursor miCursor = db.rawQuery("SELECT * FROM peliculas WHERE titulo='" + titulo +
                "' AND lista=1", null);
        boolean encontrada = miCursor.moveToFirst();
        miCursor.close();
        return encontrada;
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

    private static void insertarDatos(SQLiteDatabase db) {
        db.execSQL("delete from peliculas;");
        db.execSQL("INSERT INTO peliculas(titulo,portada,lista) VALUES ('ALQUIMIA'," + R.drawable.alquimia + ",'0')");
        db.execSQL("INSERT INTO peliculas(titulo,portada,lista) VALUES ('BREAKING BAD'," + R.drawable.breaking + ",'0')");
        db.execSQL("INSERT INTO peliculas(titulo,portada,lista) VALUES ('BROADCHURCH'," + R.drawable.broadchurch + ",'0')");
        db.execSQL("INSERT INTO peliculas(titulo,portada,lista) VALUES ('ERASED'," + R.drawable.erased + ",'0')");
        db.execSQL("INSERT INTO peliculas(titulo,portada,lista) VALUES ('HILL'," + R.drawable.hill + ",'0')");
        db.execSQL("INSERT INTO peliculas(titulo,portada,lista) VALUES ('HOWL'," + R.drawable.howl + ",'0')");
        db.execSQL("INSERT INTO peliculas(titulo,portada,lista) VALUES ('LUCIFER'," + R.drawable.lucifer + ",'0')");
        db.execSQL("INSERT INTO peliculas(titulo,portada,lista) VALUES ('STRANGER THINGS'," + R.drawable.stranger + ",'0')");
        db.execSQL("INSERT INTO peliculas(titulo,portada,lista) VALUES ('SUPERNATURAL'," + R.drawable.supernatural + ",'0')");
        db.execSQL("INSERT INTO peliculas(titulo,portada,lista) VALUES ('ATTACK ON TITAN'," + R.drawable.titanes + ",'0')");
    }

    private void cargarTendencias(SQLiteDatabase db, ListView tendencias) {
        ArrayList<Datos> datos = new ArrayList<>();
        Adaptador adaptador = new Adaptador(MainActivity.this, datos);
        Cursor miCursor = db.rawQuery("SELECT _id,portada,titulo FROM peliculas;", null);
        if (miCursor.moveToFirst()) {
            do {
                int id = miCursor.getInt(0);
                int portada = miCursor.getInt(1);
                String titulo = miCursor.getString(2);
                datos.add(new Datos(id, portada, titulo));
            } while (miCursor.moveToNext());
            miCursor.close();
        }
        tendencias.setAdapter(adaptador);
    }

    private void cargarMiLista(SQLiteDatabase db, ListView miLista) {
        ArrayList<Datos> datos = new ArrayList<>();
        Adaptador adaptador = new Adaptador(MainActivity.this, datos);
        Cursor miCursor = db.rawQuery("SELECT _id,portada,titulo FROM peliculas WHERE lista=1;", null);
        if (miCursor.moveToFirst()) {
            do {
                int id = miCursor.getInt(0);
                int portada = miCursor.getInt(1);
                String titulo = miCursor.getString(2);
                datos.add(new Datos(id, portada, titulo));
            } while (miCursor.moveToNext());
            miCursor.close();
        }
        miLista.setAdapter(adaptador);
    }

}