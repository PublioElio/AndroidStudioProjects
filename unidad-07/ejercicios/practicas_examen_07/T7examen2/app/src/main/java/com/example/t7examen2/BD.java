package com.example.t7examen2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BD extends SQLiteOpenHelper {

    // Sentencia SQL para crear la tabla de usuarios. Por simplicidad de las pruebas, no se ha
    // puesto clave primaria
    String sqlCreate = "CREATE TABLE contactos(_id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, telefono TEXT, avatar INTEGER)";

    public BD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Por simplicidad del ejemplo, simplemente elimino la tabla y vuelvo a crearla
        db.execSQL("DROP TABLE IF EXISTS versiones");
        db.execSQL(sqlCreate);
    }
}
