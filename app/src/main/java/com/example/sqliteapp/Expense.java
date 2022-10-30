package com.example.sqliteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class Expense extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    EditText itemID, amountT;
    TextView currentInc;
    Button btncategory, btndate;
    ImageButton btnOk;
    Spinner spinner;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountT = (EditText)findViewById(R.id.amount);
        currentInc =(TextView)findViewById(R.id.currentInc);
        btnOk = (ImageButton)findViewById(R.id.btnOk);
        btncategory = findViewById(R.id.btncategory);
        btndate = findViewById(R.id.date);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.addIncome);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        Intent i = getIntent();
        String cat = i.getStringExtra("BtnTxt");
        if(cat != null){
            btncategory.setText(cat);
        }
        DB = new DBHelper(this);

        Cursor res = DB.getExpenseData();
        float sum = 0;
        while(res.moveToNext()){
            sum += res.getFloat(3);
        }
        currentInc.setText(String.valueOf(sum));



        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch(id){
                    case R.id.nav_home:
                        Toast.makeText(Expense.this, "Home is clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_expenses:
                        Toast.makeText(Expense.this, "Expenses is clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_income:
                        Toast.makeText(Expense.this, "Income is clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_budget:
                        Toast.makeText(Expense.this, "Budget is clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_goals:
                        Toast.makeText(Expense.this, "Goal is clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_login:
                        Toast.makeText(Expense.this, "Login is clicked", Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;

                }
                return true;
            }
        });



        initDatePicker();
        btndate.setText(getTodaysDate());


    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month +=1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    public void switchToCategory(View view){
        Intent switchActivityIntent = new Intent(this, Category.class);
        startActivity(switchActivityIntent);
    }

    public void modifyIncome(View view){
        Intent switchActivityIntent = new Intent(this, Modify_Income.class);
        startActivity(switchActivityIntent);
    }


    public void insertIncome(View v){
        String category = btncategory.getText().toString();
        String date = btndate.getText().toString();
        String amount = amountT.getText().toString();
        float amnt = Float.parseFloat(amount);

        Toast.makeText(Expense.this, "Please Insert amount", Toast.LENGTH_LONG).show();
        boolean b = DB.insertExpenseData(category, date, amnt);
        if(b){
            Toast.makeText(Expense.this, "Correct", Toast.LENGTH_LONG).show();
            Intent switchActivityIntent = new Intent(this, Expense.class);
            startActivity(switchActivityIntent);
        }
        else{
            Toast.makeText(Expense.this, "FAILED", Toast.LENGTH_LONG).show();
        }
    }



    // dropdown menu methods
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int month, int day, int year) {
                month = month+1;
                String sDate = makeDateString(day,month,year);
                btndate.setText(sDate);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style,dateSetListener,year,month,day);

    }

    private String makeDateString(int day, int month, int year){
        return getMonthFormat(month) + " " +day+ " " + year;
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
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

}
