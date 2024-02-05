package com.example.ejercicio_08;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DibujantesBBDD extends SQLiteOpenHelper {

    // Sentencias para crear la tabla dibujantes
    String sqlCreate = "CREATE TABLE Dibujantes(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, apellidos TEXT)";

    public DibujantesBBDD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Dibujantes");
        db.execSQL(sqlCreate);
    }

}



