package com.example.sqliteapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;


public class ViewGoal extends AppCompatActivity {
        Button delete, update;
        TextView goalDescription, goalHeading;
        String textVal, name, goalDesc;
        DBHelper DB;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view_goal);

            Intent i = getIntent();
            textVal = i.getStringExtra("Gname");

            update = findViewById(R.id.update);
            delete = findViewById(R.id.delete);

            goalDescription = findViewById(R.id.goalDesc);
            goalHeading = findViewById(R.id.GoalHeading);

            DB = new DBHelper(this);

            try {
                getSingleGoalData();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        public void launchUpdateGoal(View v){
            Intent i = new Intent(this, UpdateGoal.class);
            startActivity(i);
        }

        public void switchToMainActivity(View v){
            Intent switchActivityIntent = new Intent(this, GoalHome.class);
            startActivity(switchActivityIntent);
        }

        public void getSingleGoalData() throws ParseException {
            Cursor res = DB.getSingleGoalData(name);
            while(res.moveToNext()){
                goalDesc = res.getString(3);
                goalDescription.setText(goalDesc);
            }
        }




    }

