package com.example.unidad_07_apuntes_06;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Abrimos la base de datos en modo escritura
        // Es importante cambiar el número de la versión cada vez que modificamos la estructura de
        // la Base de Datos
        UsusariosBBDD usuarioBBDD = new UsusariosBBDD(this, "BDUsuarios", null, 1);
        SQLiteDatabase db = usuarioBBDD.getWritableDatabase();

        if (db != null) {
            // Insertar cinco usuarios
            String usuario;
            for (int i = 0; i < 5; i++) {
                usuario = "Usuariio" + i;
                db.execSQL("INSERT INTO Usuarios(codigo,nombre) VALUES (" + i + ", '" + usuario + "')");
            }
            db.close();
        }
    }
}