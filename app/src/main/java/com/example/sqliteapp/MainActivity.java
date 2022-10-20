package com.example.sqliteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText ID, name, amount;
    TextView currentInc;
    Button category;
    ImageButton btnOk;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.btnCategory);
        NavigationView navigationView = findViewById(R.id.navigation_view);
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

        amount = (EditText)findViewById(R.id.amount);
        currentInc =(TextView)findViewById(R.id.currentInc);
        btnOk = (ImageButton)findViewById(R.id.btnOk);

        onAdd();

        category = findViewById(R.id.btncategory);
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Category.class);
                startActivity(intent);
            }
        });
    }
    //add amount to the current amount
    private void onAdd() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double value = Double.parseDouble(amount.getText().toString());
                double currentVal = Double.parseDouble(currentInc.getText().toString());
                currentVal += value;
                currentInc.setText(Double.toString(currentVal));
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


}