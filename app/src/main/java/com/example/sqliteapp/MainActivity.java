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
    DonutProgressView dpvChart;

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




    public void getBudgetData(){
        ArrayList<Float> cat = new ArrayList<>();
        Cursor res = DB.getIncomeData();
        while(res.moveToNext()){
            cat.add(res.getFloat(3));
        }

        Log.d("FFFGGG", String.valueOf(cat.get(0)));
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

        dpvChart = findViewById(R.id.incDpvChart);
        Cursor res = DB.getIncomeData();
        float totalIncome = 0;
        while(res.moveToNext()){
            totalIncome += res.getFloat(3);
            if(res.getString(1).equals("Checks, Coupons"))
                checks = res.getFloat(3);
            else if(res.getString(1).equals("Child Support"))
                childSupport = res.getFloat(3);
            else if(res.getString(1).equals("Dues & Grants"))
                duesAndGrants = res.getFloat(3);
            else if(res.getString(1).equals("Gifts"))
                gifts = res.getFloat(3);
            else if(res.getString(1).equals("Interests, Dividends"))
                interests = res.getFloat(3);
            else if(res.getString(1).equals("Lending, Renting"))
                lending = res.getFloat(3);
            else if(res.getString(1).equals("Lottery, Gambling"))
                lottery = res.getFloat(3);
            else if(res.getString(1).equals("Refunds (Tax,Purchase)"))
                refunds = res.getFloat(3);
            else if(res.getString(1).equals("Rental Income"))
                rental = res.getFloat(3);
            else if(res.getString(1).equals("Sale"))
                sale = res.getFloat(3);
            else if(res.getString(1).equals("Wage, Invoices"))
                wage = res.getFloat(3);
            else if(res.getString(1).equals("Other"))
                other = res.getFloat(3);
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

        dpvChart.setCap(100f);
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

        dpvChart.submitData(ar);
    }
}



