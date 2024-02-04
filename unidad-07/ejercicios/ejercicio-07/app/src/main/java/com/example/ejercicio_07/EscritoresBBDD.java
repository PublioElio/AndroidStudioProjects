package com.example.ejercicio_07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EscritoresBBDD extends SQLiteOpenHelper {


    // Sentencias para crear la tabla usuarios
    String sqlCreate = "CREATE TABLE Escritores(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, apellidos TEXT)";

    public EscritoresBBDD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Escritores");
        db.execSQL(sqlCreate);
    }

}
