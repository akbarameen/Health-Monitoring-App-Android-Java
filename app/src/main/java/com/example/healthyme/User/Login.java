package com.example.healthyme.User;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthyme.Database.DbHelper;
import com.example.healthyme.R;
import com.example.healthyme.Sessions.LoginSessionManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


public class Login extends AppCompatActivity {
    TextView loginSignupTxt;
    Button loginBtn;
    private DbHelper dbHelper;
    TextInputEditText edtLoginEmail, edtLoginPassword;


    @Override
    protected void onStart() {
        LoginSessionManagement session = new LoginSessionManagement(getApplicationContext());
        if (session.isLoggedIn()) {
            Intent intent = new Intent(Login.this, UserDashboard.class);
            startActivity(intent);
            finish();
        }
        super.onStart();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.user_login);

        //Hooks
        loginSignupTxt = findViewById(R.id.login_signup_txt);
        loginBtn = findViewById(R.id.user_login);
        edtLoginEmail = findViewById(R.id.login_email);
        edtLoginPassword = findViewById(R.id.login_password);
        ImageView backArrow = findViewById(R.id.login_back_btn);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });



        dbHelper = new DbHelper(this);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();

            }
        });

        loginSignupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
    private Boolean validateEmail() {
        String email = Objects.requireNonNull(edtLoginEmail.getText()).toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.isEmpty()) {
            edtLoginEmail.setError("Email is required");
            return false;
        } else if (!email.matches(emailPattern)) {
            edtLoginEmail.setError("Invalid Email");
            return false;
        } else {
            edtLoginEmail.setError(null);
            return true;
        }


    }
    private Boolean validatePassword() {
        String pass = Objects.requireNonNull(edtLoginPassword.getText()).toString();
        if (pass.isEmpty()) {
            edtLoginPassword.setError("Please Enter Your Password");
            return false;
        } else {
            edtLoginPassword.setError(null);
            return true;
        }

    }

    public void userLogin(){
        String email = Objects.requireNonNull(edtLoginEmail.getText()).toString();
        String pass = Objects.requireNonNull(edtLoginPassword.getText()).toString();
        LoginSessionManagement session = new LoginSessionManagement(this);
        if (!validateEmail()||!validatePassword()) {
            return;
        }else {
            boolean checkEmail = dbHelper.checkEmail(email);
            if (checkEmail) {
                boolean checkEmailPassword = dbHelper.checkEmailPassword(email,pass);
                if (checkEmailPassword) {
                    Toast.makeText(Login.this, "login Successfully", Toast.LENGTH_SHORT).show();

                    SharedPreferences emailPref = getSharedPreferences("email_pref", Context.MODE_PRIVATE);
                    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = emailPref.edit();
                    editor.putString("userEmail",email);
                    editor.apply();


                    Intent intent = new Intent(Login.this, UserDashboard.class);
                    startActivity(intent);
                    session.setLogin(true);

                    finish();
                }else{
                    edtLoginPassword.setError("Incorrect Password");
                }
            }else{
                edtLoginEmail.setError("No User Found");
            }


        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertExitBox = new AlertDialog.Builder(Login.this);
        alertExitBox.setTitle("Exit?");
        alertExitBox.setIcon(R.drawable.ic_logout);
        alertExitBox.setMessage("Are you sure to Exit?");


        alertExitBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Login.super.onBackPressed();
            }
        });
        alertExitBox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Login.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
            }
        });


        alertExitBox.show();
    }
}