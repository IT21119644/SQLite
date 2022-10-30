package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UpdateExpenseDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_expense_details);
    }

    public void switchToCategory(View view){
        Intent switchActivityIntent = new Intent(this, ExpenseCategory.class);
        startActivity(switchActivityIntent);
    }
}