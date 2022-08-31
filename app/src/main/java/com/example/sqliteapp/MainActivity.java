package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText ID, name;
    Button insert, read, update, delete;
    DBHelper DB;
    Spinner spinner;

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

        //dropdown menu
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);

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