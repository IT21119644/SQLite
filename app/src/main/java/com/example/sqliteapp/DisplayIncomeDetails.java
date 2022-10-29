package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;

public class DisplayIncomeDetails extends AppCompatActivity {
    DBHelper DB;
    int idInt;
    String category, date;
    float amount;
    TextView cat, dat, amt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_income_details);
        Intent i = getIntent();
        String textVal = i.getStringExtra("BName");
        String id = i.getStringExtra("incID");
        idInt = Integer.parseInt(id);

        TextView t = findViewById(R.id.head);
        String disp = textVal+": " + id;
        t.setText(disp);

        cat = findViewById(R.id.catTxtV);
        dat = findViewById(R.id.dateTxtV);
        amt = findViewById(R.id.amtTxtV);

        DB = new DBHelper(this);

        try {
            getSingleIncomeData(idInt);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void getSingleIncomeData(int id){
        Cursor res = DB.getSingleIncomeDataUsingIncomeID(id);

        while(res.moveToNext()){
            category = res.getString(1);
            date = res.getString(2);
            amount = res.getFloat(3);

            cat.setText(category);
            dat.setText(date);
            amt.setText(String.valueOf(amount));
        }
    }
}