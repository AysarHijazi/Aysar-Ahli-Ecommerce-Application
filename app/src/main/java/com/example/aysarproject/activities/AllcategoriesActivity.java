package com.example.aysarproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aysarproject.R;
import com.example.aysarproject.adapters.RecycleAdapterCategoriesHome;
import com.example.aysarproject.model.CategoriesModel;
import com.example.aysarproject.retrofit.Api;
import com.example.aysarproject.retrofit.RetrofitClint;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllcategoriesActivity extends AppCompatActivity {
    RecyclerView recyclerViewhomecategories;
    ImageView backarrowinallctogres ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcategories);
        backarrowinallctogres =findViewById(R.id.backarrowinallctogres);
        recyclerViewhomecategories = findViewById(R.id.recyclerViewhomecategories);
        recyclerViewhomecategories.setLayoutManager(new GridLayoutManager(this,2)); //this, LinearLayoutManager.HORIZONTAL, false
        getCategories();



        backarrowinallctogres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AllcategoriesActivity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });
    }
    public void getCategories() {

        Api service = RetrofitClint.getApiService();


        Call<List<CategoriesModel>> retCall = service.getCategories();
        retCall.enqueue(new Callback<List<CategoriesModel>>() {
            @Override

            public void onResponse(Call<List<CategoriesModel>> call, Response<List<CategoriesModel>> response) {
                RecycleAdapterCategoriesHome recycleAdapter = new RecycleAdapterCategoriesHome(AllcategoriesActivity.this, response.body());
                recyclerViewhomecategories.setAdapter(recycleAdapter);
            }

            @Override
            public void onFailure(Call<List<CategoriesModel>> call, Throwable t) {

            }
        });
    }
}