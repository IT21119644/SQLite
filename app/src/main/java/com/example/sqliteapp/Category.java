package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class Category extends AppCompatActivity{ //implements AdapterView.OnItemSelectedListener{

    private static final String TAG = "Category";

    private ArrayList<Integer> mImages = new ArrayList<>();
    private ArrayList<String> mCategoryName = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Log.d(TAG, "onCreate: started");

        initData();
    }

    private void initData(){
        mImages.add(R.drawable.check_book);
        mCategoryName.add("Checks, Coupons");

        mImages.add(R.drawable.romper);
        mCategoryName.add("Child Support");

        mImages.add(R.drawable.dues);
        mCategoryName.add("Dues & grants");

        mImages.add(R.drawable.gifts);
        mCategoryName.add("Gifts");

        mImages.add(R.drawable.interests);
        mCategoryName.add("Interests, Dividends");

        mImages.add(R.drawable.rent);
        mCategoryName.add("Lending, Renting");

        mImages.add(R.drawable.dice);
        mCategoryName.add("Lottery, Gambling");

        mImages.add(R.drawable.refund);
        mCategoryName.add("Refunds (tax, purchase)");

        mImages.add(R.drawable.rent_income);
        mCategoryName.add("Rental Income");

        mImages.add(R.drawable.sales);
        mCategoryName.add("Sales");

        mImages.add(R.drawable.wages);
        mCategoryName.add("Wage, Invoice");

        mImages.add(R.drawable.other);
        mCategoryName.add("Other");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mImages,mCategoryName,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
   /* @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
};