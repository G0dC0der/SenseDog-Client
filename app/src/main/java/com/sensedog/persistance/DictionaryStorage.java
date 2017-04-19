package com.sensedog.persistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DictionaryStorage extends SQLiteOpenHelper {

    public DictionaryStorage(Context context) {
        super(context, "sensedog_db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE dictionary (" +
                    "key TEXT PRIMARY KEY NOT NULL," +
                    "value TEXT" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void put(String key, String value) {
        remove(key);

        ContentValues values = new ContentValues();
        values.put("key", key);
        values.put("value", value);

        getWritableDatabase().insert("dictionary", null, values);
    }

    public String get(String key) {
        Cursor result = null;
        try {
            result = getReadableDatabase().rawQuery("SELECT value FROM dictionary WHERE key = ? ", new String[]{key});
            if (result.getCount() > 0) {
                result.moveToFirst();
                return result.getString(result.getColumnIndex("value"));
            }
            return null;
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    public void remove(String key) {
        getWritableDatabase().execSQL(String.format("DELETE FROM dictionary WHERE key = '%S'", key));
    }
}