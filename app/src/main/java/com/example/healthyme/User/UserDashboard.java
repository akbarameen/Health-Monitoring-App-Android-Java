package com.example.healthyme.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.healthyme.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserDashboard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FloatingActionButton addFloatingBtn, addCaloriesFab;
    Button addBpBtn, addHbBtn, addCaloriesBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // status bar hiding
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.user_dashboard);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        addFloatingBtn = findViewById(R.id.add_floating_btn);



        // main navigation bar floating button
        addFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBtnDialog();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.dynamics) {
                    addFragment(new DynamicsFragment(), true );
                }else if (id == R.id.info) {
                    addFragment(new InfoFragment(), false );
                } else if (id == R.id.report) {
                    addFragment(new ReportFragment(), false );
                } else {//profile
                    addFragment(new ProfileFragment(), false );
                }

                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.dynamics);


    }
    // Adding Fragment into navigation bar
    public void addFragment(Fragment fragment, boolean flag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (flag) {
            fragmentTransaction.add(R.id.dashboard_container, fragment);
        }else{
            fragmentTransaction.replace(R.id.dashboard_container, fragment);
        }
        fragmentTransaction.commit();
    }

    // for showing bottom sheet dialog
    private void showBtnDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_layout);

        ImageView cancelButton = dialog.findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        addBpBtn= dialog.findViewById(R.id.add_bp_btn);
        addHbBtn= dialog.findViewById(R.id.add_sugar_level_btn);
        addCaloriesBtn= dialog.findViewById(R.id.add_calories_btn);

        addBpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, AddBloodPressure.class);
                dialog.dismiss();
                startActivity(intent);
            }
        });

        addHbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, AddSugarLevel.class);
                dialog.dismiss();
                startActivity(intent);
            }
        });

        addCaloriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, AddCaloriesIntake.class);
                dialog.dismiss();
                startActivity(intent);
            }
        });



    }

    //


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertExitBox = new AlertDialog.Builder(UserDashboard.this);
        alertExitBox.setTitle("Exit?");
        alertExitBox.setIcon(R.drawable.ic_logout);
        alertExitBox.setMessage("Are you sure to Exit?");


        alertExitBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UserDashboard.super.onBackPressed();
            }
        });
        alertExitBox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(UserDashboard.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
            }
        });


        alertExitBox.show();
    }



}