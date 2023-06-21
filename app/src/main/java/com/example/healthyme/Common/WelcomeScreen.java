package com.example.healthyme.Common;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.example.healthyme.R;
import com.example.healthyme.Sessions.LoginSessionManagement;
import com.example.healthyme.User.Login;
import com.example.healthyme.User.Register;
import com.example.healthyme.User.UserDashboard;

public class WelcomeScreen extends AppCompatActivity {
    @Override
    protected void onStart() {
        LoginSessionManagement session = new LoginSessionManagement(getApplicationContext());
        if (session.isLoggedIn()) {
            Intent intent = new Intent(WelcomeScreen.this, UserDashboard.class);
            startActivity(intent);
            finish();
        }

        super.onStart();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome_screen);


        Button login = findViewById(R.id.welcome_login);
        TextView signup = findViewById(R.id.welcome_signup);
        TextView welcomeText = findViewById(R.id.welcome_text);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            welcomeText.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreen.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}