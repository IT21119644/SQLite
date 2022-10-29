package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Update_details extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
    }

    public void switchToCategory(View view){
        Intent switchActivityIntent = new Intent(this, Category.class);
        startActivity(switchActivityIntent);
    }
}