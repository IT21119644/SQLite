package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {

        super(context, "BudgetDataNew.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE UserDetails(Name TEXT , Currency TEXT, PIN NUMERIC PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE if exists UerDetails");
    }

    public boolean insertUserData(String name, String currency, int PIN){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Currency", currency);
        contentValues.put("PIN", PIN);

        long result = DB.insert("UserDetails", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM UserDetails", null);
        return cursor;
    }
}
