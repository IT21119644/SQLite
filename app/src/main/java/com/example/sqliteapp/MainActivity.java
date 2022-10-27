package com.example.sqliteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
                        Toast.makeText(MainActivity.this, "Home is clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_expenses:
                        Toast.makeText(MainActivity.this, "Expenses is clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_income:
                        Toast.makeText(MainActivity.this, "Income is clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_budget:
                        Toast.makeText(MainActivity.this, "Budget is clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_goals:
                        Toast.makeText(MainActivity.this, "Goal is clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_login:
                        Toast.makeText(MainActivity.this, "Login is clicked", Toast.LENGTH_SHORT).show();break;
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

//    public void insertIncomeToDB(View view){
//        Toast.makeText(MainActivity.this, "Tick clicked", Toast.LENGTH_LONG).show();
//        String category = btncategory.getText().toString();
//        String date = btndate.getText().toString();
//        String amount = amountT.getText().toString();
//        float amnt = Float.parseFloat(amount);
//
//        if( TextUtils.isEmpty(amountT.getText())){
//            Toast.makeText(MainActivity.this, "Please Insert amount", Toast.LENGTH_LONG).show();
//
//            amountT.setError( "Amount name is required!" );
//        }
//        else{
//            boolean checkInsertData = DB.insertIncomeData(0, category, date, amnt);
//            if(checkInsertData){
//                Toast.makeText(MainActivity.this, "New Income inserted", Toast.LENGTH_LONG).show();
//                btncategory.setText(null);
//                amountT.setText(null);
//                btncategory.setText("None");
////                Intent switchActivityIntent = new Intent(this, MainActivity.class);
////                startActivity(switchActivityIntent);
//            }
//            else
//                Toast.makeText(MainActivity.this, "New entry not inserted", Toast.LENGTH_LONG).show();
//        }
//    }

    //add amount to the current amount
//    private void onAdd() {
//        btnOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(MainActivity.this, "Please Insert amount", Toast.LENGTH_LONG).show();
////                double value = Double.parseDouble(amountT.getText().toString());
////                double currentVal = Double.parseDouble(currentInc.getText().toString());
////                currentVal += value;
////                currentInc.setText(Double.toString(currentVal));
//                Toast.makeText(MainActivity.this, "Tick clicked", Toast.LENGTH_LONG).show();
//                String category = btncategory.getText().toString();
//                String date = btndate.getText().toString();
//                String amount = amountT.getText().toString();
//                float amnt = Float.parseFloat(amount);
//
//                Log.d("COW", category+" "+date+" "+amnt);
//
//                if( TextUtils.isEmpty(amountT.getText())){
//                    Toast.makeText(MainActivity.this, "Please Insert amount", Toast.LENGTH_LONG).show();
//                    amountT.setError( "Amount name is required!" );
//                }
//                else{
//                    boolean checkInsertData = DB.insertIncomeData("0", category, date, "amnt");
////                DB.insertIncomeData("0", "CAR", "DATE", "12000");
//                if(checkInsertData){
//                    Toast.makeText(MainActivity.this, "New Income inserted", Toast.LENGTH_LONG).show();
////                        btncategory.setText(null);
////                        amountT.setText(null);
////                        btncategory.setText("None");
//////                Intent switchActivityIntent = new Intent(this, MainActivity.class);
//////                startActivity(switchActivityIntent);
//                }
//                else
//                    Toast.makeText(MainActivity.this, "New entry not inserted", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
//    }

    public void st(View v){
        String category = btncategory.getText().toString();
        String date = btndate.getText().toString();
        String amount = amountT.getText().toString();
        float amnt = Float.parseFloat(amount);

        Toast.makeText(MainActivity.this, "Please Insert amount", Toast.LENGTH_LONG).show();
        boolean b = DB.insertIncomeData(category, date, amnt);
        if(b){
            Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "FAILED", Toast.LENGTH_LONG).show();
        }

//        Toast.makeText(MainActivity.this, "Tick clicked", Toast.LENGTH_LONG).show();
//        String category = btncategory.getText().toString();
//        String date = btndate.getText().toString();
//        String amount = amountT.getText().toString();
//        float amnt = Float.parseFloat(amount);
//
//        Log.d("COW", category+" "+date+" "+amnt);
//
//        if( TextUtils.isEmpty(amountT.getText())){
//            Toast.makeText(MainActivity.this, "Please Insert amount", Toast.LENGTH_LONG).show();
//            amountT.setError( "Amount name is required!" );
//        }
//        else{
//            boolean checkInsertData = DB.insertIncomeData("0", category, date, "amnt");
////                DB.insertIncomeData("0", "CAR", "DATE", "12000");
//            if(checkInsertData){
//                Toast.makeText(MainActivity.this, "New Income inserted", Toast.LENGTH_LONG).show();
////                        btncategory.setText(null);
////                        amountT.setText(null);
////                        btncategory.setText("None");
//////                Intent switchActivityIntent = new Intent(this, MainActivity.class);
//////                startActivity(switchActivityIntent);
//            }
//            else
//                Toast.makeText(MainActivity.this, "New entry not inserted", Toast.LENGTH_LONG).show();
//        }

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

//    public void insertIncomeToDB(View v){
//        String name = BName.getText().toString();
//        String date = dateButton.getText().toString();
//        String amountStr = BAmount.getText().toString();
//        float amount = Float.parseFloat(amountStr);
//        String category = Bcategory.getText().toString();
//        String todayDate = dp.getTodaysDate();
//        float currentAmt = 0;
//
//
//        if( TextUtils.isEmpty(BName.getText())){
//            Toast.makeText(CreateBudgetUI.this, "Please Insert budget name", Toast.LENGTH_LONG).show();
//
//            BName.setError( "Budget name is required!" );
//        }
//        else{
//            boolean checkInsertData = DB.insertBudgetData(name, date, amount, "LKR", category, 1, 1, todayDate, currentAmt);
//            if(checkInsertData){
//                Toast.makeText(CreateBudgetUI.this, "New Budget inserted", Toast.LENGTH_LONG).show();
//                BName.setText(null);
//                BAmount.setText(null);
//                Bcategory.setText("None");
//                Intent switchActivityIntent = new Intent(this, MainActivity.class);
//                startActivity(switchActivityIntent);
//            }
//            else
//                Toast.makeText(CreateBudgetUI.this, "New entry not inserted", Toast.LENGTH_LONG).show();
//        }
//    }


//    public void sw(View view) {
//        Toast.makeText(this, "Btn clicked", Toast.LENGTH_SHORT).show();
//    }
}