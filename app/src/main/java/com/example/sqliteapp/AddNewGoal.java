package com.example.sqliteapp;
//My name is Yohan
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class AddNewGoal extends AppCompatActivity {
    EditText name, goalAmount, goalDescription, addSavings;
    DatePickerDialog datePickerDialog;
    Button create, estimatedDate, category;
    DBHelper DB;

    //@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_goal);



        name = findViewById(R.id.name);
        goalAmount = findViewById(R.id.goalAmount);
        goalDescription = findViewById(R.id.goalDescription);
        initDatePicker();
        estimatedDate = findViewById(R.id.estimatedDate);
        estimatedDate.setText(getTodaysDate());
        addSavings = findViewById(R.id.addSavings);
        category = findViewById(R.id.category);

        create = findViewById(R.id.create);
        DB = new DBHelper(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameSrc = name.getText().toString();
                float goalAmountSrc = goalAmount.getAlpha();
                String goalDescriptionSrc = goalDescription.getText().toString();
                String estimatedDateSrc = estimatedDate.getText().toString();
                String categorySrc = category.getText().toString();
                float addSavingsSrc = addSavings.getAlpha();

                Boolean cheackinsertdata = DB.insertGoalData(nameSrc, estimatedDateSrc, goalAmountSrc, categorySrc, goalDescriptionSrc, addSavingsSrc);
                if(cheackinsertdata==true){
                    Toast.makeText(AddNewGoal.this, "New Entry inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddNewGoal.this, "New Entry Not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                estimatedDate.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListner, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return day + " " + getMonthFormat(month) + " " + year;
    }


    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        return "JAN";
    }

    public void addNewGoal(View v) {


        //Toast.makeText(this, nameSrc, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, estimatedDateSrc, Toast.LENGTH_LONG).show();

    }

    public void addDatePop(View v) {

        datePickerDialog.show();

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
