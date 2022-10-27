package com.example.sqliteapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class UpdateGoal extends AppCompatActivity {

    EditText name, goalAmount, goalDescription, addSavings;
    DatePickerDialog datePickerDialog;
    Button update, estimatedDate, category;
    DBHelper DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_goal);

        goalAmount = findViewById(R.id.goalAmount);
        goalDescription = findViewById(R.id.goalDescription);
        initDatePicker();
        estimatedDate = findViewById(R.id.estimatedDate);
        estimatedDate.setText(getTodaysDate());
        addSavings = findViewById(R.id.addSavings);

        update = findViewById(R.id.update);
        DB = new DBHelper(this);
    }
            public void onClick(View view) {
                String nameSrc = name.getText().toString();
                String goalAmountSrc = goalAmount.getText().toString();
                float goalAmounts = Float.parseFloat(goalAmountSrc);
                String goalDescriptionSrc = goalDescription.getText().toString();
                String estimatedDateSrc = estimatedDate.getText().toString();
                String categorySrc = category.getText().toString();
                String addSavingsSrc = addSavings.getText().toString();
                float addSavingsAmount = Float.parseFloat(addSavingsSrc);

                if( TextUtils.isEmpty(name.getText()) || goalAmounts == 0){
                    Toast.makeText(UpdateGoal.this, "Please Insert goal name", Toast.LENGTH_LONG).show();

                    name.setError( "Goal name is required!" );
                }
                //display confirmation pop-up before deletion
                new AlertDialog.Builder(this)
                        .setTitle("Update Goal")
                        .setMessage("Are you sure you want to Update this Goal?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Boolean checkupdatedata = DB.updateGoalData(nameSrc, estimatedDateSrc, goalAmounts, categorySrc, goalDescriptionSrc, addSavingsAmount);
                                if (checkupdatedata == true) {
                                    Toast.makeText(UpdateGoal.this, "Entry updated", Toast.LENGTH_SHORT).show();
                                    switchToMainActivity();
                                } else {
                                    Toast.makeText(UpdateGoal.this, "Entry Not updated", Toast.LENGTH_SHORT).show();
                                }
                            }
                            })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
    public void switchToMainActivity(){
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
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

    public void addDatePop(View v) {

        datePickerDialog.show();

    }

}
