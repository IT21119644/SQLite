package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Wallet.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE ExpenseDetails(expenseID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, category TEXT, date TEXT, amount REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE if exists ExpenseDetails");
    }

    public boolean insertExpenseData(String category, String date, float amount){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", category);
        contentValues.put("date", date);
        contentValues.put("amount", amount);

        long result = DB.insert("ExpenseDetails", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }


//        public boolean updateExpense(String itemID, String category, String date, float amount){
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
    public boolean deleteExpenseData(int itemID){
        SQLiteDatabase DB = this.getWritableDatabase();

        String query  = "SELECT * FROM ExpenseDetails WHERE expenseID = " + itemID;
        Cursor cursor = DB.rawQuery(query, null);
        if(cursor.getCount() > 0){
            long result = DB.delete("ExpenseDetails","expenseID= "+itemID, null);
            if(result == -1)
                return false;
            else
                return true;
        }
        else{
            return false;
        }
    }

    public Cursor getExpenseData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM ExpenseDetails", null);
        return cursor;
    }

    public Cursor getSingleExpenseDataUsingIncomeID(int id){
        SQLiteDatabase DB = this.getWritableDatabase();
        String query  = "SELECT * FROM ExpenseDetails WHERE expenseID = " + id;
        Cursor cursor = DB.rawQuery(query, null);
        return cursor;
    }
}
