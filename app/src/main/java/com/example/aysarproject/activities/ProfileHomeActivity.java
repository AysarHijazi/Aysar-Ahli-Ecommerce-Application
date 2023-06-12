package com.example.aysarproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aysarproject.R;

import java.util.Locale;

public class ProfileHomeActivity extends AppCompatActivity {
    ImageView profilegoarrwo;
    TextView textprofiletext2;
    ImageView aboutusgo;
    ImageView policygo;
ImageView imgViewSignOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_home);

        profilegoarrwo = findViewById(R.id.profilegoarrwo);
        textprofiletext2 = findViewById(R.id.textprofiletext2);
        aboutusgo = findViewById(R.id.aboutusgo);
        policygo = findViewById(R.id.policygo);

        policygo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileHomeActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        aboutusgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileHomeActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        textprofiletext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileHomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        profilegoarrwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileHomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


        imgViewSignOut=findViewById(R.id.imgViewSignOut);

        imgViewSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileHomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateLanguage();
    }

    private void updateLanguage() {
        String selectedLanguage = getResources().getStringArray(R.array.languages)[0];

        if (selectedLanguage.equals("انجليزي")) {
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Resources resources = getResources();
            Configuration config = resources.getConfiguration();
            config.setLocale(locale);
            resources.updateConfiguration(config, resources.getDisplayMetrics());
        } else {
            Locale locale = new Locale("ar");
            Locale.setDefault(locale);
            Resources resources = getResources();
            Configuration config = resources.getConfiguration();
            config.setLocale(locale);
            resources.updateConfiguration(config, resources.getDisplayMetrics());
        }
    }
}
