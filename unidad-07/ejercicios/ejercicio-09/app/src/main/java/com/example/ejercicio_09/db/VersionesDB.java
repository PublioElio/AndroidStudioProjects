package com.example.ejercicio_09.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VersionesDB extends SQLiteOpenHelper {
    String sqlCreate =
            "CREATE TABLE versiones(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "year INTEGER)";
    public VersionesDB(Context context, String nombre,
                       SQLiteDatabase.CursorFactory factory, int version){
        super(context, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS versiones");
        db.execSQL(sqlCreate);
    }
}
