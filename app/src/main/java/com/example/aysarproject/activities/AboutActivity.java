package com.example.aysarproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.aysarproject.R;

public class AboutActivity extends AppCompatActivity {
ImageView backarrowprofileaboutus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        backarrowprofileaboutus=findViewById(R.id.backarrowprofileaboutus);



        backarrowprofileaboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(AboutActivity.this,ProfileHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}