package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ExpenseCategory extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ExpenseModelClass> userList;
    ExpenseAdapter adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_category);

        initData();
        initRecyclerView();
    }

    private void initData() {
        userList = new ArrayList<>();

//        userList.add(new ExpenseModelClass(R.drawable.check_book,"Checks, Coupons"));
//
//        userList.add(new ExpenseModelClass(R.drawable.romper,"Child Support"));
//
//        userList.add(new ExpenseModelClass(R.drawable.dues,"Dues & Grants"));
//
//        userList.add(new ExpenseModelClass(R.drawable.gifts,"Gifts"));
//
//        userList.add(new ExpenseModelClass(R.drawable.interests,"Interests, Dividends"));
//
//        userList.add(new ExpenseModelClass(R.drawable.rent,"Lending, Renting"));
//
//        userList.add(new ExpenseModelClass(R.drawable.dice,"Lottery, Gambling"));
//
//        userList.add(new ExpenseModelClass(R.drawable.refund,"Refunds (Tax,Purchase)"));
//
//        userList.add(new ExpenseModelClass(R.drawable.rent_income,"Rental Income"));
//
//        userList.add(new ExpenseModelClass(R.drawable.sales,"Sale"));
//
//        userList.add(new ExpenseModelClass(R.drawable.wages,"Wage, Invoices"));
//
//        userList.add(new ExpenseModelClass(R.drawable.other,"Other"));


    }

    private void initRecyclerView(){
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager= new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adaptor= new ExpenseAdapter(userList);
        recyclerView.setAdapter(adaptor);
        adaptor.notifyDataSetChanged();
    }
}