package com.example.examen_cp.cp;

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

import com.example.examen_cp.db.ContactosDB;

public class ContactosProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.examen_cp.cp";
    private static final String URI = "content://" + AUTHORITY + "/contactos";
    public static final Uri CONTENT_URI = Uri.parse(URI);
    private static final int CONTACTOS = 1;
    private static final int CONTACTOS_ID = 2;
    public static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, "contactos", CONTACTOS);
        URI_MATCHER.addURI(AUTHORITY, "contactos/#", CONTACTOS_ID);
    }
    public static final class Contactos implements BaseColumns {
        private Contactos() {
        }
        public static final String COL_ID = "_id";
        public static final String COL_NOMBRE = "nombre";
        public static final String COL_TELEFONO = "telefono";
        public static final String COL_AVATAR = "avatar";
    }

    public ContactosDB clidb;
    public static final String BD_NOMBRE = "ContactosDB";
    public static final int BD_CONTACTOS = 1;
    public static final String TABLA_CONTACTOS = "Contactos";

    @Override
    public boolean onCreate() {
        clidb = new ContactosDB(getContext(), BD_NOMBRE, null, BD_CONTACTOS);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // Si es una consulta a un ID concreto, tenemos que construir el WHERE
        String where = selection;
        if (URI_MATCHER.match(uri) == CONTACTOS_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidb.getWritableDatabase();
        return db.query(TABLA_CONTACTOS, projection, where, selectionArgs, null,
                null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);
        String type = null;
        switch (match) {
            case CONTACTOS:
                type = "vnd.android.cursor.dir/vnd.com.example.examen_cp.db";
                break;
            case CONTACTOS_ID:
                type = "vnd.android.cursor.item/vnd.com.example.examen_cp.db";
                break;
        }
        return type;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long regId;
        SQLiteDatabase db = clidb.getWritableDatabase();
        regId = db.insert(TABLA_CONTACTOS, null, values);
        return ContentUris.withAppendedId(CONTENT_URI, regId);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int cont;
        String where = selection;
        if (URI_MATCHER.match(uri) == CONTACTOS_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidb.getWritableDatabase();
        cont = db.delete(TABLA_CONTACTOS, where, selectionArgs);
        return cont;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int cont;
        String where = selection;
        if (URI_MATCHER.match(uri) == CONTACTOS_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidb.getWritableDatabase();
        cont = db.update(TABLA_CONTACTOS, values, where, selectionArgs);
        return cont;
    }

    public ContactosProvider() {
    }
}
