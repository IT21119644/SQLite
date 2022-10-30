package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class Category extends AppCompatActivity {
    String previousPage;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        DB = new DBHelper(this);

        TextView fromPage = findViewById(R.id.fromPage);
        Intent i = getIntent();

        previousPage = i.getStringExtra("fromPage");
        fromPage.setText(previousPage);

        LinearLayout yourlayout= (LinearLayout) findViewById(R.id.yourlayout2);

        ArrayList<String> cat = new ArrayList<>();
        cat.add("Shopping");
        cat.add("Food and drinks");
        cat.add("Entertainment");
        cat.add("Health");
        cat.add("Transport");
        cat.add("Housing");
        cat.add("Communication");
        cat.add("Investments");


        Log.d("val", cat.get(0));
        for (int j = 0; j < cat.size(); j++){
            Button btn = new Button (Category.this);
            btn.setWidth(5);
            btn.setHeight(20);
            btn.setTextSize(25);
            btn.setPadding(20, 0, 0, 0);
            btn.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            btn.setBackgroundColor(Color.parseColor("#fb8d19"));
            btn.setTextColor(Color.parseColor("#000000"));

            //Get the width of the screen
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;

            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //calculate and set the button width
            int buttonWidth = (int) (width / 1.5);
            buttonLayoutParams.width = buttonWidth;

            if(j == 0)
                buttonLayoutParams.setMargins(80, 300, 80, 0);
            else
                buttonLayoutParams.setMargins(80, 20, 80, 0);

            btn.setLayoutParams(buttonLayoutParams);

            //Set button text
            String head = cat.get(j);
            btn.setText(head);
            yourlayout.addView(btn);

            if(previousPage.equals("CreateBudgetUI")){
                Cursor res = DB.getBudgetData();
                while(res.moveToNext()){
                    String category = res.getString(4);
                    if(btn.getText().toString().equals(category)){
                        btn.setEnabled(false);
                        btn.setBackgroundColor(Color.parseColor("#d3d3d3"));
                    }
                }
            }

            btn.setOnClickListener(handleOnClick(btn, head));
        }
    }


    View.OnClickListener handleOnClick(final Button button, String heading) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("className", Category.this.getClass().getSimpleName());
                switchToCreateBudget(heading);
            }
        };
    }
    public void switchToCreateBudget(String category){
        Intent switchActivityIntent;
//        Intent switchActivityIntent = new Intent(this, CreateBudgetUI.class);
        if(previousPage.equals("CreateBudgetUI")){
            switchActivityIntent = new Intent(this, CreateBudgetUI.class);
            switchActivityIntent.putExtra("category", category);
            startActivity(switchActivityIntent);
        }
        else if(previousPage.equals("Expenses")){
            switchActivityIntent = new Intent(this, Expenses.class);
            switchActivityIntent.putExtra("category", category);
            startActivity(switchActivityIntent);
        }
    }
}