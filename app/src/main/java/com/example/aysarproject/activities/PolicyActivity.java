package com.example.aysarproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.aysarproject.R;

public class PolicyActivity extends AppCompatActivity {
ImageView backarrowprotermspolicy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);

        backarrowprotermspolicy=findViewById(R.id.backarrowprotermspolicy);




        backarrowprotermspolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(PolicyActivity.this,ProfileHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}