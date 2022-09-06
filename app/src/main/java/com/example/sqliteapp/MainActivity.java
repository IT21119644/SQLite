package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText PIN, name;
    int pinNo;
    Button insert, read, update, delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PIN = findViewById(R.id.pin);


        DB = new DBHelper(this);
    }

//    public void insertToDB(View v){
//        pinNo = Integer.parseInt(PIN.getText().toString());
//
//        boolean checkInsertData = DB.insertUserData();
//        if(checkInsertData){
//            Toast.makeText(MainActivity.this, "Entry updated", Toast.LENGTH_LONG).show();
//            ID.setText(null);
//            name.setText(null);
//        }
//        else
//            Toast.makeText(MainActivity.this, "New entry not inserted", Toast.LENGTH_LONG).show();
//    }

    public void getUserDataFromDB(View v){
        pinNo = Integer.parseInt(PIN.getText().toString());
        Cursor res = DB.getData();
        if(res.getCount() == 0){
            Toast.makeText(MainActivity.this, "No entry exists", Toast.LENGTH_LONG).show();
        }
        else{
            while(res.moveToNext()){
                int existingPin = res.getInt(2);
                if(pinNo == existingPin){
                    //move to success page
                    Intent i = new Intent(this, Home.class);

                }
            }
            Toast.makeText(MainActivity.this, "Incorrect PIN", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_LONG).show();
    }

    public void switchToSignUp(View v){
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

}