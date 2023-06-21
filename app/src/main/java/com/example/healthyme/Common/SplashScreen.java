package com.example.healthyme.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.healthyme.R;

public class SplashScreen extends AppCompatActivity {
    LottieAnimationView lottieAnimSplash;
    ImageView topLeft, topRight, bottomLeft, bottomRight;
    TextView appName;
    private static final int SPLASH_TIMER = 5000;
    RelativeLayout splashScreen;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

//        topLeft = findViewById(R.id.top_left);
//        topRight = findViewById(R.id.top_right);
//        bottomLeft = findViewById(R.id.bottom_left);
//        bottomRight = findViewById(R.id.bottom_right);
        splashScreen = (RelativeLayout)findViewById(R.id.splash_screen);
        lottieAnimSplash = findViewById(R.id.lottie_anim_splash);

//        topLeft.animate().translationX(-400).setDuration(1000).setStartDelay(4000);
//        topRight.animate().translationX(400).setDuration(1000).setStartDelay(4000);
//        bottomLeft.animate().translationX(-400).setDuration(1000).setStartDelay(4000);
//        bottomRight.animate().translationX(400).setDuration(1000).setStartDelay(4000);
//        lottieAnimSplash.animate().translationX(-300).translationY(-1000).scaleX(0.4F).scaleY(0.4f).setDuration(1000).setStartDelay(4000);

        // splash screen through handler class
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, OnBoarding.class);
//                Pair[] pairs = new Pair[2];
//
//                pairs[0] = new Pair<View, String>(splashScreen, "splashAnimation");
//                pairs[1] = new Pair<View, String>(lottieAnimSplash, "appNameAnimation");
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, pairs);
//                startActivity(intent, options.toBundle());
                startActivity(intent);

                finish();
            }
        },SPLASH_TIMER);



    }
}