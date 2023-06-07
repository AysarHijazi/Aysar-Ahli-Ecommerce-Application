package com.example.aysarproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aysarproject.R;

public class SplachScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(2000);
        ImageView logo = findViewById(R.id.logo);
        logo.startAnimation(anim);

        Animation animtext = new AlphaAnimation(0.0f, 1.0f);
        animtext.setDuration(2000);
        TextView textsplash_screen = findViewById(R.id.textsplash_screen);
        textsplash_screen.startAnimation(anim);

        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            Intent intent = new Intent(SplachScreen.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent Intent = new Intent(SplachScreen.this, LoginActivity.class);
                    startActivity(Intent);
                    finish();
                }
            }, 3000);
        }
    }
}
