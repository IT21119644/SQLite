package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "BudgetData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE BudgetDetails(BudgetName TEXT PRIMARY KEY, Date TEXT, Amount NUMERIC, Currency TEXT, Category TEXT, almostComplete NUMERIC, overspent NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE if exists BudgetDetails");
    }

    public boolean insertBudgetData(String BudName, String date, float amount, String currency, String category, int almostComplete, int overspent){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BudgetName", BudName);
        contentValues.put("Date", date);
        contentValues.put("Amount", amount);
        contentValues.put("Currency", currency);
        contentValues.put("Category", category);
        contentValues.put("almostComplete", almostComplete);
        contentValues.put("overspent", overspent);


        long result = DB.insert("BudgetDetails", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getBudgetData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM BudgetDetails", null);
        return cursor;
    }
}
