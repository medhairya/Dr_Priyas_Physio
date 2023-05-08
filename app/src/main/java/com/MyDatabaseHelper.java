package com;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Add Patient";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "patients";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_PHONE_NO = "phone_no";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_DIAGNOSIS = "diagnosis";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATE TABLE patients (id INTEGER PRIMARY KEY AUTOINCREMENT,
        //                       name TEXT, age INTEGER,gender TEXT, phone_no TEXT, address TEXT, diagnosis TEXT)
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, " + KEY_AGE + " INTEGER, " + KEY_GENDER + " TEXT, " + KEY_PHONE_NO + " TEXT, "
                + KEY_ADDRESS + " TEXT, " + KEY_DIAGNOSIS + " TEXT" + ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addPatient(String name, int age, String gender, String phone_no, String address){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_AGE, age);
        values.put(KEY_GENDER, gender);
        values.put(KEY_PHONE_NO, phone_no);
        values.put(KEY_ADDRESS, address);
        //values.put(KEY_DIAGNOSIS, diagnosis);

        db.insert(TABLE_NAME, null, values);
    }
}
