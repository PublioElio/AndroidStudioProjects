package com.example.unidad_07_apuntes08;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClientesBBDD extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Clientes(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, " +
            "telefono TEXT, email TEXT)";

    public ClientesBBDD(@Nullable Context context,
                        @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos la tabla
        db.execSQL(sqlCreate);
        // Por simplicidad del ejemplo, insertamos directamente clientes
        for(int i = 1; i<10; i++){
            // Genero los datos
            String nombre = "Cliente " + i;
            String tlfn = "900-100-00" + i;
            String mail = "email" + i + "@gmail.com";

            // Isertamos los datos en la tabla cliente
            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            registro.put("telefono", tlfn);
            registro.put("email", mail);
            db.insert("Clientes",
                    null,
                    registro);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Clientes");
        db.execSQL(sqlCreate);
    }
}
