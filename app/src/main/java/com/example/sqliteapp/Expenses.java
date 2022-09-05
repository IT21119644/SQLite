package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Expenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        TextView categoryTxtv = findViewById(R.id.categoryTxtV2);

        Intent i = getIntent();
        String cat = i.getStringExtra("category");
        if(cat != null){
            categoryTxtv.setText(cat);
        }
    }

    public void switchToCategoryPage(View v){
        //Move to shali's page
        Intent switchActivityIntent = new Intent(this, Category.class);
        switchActivityIntent.putExtra("fromPage", this.getClass().getSimpleName());
        startActivity(switchActivityIntent);
    }

    public void switchToMainActivity(View v){
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}