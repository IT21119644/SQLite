package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateBudgetUI extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinner;
    EditText BName, BDate, BAmount;
    TextView Bcategory;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch almost, overspent;
    DBHelper DB;
    String spinnerChoice;
    Boolean almostIsSelected, overspentIsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget_ui);

        BName = findViewById(R.id.budget_name);
        BDate = findViewById(R.id.budget_time);
        BAmount = findViewById(R.id.budget_amount);
        Bcategory = findViewById(R.id.categoryTxtV);

        //Error with toggle switches
        almost = findViewById(R.id.almost);
        almostIsSelected = almost.isSelected();

        overspent = findViewById(R.id.overspent);
        overspentIsSelected = overspent.isSelected();

        //dropdown menu
        spinner = findViewById(R.id.currency);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //create a DBHelper object
        DB = new DBHelper(this);
    }

    public void switchToMainActivity(View v){
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void switchToCategoryPage(View v){
        //Move to shali's page
//        Intent switchActivityIntent = new Intent(this, MainActivity.class);
//        startActivity(switchActivityIntent);
    }

    public void insertToDB(View v){
        String name = BName.getText().toString();
        String date = BDate.getText().toString();
        String amountStr = BAmount.getText().toString();
        float amount = Float.parseFloat(amountStr);
        String category = Bcategory.getText().toString();

        int almost, overspent;
        if(almostIsSelected)
            almost = 0;
        else
            almost = 1;

        if(overspentIsSelected)
            overspent = 0;
        else
            overspent = 1;

        boolean checkInsertData = DB.insertBudgetData(name, date, amount, spinnerChoice, category, almost, overspent);
        if(checkInsertData){
            Toast.makeText(CreateBudgetUI.this, "New Budget inserted", Toast.LENGTH_LONG).show();
            BName.setText(null);
            BDate.setText(null);
            BAmount.setText(null);
            Bcategory.setText("None");
        }
        else
            Toast.makeText(CreateBudgetUI.this, "New entry not inserted", Toast.LENGTH_LONG).show();
    }

    //generated methods for spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerChoice = adapterView.getItemAtPosition(i).toString();
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
        Toast.makeText(getApplicationContext(), spinnerChoice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    public void getDataFromDB(View v){
//        Cursor res = DB.getBudgetData();
//        ArrayList<String> myArrList = new ArrayList<>();
//        Intent i = new Intent(this, MainActivity.class);
//        if(res.getCount() == 0){
//            Toast.makeText(CreateBudgetUI.this, "No entry exists", Toast.LENGTH_SHORT).show();
//            i.putExtra("EmptyMsg", "Your budget is empty");
//            startActivity(i);
//            return;
//        }
//
//        while(res.moveToNext()){
////            buffer.append("BudgetName " + res.getString(0) + "\n");
////            buffer.append("Amount: " + res.getFloat(2) + "\n\n");
//            myArrList.add(res.getString(0));
//        }
//
//        i.putStringArrayListExtra("COOL", myArrList);
//        startActivity(i);
//    }
}