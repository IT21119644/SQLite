package com.example.sqliteapp;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button createBudget;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout yourlayout= (LinearLayout) findViewById(R.id.yourlayout);

        DBHelper DB = new DBHelper(this);
        Cursor res = DB.getBudgetData();

        ArrayList<String> msg = new ArrayList<>();
        if(res.getCount() == 0){
            Toast.makeText(MainActivity.this, "Your budget is empty", Toast.LENGTH_SHORT).show();

        }

        while(res.moveToNext()){
//            buffer.append("BudgetName " + res.getString(0) + "\n");
//            buffer.append("Amount: " + res.getFloat(2) + "\n\n");
            msg.add(res.getString(0));
        }


        if(!msg.isEmpty()){
            Log.d("val", msg.get(0));
            for (int j = 0; j < msg.size(); j++){
                Button btn = new Button (MainActivity.this);
                btn.setWidth(5);
                btn.setHeight(20);
                btn.setTextSize(25);
                btn.setPadding(20, 0, 0, 0);
                btn.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                btn.setBackgroundColor(Color.parseColor("#fb8d19"));
                btn.setTextColor(Color.parseColor("#000000"));

                //Get the width of the screen
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;

                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                //calculate and set the button width
                int buttonWidth = (int) (width / 1.5);
                buttonLayoutParams.width = buttonWidth;

                if(j == 0)
                    buttonLayoutParams.setMargins(80, 500, 80, 0);
                else
                    buttonLayoutParams.setMargins(80, 20, 80, 0);

                btn.setLayoutParams(buttonLayoutParams);

                //Set button text
                String head = msg.get(j);
                btn.setText(head);
                yourlayout.addView(btn);

                btn.setOnClickListener(handleOnClick(btn, head));
            }
        }
        else{
            TextView t = findViewById(R.id.tv);
            t.setText("Your budget is empty");
        }


        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        createBudget = findViewById(R.id.createBudgetBtn);

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
                        switchToExpenses();
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

    public void switchToExpenses(){
        Intent switchActivityIntent = new Intent(this, Expenses.class);
        startActivity(switchActivityIntent);
    }

    public void switchToCreateBudget(View v){
        Intent switchActivityIntent = new Intent(this, CreateBudgetUI.class);
        startActivity(switchActivityIntent);
    }

    View.OnClickListener handleOnClick(final Button button, String heading) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button is clicked", Toast.LENGTH_SHORT).show();
                Log.d("gg", "Button clicked");
                switchToDisplayBudget(heading);
            }
        };
    }

    public void switchToDisplayBudget(String heading){
        Intent switchActivityIntent = new Intent(this, DisplayBudget.class);
        switchActivityIntent.putExtra("Bow", heading);
        startActivity(switchActivityIntent);
    }
}