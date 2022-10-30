package com.example.sqliteapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.Calendar;


public class UpdateGoal extends AppCompatActivity {

    EditText name, goalAmount, goalDescription, addSavings;
    String GoalName, goalAmt, goalDesc, goalSavings, goalDate, goalCategory, GoalHead;
    TextView GoalHeading;
    DatePickerDialog datePickerDialog;
    Button update, estimatedDate, category;
    DBHelper DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_goal);

//        name = findViewById(R.id.name);
        GoalHeading = findViewById(R.id.GoalHeading);
        goalAmount = findViewById(R.id.goalAmount);
        goalDescription = findViewById(R.id.goalDescription);
//        category = findViewById(R.id.category);

//        initDatePicker();
//        estimatedDate = findViewById(R.id.estimatedDate);
//        estimatedDate.setText(getTodaysDate());
        addSavings = findViewById(R.id.addSavings);

        Intent i = getIntent();

        GoalName = i.getStringExtra("Gname");
        goalAmt = i.getStringExtra("GAmt");
        goalDesc = i.getStringExtra("GDesc");
        goalSavings = i.getStringExtra("GSave");
//        goalDate = i.getStringExtra("GDate");
        GoalHeading.setText(GoalName);
        goalAmount.setText(goalAmt);
        goalDescription.setText(goalDesc);
//        estimatedDate.setText(goalDate);
//        addSavings.setText(goalSavings);

        //



        update = findViewById(R.id.update);
        DB = new DBHelper(this);
    }
            public void onClick(View view) {
//                GoalHead = GoalHeading.getText().toString();
                String nameSrc = GoalHeading.getText().toString();
                String goalAmountSrc = goalAmount.getText().toString();
                float goalAmounts = Float.parseFloat(goalAmountSrc);
                String goalDescriptionSrc = goalDescription.getText().toString();
//              String estimatedDateSrc = estimatedDate.getText().toString();
//                String categorySrc = category.getText().toString();
                String addSavingsSrc = addSavings.getText().toString();
                float addSavingsAmount = Float.parseFloat(addSavingsSrc);
                //
                addSavingsAmount = Float.parseFloat(goalSavings) + addSavingsAmount;

                if( TextUtils.isEmpty(GoalHeading.getText()) || goalAmounts == 0){
                    Toast.makeText(UpdateGoal.this, "Please Fill all the Fields", Toast.LENGTH_LONG).show();

                    name.setError( "Details are required" );
                }
                //display confirmation pop-up before deletion
                float finalAddSavingsAmount = addSavingsAmount;
                new AlertDialog.Builder(this)
                        .setTitle("Update Goal")
                        .setMessage("Are you sure you want to Update this Goal?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Boolean checkupdatedata = DB.updateGoalData(nameSrc, goalAmounts, goalDescriptionSrc, finalAddSavingsAmount);
                                if (checkupdatedata) {
                                    Toast.makeText(UpdateGoal.this, "Goal updated", Toast.LENGTH_SHORT).show();
                                    switchToMainActivity();
                                } else {
                                    Toast.makeText(UpdateGoal.this, "Goal Not updated", Toast.LENGTH_SHORT).show();
                                }
                            }
                            })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
    public void switchToMainActivity(){
        Intent switchActivityIntent = new Intent(this, GoalHome.class);
        startActivity(switchActivityIntent);
    }

//    public void backToViewGoal(View v){
//        Intent i = new Intent(this, ViewGoal.class);
//        i.putExtra("Gname", GoalHead);
//        startActivity(i);
//    }

    public void backToViewGoal(View v){
        Intent i = new Intent(this, ViewGoal.class);
        i.putExtra("Gname", GoalName);
        startActivity(i);
    }

//    private String getTodaysDate() {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        month = month + 1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//
//        return makeDateString(day, month, year);
//    }
//
//    private void initDatePicker() {
//        DatePickerDialog.OnDateSetListener dateSetListner = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//                String date = makeDateString(day, month, year);
//                estimatedDate.setText(date);
//            }
//        };
//
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//
//        int style = AlertDialog.THEME_HOLO_LIGHT;
//
//        datePickerDialog = new DatePickerDialog(this, style, dateSetListner, year, month, day);
//    }

//    private String makeDateString(int day, int month, int year) {
//        return day + " " + getMonthFormat(month) + " " + year;
//    }
//
//
//    private String getMonthFormat(int month) {
//        if (month == 1)
//            return "JAN";
//        if (month == 2)
//            return "FEB";
//        if (month == 3)
//            return "MAR";
//        if (month == 4)
//            return "APR";
//        if (month == 5)
//            return "MAY";
//        if (month == 6)
//            return "JUN";
//        if (month == 7)
//            return "JUL";
//        if (month == 8)
//            return "AUG";
//        if (month == 9)
//            return "SEP";
//        if (month == 10)
//            return "OCT";
//        if (month == 11)
//            return "NOV";
//        if (month == 12)
//            return "DEC";
//        return "JAN";
//    }
//
//    public void addDatePop(View v) {
//
//        datePickerDialog.show();
//
//    }

//    public void getSingleGoalData() throws ParseException {
//        Cursor res = DB.getSingleGoalData(GoalName);
//        while(res.moveToNext()){
//            GoalName = res.getString(0);
//            goalDate = res.getString(1);
//            goalAmt = res.getString(2);
////            category = res.getString(3);
//            goalDesc = res.getString(4);
//            goalSavings = res.getString(5);
//
//            name.setText(GoalName);
//            estimatedDate.setText(goalDate);
//            goalAmount.setText(goalAmt);
//            goalDescription.setText(goalDesc);
//            addSavings.setText(goalSavings);
//            GoalHeading.setText(GoalName);
//        }
//    }

}
