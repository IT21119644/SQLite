package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "GoalData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE GoalData(name TEXT PRIMARY KEY, estimated_date TEXT, gaol_amount REAL, category TEXT, goal_description TEXT, add_savings REAL, today_date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE if exists GoalData");
    }

    public boolean insertGoalData(String name, String estimated_date, float gaol_amount, String category, String goal_description, float add_savings, String today_date ){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("estimated_date", estimated_date);
        contentValues.put("gaol_amount", gaol_amount);
        contentValues.put("category", category);
        contentValues.put("goal_description", goal_description);
        contentValues.put("add_savings", add_savings);
        contentValues.put("today_date", today_date);


        long result = DB.insert("GoalData", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateGoalData(String name, float gaol_amount, String goal_description, float add_savings ){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("estimated_date", estimated_date);
        contentValues.put("gaol_amount", gaol_amount);
//        contentValues.put("category", category);
        contentValues.put("goal_description", goal_description);
        contentValues.put("add_savings", add_savings);
        Cursor cursor = DB.rawQuery("Select * from GoalData where name = ?", new String[]{name});
        if(cursor.getCount()>0) {
            long result = DB.update("GoalData", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
             return false;
        }


    }

    public boolean deleteGoalData(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from GoalData where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("GoalData", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM GoalData", null);
        return cursor;
    }

    public Cursor getSingleGoalData(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM GoalData WHERE name = ?", new String[] {name});
        return cursor;
    }
}
