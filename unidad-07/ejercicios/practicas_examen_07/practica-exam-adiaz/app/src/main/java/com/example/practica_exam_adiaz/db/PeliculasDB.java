package com.example.practica_exam_adiaz.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PeliculasDB extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE peliculas(_id INTEGER PRIMARY KEY AUTOINCREMENT,titulo TEXT, portada INTEGER, lista INTEGER)";

    public PeliculasDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS versiones");
        db.execSQL(sqlCreate);
    }
}
