package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Category extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initData();
        initRecyclerView();
    }

    private void initData() {
        userList = new ArrayList<>();

        userList.add(new ModelClass(R.drawable.new_house,"New Home"));

        userList.add(new ModelClass(R.drawable.new_vehicle,"New Vehicle"));

        userList.add(new ModelClass(R.drawable.holiday_trip,"Holiday Trip"));

        userList.add(new ModelClass(R.drawable.education,"Education"));

        userList.add(new ModelClass(R.drawable.emergency_fund,"Emergency Fund"));

        userList.add(new ModelClass(R.drawable.health_care,"Health Care"));

        userList.add(new ModelClass(R.drawable.party,"Party"));

        userList.add(new ModelClass(R.drawable.kids_spoiling,"Kids Spoiling"));

        userList.add(new ModelClass(R.drawable.charity,"Charity"));

        userList.add(new ModelClass(R.drawable.other,"Other"));

//        userList.add(new ModelClass(R.drawable.sales,"Sale"));
//
//        userList.add(new ModelClass(R.drawable.wages,"Wage, Invoices"));
//        userList.add(new ModelClass(R.drawable.refund,"Refunds (Tax,Purchase)"));

    }

    private void initRecyclerView(){
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager= new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adaptor= new Adaptor(userList);
        recyclerView.setAdapter(adaptor);
        adaptor.notifyDataSetChanged();
    }
}