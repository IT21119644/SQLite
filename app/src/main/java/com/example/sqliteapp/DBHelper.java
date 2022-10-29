package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE UserDetails(ID TEXT PRIMARY KEY, name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE if exists UerDetails");
    }

    public boolean insertUserData(String ID, String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", ID);
        contentValues.put("name", name);

        long result = DB.insert("UserDetails", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateUserData(String ID, String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", ID);
        contentValues.put("name", name);

        Cursor cursor = DB.rawQuery("SELECT * FROM UserDetails WHERE id = ?", new String[] {ID});
        if(cursor.getCount() > 0){
            long result = DB.update("UserDetails", contentValues, "ID=?", new String[] {ID});
            if(result == -1)
                return false;
            else
                return true;
        }
        else{
            return false;
        }
    }

    public boolean deleteUserData(String ID){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM UserDetails WHERE ID = ?", new String[] {ID});
        if(cursor.getCount() > 0){
            long result = DB.delete("UserDetails","ID=?", new String[] {ID});
            if(result == -1)
                return false;
            else
                return true;
        }
        else{
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM UserDetails", null);
        return cursor;
    }

    public Cursor getBudgetData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM BudgetDetails", null);
        return cursor;
    }

    public Cursor getSingleBudgetData(String category){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM BudgetDetails WHERE Category = ?", new String[] {category});
        return cursor;
    }

    public Cursor getSingleBudgetDataUsingCategory(String category){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM BudgetDetails WHERE Category = ?", new String[] {category});
        return cursor;
    }

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
