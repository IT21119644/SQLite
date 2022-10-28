package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context, "Income.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE IncomeDetails(incomeID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, category TEXT, date TEXT, amount REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE if exists IncomeDetails");
    }

    public boolean insertIncomeData(String category, String date, float amount){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", category);
        contentValues.put("date", date);
        contentValues.put("amount", amount);

        long result = DB.insert("IncomeDetails", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }


//    public boolean updateUserData(String itemID, String category, String date, float amount){
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("ID", itemID);
//        contentValues.put("Category", category);
//        contentValues.put("Date", date);
//        contentValues.put("Amount", amount);
//
//        Cursor cursor = DB.rawQuery("SELECT * FROM IncomeDetails WHERE incomeID = ?", new String[] {itemID});
//        if(cursor.getCount() > 0){
//            long result = DB.update("IncomeDetails", contentValues, "ID=?", new String[] {itemID});
//            if(result == -1)
//                return false;
//            else
//                return true;
//        }
//        else{
//            return false;
//        }
//    }
//
//    public boolean deleteUserData(String itemID){
//        SQLiteDatabase DB = this.getWritableDatabase();
//
//            Cursor cursor = DB.rawQuery("SELECT * FROM IncomeDetails WHERE incomeID = ?", new String[] {itemID});
//        if(cursor.getCount() > 0){
//            long result = DB.delete("UserDetails","ID=?", new String[] {itemID});
//            if(result == -1)
//                return false;
//            else
//                return true;
//        }
//        else{
//            return false;
//        }
//    }

    public Cursor getIncomeData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM IncomeDetails", null);
        return cursor;
    }

    public Cursor getSingleIncomeDataUsingIncomeID(int id){
        SQLiteDatabase DB = this.getWritableDatabase();
        String query  = "SELECT * FROM IncomeDetails WHERE incomeID = " + id;
        Cursor cursor = DB.rawQuery(query, null);
        return cursor;
    }

}
