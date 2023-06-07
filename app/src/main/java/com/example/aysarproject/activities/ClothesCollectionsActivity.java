package com.example.aysarproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aysarproject.R;
import com.example.aysarproject.adapters.RecycleAdapterShop2;
import com.example.aysarproject.map.MapAssistActivity;
import com.example.aysarproject.map.MapsActivity;
import com.example.aysarproject.model.CategoriesModel;
import com.example.aysarproject.model.ShopModel;
import com.example.aysarproject.retrofit.Api;
import com.example.aysarproject.retrofit.RetrofitClint;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClothesCollectionsActivity extends AppCompatActivity {
    RecyclerView recyclerViewhomeclothescollections;
    ImageView backarrowshop;
    TextView textshoptitle;
    private CategoriesModel cat_select;
    String nameTitle;
    String selectedCategoryId;

    ImageView mapbuton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_collections);
        backarrowshop = findViewById(R.id.backarrowshop);
        recyclerViewhomeclothescollections = findViewById(R.id.recyclerViewhomeclothescollections);
        textshoptitle = findViewById(R.id.textshoptitle);
        mapbuton =findViewById(R.id.mapbuton);
        recyclerViewhomeclothescollections.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        nameTitle = getIntent().getStringExtra("selectedCategoryName");
        selectedCategoryId = getIntent().getStringExtra("selectedCategoryId");
        textshoptitle.setText(nameTitle);
        getShop();
        backarrowshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClothesCollectionsActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });


        mapbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ClothesCollectionsActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }


    public void getShop() {
        Api service = RetrofitClint.getApiService();
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), selectedCategoryId);
        Call<List<ShopModel>> call = service.getShop(requestBody);
        call.enqueue(new Callback<List<ShopModel>>() {
            @Override
            public void onResponse(Call<List<ShopModel>> call, Response<List<ShopModel>> response) {
                List<ShopModel> shopModelList = response.body();
                showShop(shopModelList, cat_select);
            }

            @Override
            public void onFailure(Call<List<ShopModel>> call, Throwable t) {

            }
        });
    }

    public void showShop(List<ShopModel> shopModelList, CategoriesModel cat_select) {
        this.cat_select = cat_select;

        RecycleAdapterShop2 recycleAdapter = new RecycleAdapterShop2(ClothesCollectionsActivity.this, shopModelList);
        recyclerViewhomeclothescollections.setAdapter(recycleAdapter);


    }

}


