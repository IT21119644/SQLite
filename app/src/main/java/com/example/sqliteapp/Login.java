package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText PIN;
    Button login;
    int pinNo, count = 1;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PIN = findViewById(R.id.pin);

        login = findViewById(R.id.logInBtn);
        DB = new DBHelper(this);
    }


    public void login(View v){
        String pin = PIN.getText().toString();
        Log.d("pin", "pin: " + pin );
        Cursor res = DB.getUserData();
        if(res.getCount() == 0 || pin == null){
            Toast.makeText(Login.this, "Please sign up", Toast.LENGTH_LONG).show();
        }
        else{
            while(res.moveToNext()){
                pinNo = Integer.parseInt(pin);
                int existingPin = res.getInt(2);
                if(pinNo == existingPin){
                    PIN.setText(null);

                    //move to success page
                    Intent i = new Intent(this, Homelog.class);
                    startActivity(i);
                }
                else{
                    count++;
                    if(count > 3){
                        login.setEnabled(false);
                    }
                    Toast.makeText(Login.this, "Incorrect PIN", Toast.LENGTH_LONG).show();
                    PIN.setText(null);
                }

            }
        }
    }

    public void switchToSignUp(View v){
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

}