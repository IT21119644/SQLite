package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBudget extends AppCompatActivity {
    EditText BName, updateAmtField;
    String budName, category;
    Button datePickerBtn;
    DBHelper DB = new DBHelper(this);
    DatePicker dp = new DatePicker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_budget);
        updateAmtField = findViewById(R.id.updateBudAmt);

        Intent i = getIntent();
        budName = i.getStringExtra("BName");
        category = i.getStringExtra("category");
        BName = findViewById(R.id.BName);
        BName.setText(budName);

        datePickerBtn = findViewById(R.id.datePickerBtn);
        datePickerBtn.setText(dp.getTodaysDate());
        dp.initDatePicker(datePickerBtn, this);
    }

    public void openDatePickerMethod(View view)
    {
        dp.openDatePicker();
    }

    public void onClickSave(View v){
        String budgetName = BName.getText().toString();
        float budgetAmt = Float.parseFloat(updateAmtField.getText().toString());
        String newCompDate = datePickerBtn.getText().toString();

        boolean checkUpdatedData = DB.updateBudget(category, budgetAmt, newCompDate, budgetName);
        if(checkUpdatedData){
            Toast.makeText(UpdateBudget.this, "Budget updated", Toast.LENGTH_LONG).show();

            Intent switchActivityIntent = new Intent(this, MainActivity.class);
            startActivity(switchActivityIntent);
        }
        else
            Toast.makeText(UpdateBudget.this, "Entry not updated", Toast.LENGTH_LONG).show();
    }

}