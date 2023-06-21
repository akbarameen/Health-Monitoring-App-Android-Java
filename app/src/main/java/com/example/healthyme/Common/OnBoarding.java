package com.example.healthyme.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.healthyme.AdapterClasses.SliderAdapter;
import com.example.healthyme.R;
import com.example.healthyme.Sessions.LoginSessionManagement;
import com.example.healthyme.User.Login;
import com.example.healthyme.User.UserDashboard;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    Button letsStartedBtn, nextBtn;
    SliderAdapter sliderAdapter;
    ConstraintLayout constraintLayout;

    LinearLayout dots_layout;
    TextView[]  dots;

    Animation animation;
    int currentPos;

    @Override
    protected void onStart() {
        LoginSessionManagement session = new LoginSessionManagement(getApplicationContext());
        if (session.isLoggedIn()) {
            Intent intent = new Intent(OnBoarding.this, UserDashboard.class);
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
        setContentView(R.layout.on_boarding);

        //Hooks
        viewPager= findViewById(R.id.on_boarding_slider);
        dots_layout = findViewById(R.id.on_dots);
        letsStartedBtn = findViewById(R.id.lets_started_btn);
        constraintLayout = findViewById(R.id.constraint_layout);
        nextBtn = findViewById(R.id.on_boarding_next_btn);

        //calling adapter
        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);

        //calling function created below
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        letsStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoarding.this, WelcomeScreen.class);
//                Pair[] pairs = new Pair[2];
//
//                pairs[0] = new Pair<View, String>(splashScreen, "splashAnimation");
//                pairs[1] = new Pair<View, String>(lottieAnimSplash, "appNameAnimation");
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, pairs);
//                startActivity(intent, options.toBundle());
                startActivity(intent);
                finish();

            }
        });






    }

    // creating dots inside linearLayout
    private void addDots(int position){
        dots = new TextView[4];

        // for clearing the layout every time its created
        dots_layout.removeAllViews();

        for (int i=0; i<dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(45);

            dots_layout.addView(dots[i]);
        }

        if (dots.length>0) {
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    //getting current slide
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;


            if (position == 0 ) {
                letsStartedBtn.setVisibility(View.INVISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.purple_500));

            } else if (position ==1) {
                letsStartedBtn.setVisibility(View.INVISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.teal_700));
            } else if (position == 2) {
                letsStartedBtn.setVisibility(View.INVISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.blue_light));
            } else {
                letsStartedBtn.setVisibility(View.VISIBLE);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.teal_200));
                animation = AnimationUtils.loadAnimation(OnBoarding.this,R.anim.side_anim);
                letsStartedBtn.setAnimation(animation);
                nextBtn.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void skip(View view){
        startActivity(new Intent(this, WelcomeScreen.class));
        finish();

    }

    public void next(View view){
        viewPager.setCurrentItem(currentPos+1);

    }
}