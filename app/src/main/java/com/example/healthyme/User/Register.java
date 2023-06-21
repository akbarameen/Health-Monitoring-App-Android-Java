package com.example.healthyme.User;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthyme.Database.DbHelper;
import com.example.healthyme.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Objects;

public class Register extends AppCompatActivity {
    TextView registerLogin;
    TextInputEditText edtFullName, edtEmail, edtDOB, edtHeight, edtWeight, edtPassword;
    Button registerBtn;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // status bar hiding
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        //Hooks
        edtFullName = findViewById(R.id.register_full_name);
        edtEmail = findViewById(R.id.register_email);
        edtDOB = findViewById(R.id.register_dob);
        edtHeight = findViewById(R.id.register_height);
        edtWeight = findViewById(R.id.register_weight);
        edtPassword = findViewById(R.id.register_password);
        registerBtn = findViewById(R.id.register_btn);
        registerLogin = findViewById(R.id.register_login);
        ImageView backArrow = findViewById(R.id.signup_back_btn);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });


        edtHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        edtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edtDOB.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        edtDOB.setFocusable(false);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userRegister();

            }

        });

        registerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

    }

    // validations
    private Boolean validateName() {
        String name = Objects.requireNonNull(edtFullName.getText()).toString();
        if (name.isEmpty()) {
            edtFullName.setError("Name is required");
            return false;
        } else {
            edtFullName.setError(null);
            return true;
        }

    }

    private Boolean validateEmail() {
        String email = Objects.requireNonNull(edtEmail.getText()).toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.isEmpty()) {
            edtEmail.setError("Email is required");
            return false;
        } else if (!email.matches(emailPattern)) {
            edtEmail.setError("Invalid Email");
            return false;
        } else {
            edtEmail.setError(null);
            return true;
        }


    }

    private Boolean validateDOB() {
        String dob = Objects.requireNonNull(edtDOB.getText()).toString();
        if (dob.isEmpty()) {
            edtDOB.setError("DOB is required");
            return false;
        } else {
            edtDOB.setError(null);
            return true;
        }

    }

    private Boolean validateHeight() {
        String height = Objects.requireNonNull(edtHeight.getText()).toString();
        if (height.isEmpty()) {
            edtHeight.setError("Height is required");
            return false;
        } else {
            edtHeight.setError(null);
            return true;
        }

    }

    private Boolean validateWeight() {
        String weight = Objects.requireNonNull(edtWeight.getText()).toString();
        if (weight.isEmpty()) {
            edtWeight.setError("Weight is required");
            return false;
        } else {
            edtWeight.setError(null);
            return true;
        }

    }

    private Boolean validatePassword() {
        String pass = Objects.requireNonNull(edtPassword.getText()).toString();
        if (pass.isEmpty()) {
            edtPassword.setError("Password is required");
            return false;
        } else {
            edtPassword.setError(null);
            return true;
        }

    }

    // register function
    public void userRegister() {
        DbHelper dbHelper = new DbHelper(Register.this);
        String name = Objects.requireNonNull(edtFullName.getText()).toString();
        String email = Objects.requireNonNull(edtEmail.getText()).toString();
        String DOB = Objects.requireNonNull(edtDOB.getText()).toString();
        String height = Objects.requireNonNull(edtHeight.getText()).toString();
        String weight = Objects.requireNonNull(edtWeight.getText()).toString();
        String password = Objects.requireNonNull(edtPassword.getText()).toString();
        if (!validateName() || !validateEmail() || !validateDOB() || !validateHeight() || !validateWeight() || !validatePassword()) {
            return;
        } else {
            boolean checkEmail = dbHelper.checkEmail(email);
            if (checkEmail) {
                edtEmail.setError("User already Exits");
            } else {
                boolean insert = dbHelper.userRegister(name, email, DOB, height, weight, password);
                if (insert) {
                    Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                    edtFullName.setText("");
                    edtEmail.setText("");
                    edtDOB.setText("");
                    edtHeight.setText("");
                    edtWeight.setText("");
                    edtPassword.setText("");
                } else {
                    Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertExitBox = new AlertDialog.Builder(Register.this);
        alertExitBox.setTitle("Exit?");
        alertExitBox.setIcon(R.drawable.ic_logout);
        alertExitBox.setMessage("Are you sure to Exit?");


        alertExitBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Register.super.onBackPressed();
            }
        });
        alertExitBox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Register.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
            }
        });


        alertExitBox.show();
    }



}