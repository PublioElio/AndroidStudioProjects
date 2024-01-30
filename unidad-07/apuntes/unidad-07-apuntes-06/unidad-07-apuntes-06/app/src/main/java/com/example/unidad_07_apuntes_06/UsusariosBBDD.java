package com.example.unidad_07_apuntes_06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsusariosBBDD extends SQLiteOpenHelper {
    // Sentencia SQL para crear la tabla de usuarios
    String sqlCreate = "CREATE TABLE Usuarios (codigo INTEGER PRIMARY KEY, nombre TEXT)";

    public UsusariosBBDD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Se ejecuta la sentencia SQL de creación de tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // NOTA: por simplicidad del ejemplo utilizamos vamos a borrar y volver a crear directamente
        // las tablas para no tener que hacer copia de seguridad de la información y restablecerla

        // Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        // Se crea la nueva versión
        db.execSQL(sqlCreate);

    }

}
