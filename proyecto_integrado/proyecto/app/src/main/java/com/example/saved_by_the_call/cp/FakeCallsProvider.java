package com.example.saved_by_the_call.cp;

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

import com.example.saved_by_the_call.db.FakeCallsDB;

public class FakeCallsProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.saved_by_the_call.cp";
    private static final String URI_CONTACTS = "content://" + AUTHORITY + "/contacts";
    private static final String URI_CALLS = "content://" + AUTHORITY + "/calls";
    public static final Uri CONTENT_URI_CONTACTS = Uri.parse(URI_CONTACTS);
    public static final Uri CONTENT_URI_CALLS = Uri.parse(URI_CALLS);

    private static final int CONTACTS = 1;
    private static final int CONTACTS_ID = 2;
    private static final int CALLS = 3;
    private static final int CALLS_ID = 4;

    public static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, "contacts", CONTACTS);
        URI_MATCHER.addURI(AUTHORITY, "contacts/#", CONTACTS_ID);
        URI_MATCHER.addURI(AUTHORITY, "calls", CALLS);
        URI_MATCHER.addURI(AUTHORITY, "calls/#", CALLS_ID);
    }

    public static final class Contacts implements BaseColumns {
        private Contacts() {
        }

        public static final String COL_ID = "_id_contact";
        public static final String COL_NAME = "name";
        public static final String COL_PHONE = "phone";
        public static final String COL_IMG = "img";
    }

    public static final class Calls implements BaseColumns {
        private Calls() {
        }

        public static final String COL_ID = "_id_call";
        public static final String COL_NAME = "name";
        public static final String COL_CONTACT = "contact";
        public static final String COL_HOUR = "hour";
        public static final String COL_MIN = "min";
        public static final String COL_DATE = "date";
    }

    public FakeCallsDB clidb;
    public static final String BD_NAME = "FakeCallsDB";
    public static final int BD_FAKE_CALLS = 1;
    public static final String TABLE_CONTACTS = "Contacts";
    public static final String TABLE_CALLS = "Calls";

    private static final String MIME_TYPE_CONTACTS =
            "vnd.android.cursor.dir/vnd.com.example.saved_by_the_call.contacts";
    private static final String MIME_TYPE_CONTACT =
            "vnd.android.cursor.item/vnd.com.example.saved_by_the_call.contacts";
    private static final String MIME_TYPE_CALLS =
            "vnd.android.cursor.dir/vnd.com.example.saved_by_the_call.calls";
    private static final String MIME_TYPE_CALL =
            "vnd.android.cursor.item/vnd.com.example.saved_by_the_call.calls";

    public FakeCallsProvider() {
    }

    @Override
    public boolean onCreate() {
        clidb = new FakeCallsDB(getContext(), BD_NAME, null, BD_FAKE_CALLS);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = clidb.getWritableDatabase();
        Cursor cursor;
        int match = URI_MATCHER.match(uri);

        switch (match) {
            case CONTACTS:
                cursor = db.query(TABLE_CONTACTS, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CONTACTS_ID:
                String contactsId = "_id_contact=" + uri.getLastPathSegment();
                cursor = db.query(TABLE_CONTACTS, projection, contactsId, selectionArgs, null, null, sortOrder);
                break;
            case CALLS:
                cursor = db.query(TABLE_CALLS, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CALLS_ID:
                String callsId = "_id_call=" + uri.getLastPathSegment();
                cursor = db.query(TABLE_CALLS, projection, callsId, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case CONTACTS:
                return MIME_TYPE_CONTACTS;
            case CONTACTS_ID:
                return MIME_TYPE_CONTACT;
            case CALLS:
                return MIME_TYPE_CALLS;
            case CALLS_ID:
                return MIME_TYPE_CALL;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long regId;
        SQLiteDatabase db = clidb.getWritableDatabase();
        Uri resultUri;

        switch (URI_MATCHER.match(uri)) {
            case CONTACTS:
                regId = db.insert(TABLE_CONTACTS, null, values);
                resultUri = ContentUris.withAppendedId(CONTENT_URI_CONTACTS, regId);
                break;
            case CALLS:
                regId = db.insert(TABLE_CALLS, null, values);
                resultUri = ContentUris.withAppendedId(CONTENT_URI_CALLS, regId);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int count;
        SQLiteDatabase db = clidb.getWritableDatabase();
        int match = URI_MATCHER.match(uri);

        switch (match) {
            case CONTACTS_ID:
                String contactsWhere = "_id_contact=" + uri.getLastPathSegment();
                count = db.delete(TABLE_CONTACTS, contactsWhere, selectionArgs);
                break;
            case CALLS_ID:
                String callsWhere = "_id_call=" + uri.getLastPathSegment();
                count = db.delete(TABLE_CALLS, callsWhere, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int count;
        SQLiteDatabase db = clidb.getWritableDatabase();
        int match = URI_MATCHER.match(uri);

        switch (match) {
            case CONTACTS_ID:
                String contactsWhere = "_id_contact=" + uri.getLastPathSegment();
                count = db.update(TABLE_CONTACTS, values, contactsWhere, selectionArgs);
                break;
            case CALLS_ID:
                String callsWhere = "_id_call=" + uri.getLastPathSegment();
                count = db.update(TABLE_CALLS, values, callsWhere, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return count;
    }
}
