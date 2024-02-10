package com.example.t7examen2;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

public class ProveedorContenido extends android.content.ContentProvider {

    private static final String AUTHORITY = "com.example.t7examen2";
    private static final String uri = "content://" + AUTHORITY + "/contactos";
    public static final Uri CONTENT_URI = Uri.parse(uri);

    private static final int CONTACTOS = 1;
    private static final int CONTACTOS_ID = 2;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"contactos",CONTACTOS);
        uriMatcher.addURI(AUTHORITY,"CONTACTOS/#",CONTACTOS_ID);
    }

    public static final class Contactos implements BaseColumns{
        private Contactos(){}

        public static final String COL_NOMBRE = "nombre";
        public static final String COL_TELEFONO = "telefono";
        public static final String COL_AVATAR = "avatar";

    }

    private BD clidbh;
    private static final String BD_NOMBRE = "DBContactos";
    private static final int BD_VERSION = 2;
    private static final String TABLA_CONTACTOS = "contactos";

    @Override
    public boolean onCreate() {
        clidbh = new BD(getContext(),BD_NOMBRE, null, BD_VERSION);
        Log.i("BBDD","CReada");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String where = selection;
        if (uriMatcher.match(uri)==CONTACTOS_ID){
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidbh.getWritableDatabase();

        Cursor c = db.query(TABLA_CONTACTOS, projection, where, selectionArgs, null, null, sortOrder);

        return c;
    }

    @Override
    public String getType(Uri uri) {

        int match = uriMatcher.match(uri);

        switch (match){
            case CONTACTOS:
                return "vnd.android.cursor.dir/vnd.example.t7e8.contactos";
            case CONTACTOS_ID:
                return "vnd.android.cursor.item/vnd.example.t7e8.contactos";
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        long regId;

        SQLiteDatabase db = clidbh.getWritableDatabase();

        regId = db.insert(TABLA_CONTACTOS, null, contentValues);

        Uri newUri = ContentUris.withAppendedId(CONTENT_URI,regId);
        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int cont;

        String where = selection;
        if (uriMatcher.match(uri)==CONTACTOS_ID){
            where = "_id" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidbh.getWritableDatabase();

        cont = db.delete(TABLA_CONTACTOS,where,selectionArgs);

        return cont;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int cont;

        String where = selection;
        if (uriMatcher.match(uri)==CONTACTOS_ID){
            where = "_id" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidbh.getWritableDatabase();

        cont = db.update(TABLA_CONTACTOS,values,where,selectionArgs);

        return cont;
    }

    public ProveedorContenido(){}
}
