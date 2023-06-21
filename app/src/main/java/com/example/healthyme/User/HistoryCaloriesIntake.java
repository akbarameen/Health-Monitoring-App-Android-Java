package com.example.healthyme.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.healthyme.AdapterClasses.HistoryCIAdapter;
import com.example.healthyme.AdapterClasses.HistorySLAdapter;
import com.example.healthyme.Database.DbHelper;
import com.example.healthyme.Entities.HistoryCIModel;
import com.example.healthyme.Entities.HistorySLModel;
import com.example.healthyme.R;

import java.util.ArrayList;
import java.util.Calendar;

public class HistoryCaloriesIntake extends AppCompatActivity {

    RecyclerView RVCaloriesIntake;
    HistoryCIAdapter adapter;
    ArrayList<HistoryCIModel> arrCI = new ArrayList<>();
    DbHelper dbHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_calories_intake);

        RVCaloriesIntake = findViewById(R.id.rv_history_ci);
        arrCI = new ArrayList<>();
        dbHelper = new DbHelper(this);

        ImageView backArrow = findViewById(R.id.history_calories_back_btn);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // for id
        // getting email of the user logged in
        SharedPreferences sharedPreferences = getSharedPreferences("email_pref", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("userEmail", "").toString();

        // getting the id of the user logged in
        DbHelper helper = new DbHelper(this);
        Cursor cursor = helper.getUserData(email);
        int id = 0;

        while(cursor.moveToNext()){
            id = cursor.getInt(0);
        }


        int finalId = id;


        arrCI = dbHelper.fetchCIHistory(finalId);

        adapter = new HistoryCIAdapter(this, arrCI);
        RVCaloriesIntake.setLayoutManager(new LinearLayoutManager(this));


        RVCaloriesIntake.setAdapter(adapter);



    }
}