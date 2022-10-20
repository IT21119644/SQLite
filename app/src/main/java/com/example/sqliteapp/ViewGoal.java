package com.example.sqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


    public class ViewGoal extends AppCompatActivity {
        Button delete;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view_goal);

            delete = findViewById(R.id.delete);
        }

//        public void launchUpdateGoal(View v){
//            Intent i = new Intent(this, UpdateGoal.class);
//            startActivity(i);
//        }
    }

