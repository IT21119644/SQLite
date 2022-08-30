package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ID, name;
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
            Toast.makeText(MainActivity.this, "Entry not deleted", Toast.LENGTH_LONG).show();
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
            Toast.makeText(MainActivity.this, "Entry not updated", Toast.LENGTH_LONG).show();
    }
}