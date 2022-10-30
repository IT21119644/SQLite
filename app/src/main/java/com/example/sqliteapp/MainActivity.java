package com.example.sqliteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;

public class MainActivity<createBudget> extends AppCompatActivity {
    EditText ID, name;
    TextView tv;
    Button insert, read, update, delete;
    DBHelper DB;
    DonutProgressView incChart, expChart, goalChart, budChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        tv = findViewById(R.id.amnt);


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
                        Toast.makeText(MainActivity.this, "Expenses is clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_income:
                        Toast.makeText(MainActivity.this, "Income is clicked", Toast.LENGTH_SHORT).show();
                        break;
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
        getBudgetData();
        generateIncomeChart();

        //notification when the budget is reached
//        if(balancePercentage == 100f){
//            try {
//                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//                r.play();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void generateIncomeChart(){
        float checks=0,
                childSupport=0,
                duesAndGrants=0,
                gifts=0,
                interests=0,
                lending=0,
                lottery=0,
                refunds=0,
                rental=0,
                sale=0,
                wage=0,
                other=0;

        incChart = findViewById(R.id.incDpvChart);
        Cursor res2 = DB.getIncomeData();
        float totalIncome = 0;
        while(res2.moveToNext()){
            totalIncome += res2.getFloat(3);
            if(res2.getString(1).equals("Checks, Coupons"))
                checks = res2.getFloat(3);
            else if(res2.getString(1).equals("Child Support"))
                childSupport = res2.getFloat(3);
            else if(res2.getString(1).equals("Dues & Grants"))
                duesAndGrants = res2.getFloat(3);
            else if(res2.getString(1).equals("Gifts"))
                gifts = res2.getFloat(3);
            else if(res2.getString(1).equals("Interests, Dividends"))
                interests = res2.getFloat(3);
            else if(res2.getString(1).equals("Lending, Renting"))
                lending = res2.getFloat(3);
            else if(res2.getString(1).equals("Lottery, Gambling"))
                lottery = res2.getFloat(3);
            else if(res2.getString(1).equals("Refunds (Tax,Purchase)"))
                refunds = res2.getFloat(3);
            else if(res2.getString(1).equals("Rental Income"))
                rental = res2.getFloat(3);
            else if(res2.getString(1).equals("Sale"))
                sale = res2.getFloat(3);
            else if(res2.getString(1).equals("Wage, Invoices"))
                wage = res2.getFloat(3);
            else if(res2.getString(1).equals("Other"))
                other = res2.getFloat(3);
        }

        DonutSection section1 = new DonutSection("Section 1 Name", Color.parseColor("#f44336"), (checks/totalIncome)*100);
        DonutSection section2 = new DonutSection("Section 2 Name", Color.parseColor("#4fb200"), (childSupport/totalIncome)*100);
        DonutSection section3 = new DonutSection("Section 3 Name", Color.parseColor("#3c2ada"), (duesAndGrants/totalIncome)*100);
        DonutSection section4 = new DonutSection("Section 4 Name", Color.parseColor("#FF6600"), (gifts/totalIncome)*100);
        DonutSection section5 = new DonutSection("Section 5 Name", Color.parseColor("#FFCC00"), (interests/totalIncome)*100);
        DonutSection section6 = new DonutSection("Section 6 Name", Color.parseColor("#663300"), (lending/totalIncome)*100);
        DonutSection section7 = new DonutSection("Section 7 Name", Color.parseColor("#003333"), (lottery/totalIncome)*100);
        DonutSection section8 = new DonutSection("Section 8 Name", Color.parseColor("#FF33FF"), (refunds/totalIncome)*100);
        DonutSection section9 = new DonutSection("Section 9 Name", Color.parseColor("#00FFFF"), (rental/totalIncome)*100);
        DonutSection section10 = new DonutSection("Section 10 Name", Color.parseColor("#9933FF"), (sale/totalIncome)*100);
        DonutSection section11 = new DonutSection("Section 11 Name", Color.parseColor("#CCFFFF"), (wage/totalIncome)*100);
        DonutSection section12 = new DonutSection("Section 12 Name", Color.parseColor("#7B68EE"), (other/totalIncome)*100);

        incChart.setCap(100f);
        ArrayList<DonutSection> ar = new ArrayList();
        ar.add(section1);
        ar.add(section2);
        ar.add(section3);
        ar.add(section4);
        ar.add(section5);
        ar.add(section6);
        ar.add(section7);
        ar.add(section8);
        ar.add(section9);
        ar.add(section10);
        ar.add(section11);
        ar.add(section12);

        incChart.submitData(ar);
    }

    public void getBudgetData() {
        float FoodAndDrinks = 0,
                Shopping = 0,
                Housing = 0,
                Transportation = 0,
                Vehicle = 0,
                Entertainment = 0,
                Communication = 0,
                Financial = 0,
                Investments = 0,
                Others = 0;

        budChart = findViewById(R.id.budDpvChart);
        Cursor res = DB.getBudgetData();
        float totalBudget = 0;

        while (res.moveToNext()) {
            totalBudget += res.getFloat(2);

            if (res.getString(4).equals("Shopping"))
                Shopping = res.getFloat(2);
            else if (res.getString(4).equals("Housing"))
                Housing = res.getFloat(2);
            else if (res.getString(4).equals("Food and drinks"))
                FoodAndDrinks = res.getFloat(2);
            else if (res.getString(4).equals("Transportation"))
                Transportation = res.getFloat(2);
            else if (res.getString(4).equals("Vehicle"))
                Vehicle = res.getFloat(2);
            else if (res.getString(4).equals("Entertainment"))
                Entertainment = res.getFloat(2);
            else if (res.getString(4).equals("Communication"))
                Communication = res.getFloat(2);
            else if (res.getString(4).equals("Financial"))
                Financial = res.getFloat(2);
            else if (res.getString(4).equals("Investments"))
                Investments = res.getFloat(2);
            else if (res.getString(4).equals("Others"))
                Others = res.getFloat(2);
        }

        DonutSection section1 = new DonutSection("Section 1 Name", Color.parseColor("#f44336"), (FoodAndDrinks/totalBudget)*100);
        DonutSection section2 = new DonutSection("Section 2 Name", Color.parseColor("#4fb200"), (Housing/totalBudget)*100);
        DonutSection section3 = new DonutSection("Section 3 Name", Color.parseColor("#3c2ada"), (Transportation/totalBudget)*100);
        DonutSection section4 = new DonutSection("Section 4 Name", Color.parseColor("#FF6600"), (Vehicle/totalBudget)*100);
        DonutSection section5 = new DonutSection("Section 5 Name", Color.parseColor("#FFCC00"), (Entertainment/totalBudget)*100);
        DonutSection section6 = new DonutSection("Section 6 Name", Color.parseColor("#663300"), (Communication/totalBudget)*100);
        DonutSection section7 = new DonutSection("Section 7 Name", Color.parseColor("#003333"), (Financial/totalBudget)*100);
        DonutSection section8 = new DonutSection("Section 8 Name", Color.parseColor("#FF33FF"), (Investments/totalBudget)*100);
        DonutSection section9 = new DonutSection("Section 9 Name", Color.parseColor("#00FFFF"), (Others/totalBudget)*100);
        DonutSection section10 = new DonutSection("Section 10 Name", Color.parseColor("#9933FF"), (Shopping/totalBudget)*100);


        budChart.setCap(100f);
        ArrayList<DonutSection> budAr = new ArrayList();
        budAr.add(section1);
        budAr.add(section2);
        budAr.add(section3);
        budAr.add(section4);
        budAr.add(section5);
        budAr.add(section6);
        budAr.add(section7);
        budAr.add(section8);
        budAr.add(section9);
        budAr.add(section10);


        budChart.submitData(budAr);
    }
}



