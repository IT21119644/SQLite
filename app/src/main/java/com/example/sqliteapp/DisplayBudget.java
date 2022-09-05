package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    TextView Bheading, amt, dt, curr, cat, stDate, currBal;
    DBHelper DB;
    String textVal, date, currency, category, startDate, currBalance;
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
        cat = findViewById(R.id.categoryView);
        stDate = findViewById(R.id.startDate);
        currBal = findViewById(R.id.currBalance);

        DB = new DBHelper(this);

        getSingleBudgetData();
    }

    public void deleteFromDB(View v){
        //display confirmation pop-up before deletion
        new AlertDialog.Builder(this)
                .setTitle("Delete Budget")
                .setMessage("Are you sure you want to delete this Budget?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        boolean deleteData = DB.deleteBudgetData(textVal);
                        if(deleteData){
                            Toast.makeText(DisplayBudget.this, "Budget deleted", Toast.LENGTH_LONG).show();
                            switchToMainAfterDeletion();
                        }

                        else
                            Toast.makeText(DisplayBudget.this, "Entry not deleted", Toast.LENGTH_LONG).show();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

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
            category = res.getString(4);
            startDate = res.getString(7);
            currBalance = res.getString(8);

            dt.setText(date);
            amt.setText(String.valueOf(amount));
            curr.setText(currency);
            cat.setText(category);
            stDate.setText(startDate);
            currBal.setText(currBalance);
        }
    }

    public void switchToUpdateBudget(View v){
        Intent switchActivityIntent = new Intent(this, UpdateBudget.class);
        switchActivityIntent.putExtra("BName", textVal);
        switchActivityIntent.putExtra("category", category);
        startActivity(switchActivityIntent);
    }
}