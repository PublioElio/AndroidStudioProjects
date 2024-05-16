package com.example.saved_by_the_call.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FakeCallsDB extends SQLiteOpenHelper {
    String sqlCreateContacts =
            "CREATE TABLE contacts(_id_contact INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "phone TEXT, " +
                    "img BLOB)";
    String sqlCreateCalls =
            "CREATE TABLE calls(_id_call INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "contact INTEGER, " +
                    "hour INTEGER, " +
                    "min INTEGER, " +
                    "date DATE)";
    String sqlCreateContactCalls =
            "CREATE TABLE contact_calls(" +
                    "_id_contact_call INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "contact_id INTEGER, " +
                    "call_id INTEGER, " +
                    "FOREIGN KEY (contact_id) REFERENCES contacts(_id_contact), " +
                    "FOREIGN KEY (call_id) REFERENCES calls(_id_call))";
    public FakeCallsDB(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateContacts);
        db.execSQL(sqlCreateCalls);
        db.execSQL(sqlCreateContactCalls);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE IF EXISTS calls");
        db.execSQL("DROP TABLE IF EXISTS contact_calls");
        db.execSQL(sqlCreateContacts);
        db.execSQL(sqlCreateCalls);
        db.execSQL(sqlCreateContactCalls);
    }
}
