package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Modify_Income extends AppCompatActivity {
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_income);
        LinearLayout yourlayout= (LinearLayout) findViewById(R.id.incLayout);

        DB = new DBHelper(this);
        Cursor res = DB.getIncomeData();
        ArrayList<String> incCat = new ArrayList<>();
//        ArrayList<String> IncNames = new ArrayList<>();

        if(res.getCount() == 0){
            Toast.makeText(Modify_Income.this, "Your income is empty", Toast.LENGTH_SHORT).show();
        }

        while(res.moveToNext()){
            incCat.add(res.getString(1));
//            categories.add(res.getString(4));
        }
        if(!incCat.isEmpty()){
            for (int j = 0; j < incCat.size(); j++){
                Button btn = new Button (Modify_Income.this);
                btn.setWidth(5);
                btn.setHeight(20);
                btn.setTextSize(25);
                btn.setPadding(20, 0, 0, 0);

                btn.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

                btn.setTextColor(Color.parseColor("#000000"));
                btn.setBackgroundColor(Color.parseColor("#42E375"));

                //Get the width of the screen
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;

                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                //calculate and set the button width
                int buttonWidth = (int) (width / 1.5);
                buttonLayoutParams.width = buttonWidth;

                if(j == 0)
                    buttonLayoutParams.setMargins(80, 500, 80, 0);
                else
                    buttonLayoutParams.setMargins(80, 20, 80, 0);

                btn.setLayoutParams(buttonLayoutParams);

                //Set button text
                String head = incCat.get(j);
                btn.setText(head);
                yourlayout.addView(btn);

//                    btn.setOnClickListener(handleOnClick(btn, head, category));
            }
        }
    }

}