package com.example.sqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GoalHome extends AppCompatActivity {
    DBHelper DB;
    String textVal;
    private Button addNewGoal, newbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goal_home);

//        Button btn = (Button)findViewById(R.id.btn1);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addButton();
//            }
//        });

        addNewGoal = findViewById(R.id.addNewGoal);
        //Intent i = getIntent();
        //textVal = i.getStringExtra("Bow");
    }

//    public void addButton() {
//        LinearLayout layout = (LinearLayout)  findViewById(R.id.rootlayout);
//        newbtn = new Button(this);
//        newbtn.setText("new Button");
//        layout.addView(newbtn);
//    }

    public void launchAddGoal(View v){
        Intent i = new Intent(this, AddNewGoal.class);
        startActivity(i);
    }
}
