package com.example.sqliteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout yourlayout= (LinearLayout) findViewById(R.id.yourlayout);

        DBHelper DB = new DBHelper(this);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
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
                        Toast.makeText(MainActivity.this, "Goal is clicked", Toast.LENGTH_SHORT).show();
                        launchGoalHome();
                        break;
                    case R.id.nav_login:
                        Toast.makeText(MainActivity.this, "Login is clicked", Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;

                }
                return true;
            }
        });
    }

    public void launchGoalHome() {
        Intent switchToGoals = new Intent(this, GoalHome.class);
        startActivity(switchToGoals);
    }

    // dropdown menu methods0
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
    /* EditText ID, name;
    Button insert, read, update, delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        ID = findViewById(R.id.ID);

        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        read = findViewById(R.id.read);

        DB = new DBHelper(this);
    }

    public void insertToDB(View v){
        String IDSrc = ID.getText().toString();
        String nameSrc = name.getText().toString();

        boolean checkInsertData = DB.insertUserData(IDSrc, nameSrc);
        if(checkInsertData){
            Toast.makeText(MainActivity.this, "Entry updated", Toast.LENGTH_LONG).show();
            ID.setText(null);
            name.setText(null);
        }
        else
            Toast.makeText(MainActivity.this, "New entry not inserted", Toast.LENGTH_LONG).show();
    }

    public void getDataFromDB(View v){
        Cursor res = DB.getData();
        if(res.getCount() == 0){
            Toast.makeText(MainActivity.this, "No entry exists", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("Name: " + res.getString(1) + "\n\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("User Details");
        builder.setMessage(buffer.toString());
        builder.show();
    }

    public void deleteDataFromDB(View v){
        String IDSrc = ID.getText().toString();
        boolean deleteData = DB.deleteUserData(IDSrc);

        if(deleteData){
            Toast.makeText(MainActivity.this, "Entry deleted", Toast.LENGTH_LONG).show();
            ID.setText(null);
            name.setText(null);
        }

        else
            Toast.makeText(MainActivity.this, "Entry cannot be deleted", Toast.LENGTH_LONG).show();
    }

    public void updateDataInDB(View v){
        String IDSrc = ID.getText().toString();
        String nameSrc = name.getText().toString();

        boolean checkUpdatedData = DB.updateUserData(IDSrc, nameSrc);
        if(checkUpdatedData){
            Toast.makeText(MainActivity.this, "Entry updated", Toast.LENGTH_LONG).show();
            ID.setText(null);
            name.setText(null);
        }
        else
            Toast.makeText(MainActivity.this, "Entry cannot be updated", Toast.LENGTH_LONG).show();
    }*/
