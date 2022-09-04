package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayBudget extends AppCompatActivity {
    Button deleteBudget;
    TextView Bheading, amt, dt, curr;
    DBHelper DB;
    String textVal, date, currency;
    float amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_budget);

        Intent i = getIntent();
        textVal = i.getStringExtra("Bow");

        deleteBudget = findViewById(R.id.deleteBudgetBtn);
        Bheading = findViewById(R.id.heading);
        Bheading.setText(textVal);

        amt = findViewById(R.id.amount);
        dt = findViewById(R.id.date);
        curr = findViewById(R.id.curr);

        DB = new DBHelper(this);

        getSingleBudgetData();
    }

    public void deleteFromDB(View v){
        boolean deleteData = DB.deleteBudgetData(textVal);
        if(deleteData){
            Toast.makeText(DisplayBudget.this, "Budget deleted", Toast.LENGTH_LONG).show();
            switchToMainAfterDeletion();
        }

        else
            Toast.makeText(DisplayBudget.this, "Entry not deleted", Toast.LENGTH_LONG).show();
    }

    public void switchToMainAfterDeletion(){
        //I think this is redundant. You can normally switch to main activity without using intents.

        Cursor res = DB.getBudgetData();
        ArrayList<String> myArrList = new ArrayList<>();
        Intent i = new Intent(this, MainActivity.class);
        if(res.getCount() == 0){
            Toast.makeText(DisplayBudget.this, "No entry exists", Toast.LENGTH_SHORT).show();
            i.putExtra("EmptyMsg", "Your budget is empty");
            startActivity(i);
            return;
        }

        while(res.moveToNext()){
//            buffer.append("BudgetName " + res.getString(0) + "\n");
//            buffer.append("Amount: " + res.getFloat(2) + "\n\n");
            myArrList.add(res.getString(0));
        }

        i.putStringArrayListExtra("COOL", myArrList);
        startActivity(i);
    }

    public void switchToMainActivity(View v){
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void getSingleBudgetData(){
        Cursor res = DB.getSingleBudgetData(textVal);
        while(res.moveToNext()){
//            buffer.append("BudgetName " + res.getString(0) + "\n");
//            buffer.append("Amount: " + res.getFloat(2) + "\n\n");
            date = res.getString(1);
            amount = res.getFloat(2);
            currency = res.getString(3);

            dt.setText(date);
            amt.setText(String.valueOf(amount));
            curr.setText(currency);
        }
    }
}