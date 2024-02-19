package com.example.ejercicio_09.cp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ejercicio_09.db.VersionesDB;

public class VersionesProvider extends ContentProvider {
    // Definición de la URI
    private static final String AUTHORITY = "com.example.ejercicio_09.cp";
    private static final String URI = "content://" + AUTHORITY + "/versiones";
    public static final Uri CONTENT_URI = Uri.parse(URI);

    // Definimos el objeto UriMAtcher
    private static final int VERSIONES = 1; // Acceso genérico a toda la tabla
    private static final int VERSIONES_ID = 2; // Acceso a una fila (acceso por ID)
    public static final UriMatcher URI_MATCHER; // Objeto UriMatcher

    // Inicializamos el UriMatcher
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, "versiones", VERSIONES);
        URI_MATCHER.addURI(AUTHORITY, "versiones/#", VERSIONES_ID);
    }

    // Clase interna para declarar las constantes de columna
    public static final class Versiones implements BaseColumns {
        private Versiones() {
        }

        // Nombre de columnas
        public static final String COL_ID = "_id";
        public static final String COL_NOMBRE = "nombre";
        public static final String COL_YEAR = "year";
    }

    // Base de datos
    public VersionesDB clidb;
    public static final String BD_NOMBRE = "VersionesDB";
    public static final int BD_VERSION = 1;
    public static final String TABLA_VERSIONES = "Versiones";

    @Override
    public boolean onCreate() {
        clidb = new VersionesDB(getContext(), BD_NOMBRE, null, BD_VERSION);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // Si es una consulta a un ID concreto, tenemos que construir el WHERE
        String where = selection;
        if (URI_MATCHER.match(uri) == VERSIONES_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidb.getWritableDatabase();
        Cursor cursor = db.query(TABLA_VERSIONES, projection, where, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);
        String type = null;
        switch (match) {
            case VERSIONES:
                // La estructura del final de este String es: el paquete con el nombre de la tabla
                type = "vnd.android.cursor.dir/vnd.com.example.ejercicio_09.db";
            case VERSIONES_ID:
                type = "vnd.android.cursor.item/vnd.com.example.ejercicio_09.db";
        }
        return type;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long regId;
        SQLiteDatabase db = clidb.getWritableDatabase();
        regId = db.insert(TABLA_VERSIONES, null, values);
        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);
        return newUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int cont;
        // Si es una consulta a un ID concreto contruimos el WHERE
        String where = selection;
        if (URI_MATCHER.match(uri) == VERSIONES_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidb.getWritableDatabase();
        cont = db.delete(TABLA_VERSIONES, where, selectionArgs);
        return cont;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int cont;
        // Si es una consulta a un ID concreto contruimos el WHERE
        String where = selection;
        if (URI_MATCHER.match(uri) == VERSIONES_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidb.getWritableDatabase();
        cont = db.update(TABLA_VERSIONES, values, where, selectionArgs);
        return cont;
    }

    public VersionesProvider() {
    }
}
