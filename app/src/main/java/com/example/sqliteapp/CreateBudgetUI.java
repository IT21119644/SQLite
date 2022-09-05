package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CreateBudgetUI extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    EditText BName, BAmount;
    TextView Bcategory;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch almost, overspent;
    DBHelper DB;
    Boolean almostIsSelected, overspentIsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget_ui);

        initDatePicker();
        dateButton = findViewById(R.id.datePickerBtn);
        dateButton.setText(getTodaysDate());

        BName = findViewById(R.id.budget_name);
        BAmount = findViewById(R.id.budget_amount);
        Bcategory = findViewById(R.id.categoryTxtV);

        //Error with toggle switches
        almost = findViewById(R.id.almost);
        almostIsSelected = almost.isSelected();

        overspent = findViewById(R.id.overspent);
        overspentIsSelected = overspent.isSelected();

        //dropdown menu
//        spinner = findViewById(R.id.currency);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);

        //create a DBHelper object
        DB = new DBHelper(this);

        Intent i = getIntent();
        String cat = i.getStringExtra("category");
        if(cat != null){
            Bcategory.setText(cat);
        }
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1; //since month starts from 0
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()); //set the Min date to current date

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void switchToMainActivity(View v){
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void switchToCategoryPage(View v){
        //Move to shali's page
        Intent switchActivityIntent = new Intent(this, Category.class);
//        Log.d("className", );
        switchActivityIntent.putExtra("fromPage", this.getClass().getSimpleName());
        startActivity(switchActivityIntent);
    }

    public void insertToDB(View v){
        String name = BName.getText().toString();
        String date = dateButton.getText().toString();
        String amountStr = BAmount.getText().toString();
        float amount = Float.parseFloat(amountStr);
        String category = Bcategory.getText().toString();
        String todayDate = getTodaysDate();
        float currentAmt = 0;

        int almost, overspent;
        if(almostIsSelected)
            almost = 0;
        else
            almost = 1;

        if(overspentIsSelected)
            overspent = 0;
        else
            overspent = 1;


        boolean checkInsertData = DB.insertBudgetData(name, date, amount, "LKR", category, almost, overspent, todayDate, currentAmt);
        if(checkInsertData){
            Toast.makeText(CreateBudgetUI.this, "New Budget inserted", Toast.LENGTH_LONG).show();
            BName.setText(null);
            BAmount.setText(null);
            Bcategory.setText("None");
        }
        else
            Toast.makeText(CreateBudgetUI.this, "New entry not inserted", Toast.LENGTH_LONG).show();
    }

    //generated methods for spinner
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        spinnerChoice = adapterView.getItemAtPosition(i).toString();
//        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
//        Toast.makeText(getApplicationContext(), spinnerChoice, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }

}