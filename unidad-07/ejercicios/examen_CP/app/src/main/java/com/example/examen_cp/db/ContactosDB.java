package com.example.examen_cp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactosDB extends SQLiteOpenHelper {
    String sqlCreate =
            "CREATE TABLE contactos(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "telefono TEXT, " +
                    "avatar INTEGER)";
    public ContactosDB(Context context, String nombre,
                       SQLiteDatabase.CursorFactory factory, int version){
        super(context, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contactos");
        db.execSQL(sqlCreate);
    }
}
