package com.cognifyz.internship;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.cognifyz.internship.auth.LoginActivity;
import com.cognifyz.internship.util.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 1800; // 1.8 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LinearLayout centerLayout = findViewById(R.id.ll_splash_center);

        // Smooth zoom-in & fade-in animation
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1200);

        ScaleAnimation scaleUp = new ScaleAnimation(
                0.85f, 1.0f, 0.85f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleUp.setDuration(1200);

        animationSet.addAnimation(fadeIn);
        animationSet.addAnimation(scaleUp);
        centerLayout.startAnimation(animationSet);

        // Delayed navigation
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SessionManager sessionManager = new SessionManager(this);
            Intent nextIntent;
            if (sessionManager.isLoggedIn()) {
                nextIntent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                nextIntent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            startActivity(nextIntent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }, SPLASH_DURATION);
    }
}