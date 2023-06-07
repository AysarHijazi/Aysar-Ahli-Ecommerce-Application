package com.example.aysarproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aysarproject.R;
import com.example.aysarproject.adapters.RecycleAdapter;
import com.example.aysarproject.adapters.RecycleAdapterCategories;
import com.example.aysarproject.adapters.RecycleAdapterShop;
import com.example.aysarproject.iterfaces.ShopInt;
import com.example.aysarproject.model.BannerImagesModel;
import com.example.aysarproject.model.CategoriesModel;
import com.example.aysarproject.model.ShopModel;
import com.example.aysarproject.retrofit.Api;
import com.example.aysarproject.retrofit.RetrofitClint;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageActivity extends AppCompatActivity implements ShopInt {
    private CategoriesModel selectedCategory;

    TextView textcatg;
    ImageView imagecatogersbutton;
    RecyclerView rec;
    RecyclerView recyclerViewhomecategories;

    RecyclerView recyclerViewhomeclothescollections;
    ImageButton imageButtonprofile;
    TextView textCat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        textcatg = findViewById(R.id.textcatg);
        imagecatogersbutton = findViewById(R.id.imagecatogersbutton);
        rec = findViewById(R.id.recyclerViewhome);
        textCat = findViewById(R.id.textCat);

        recyclerViewhomeclothescollections = findViewById(R.id.recyclerViewhomeclothescollections);
        recyclerViewhomecategories = findViewById(R.id.recyclerViewhomecategories);
        imageButtonprofile = findViewById(R.id.imageButtonprofile);
        rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewhomecategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        getBannerImages();
        getCategories();
        imageButtonprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        textcatg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AllcategoriesActivity.class);
                startActivity(intent);


            }
        });


        imagecatogersbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClothesCollectionsActivity.class);
                intent.putExtra("selectedCategoryId", selectedCategory.getId());
                intent.putExtra("selectedCategoryName", selectedCategory.getName());
                startActivity(intent);
            }
        });
    }

    public void getBannerImages() {

        Api service = RetrofitClint.getApiService();

        Call<List<BannerImagesModel>> retCall = service.getImage();
        retCall.enqueue(new Callback<List<BannerImagesModel>>() {
            @Override

            public void onResponse(Call<List<BannerImagesModel>> call, Response<List<BannerImagesModel>> response) {
                RecycleAdapter recycleAdapter = new RecycleAdapter(HomePageActivity.this, response.body());
                rec.setAdapter(recycleAdapter);
            }

            @Override
            public void onFailure(Call<List<BannerImagesModel>> call, Throwable t) {

            }
        });

    }


    public void getCategories() {

        Api service = RetrofitClint.getApiService();


        Call<List<CategoriesModel>> retCall = service.getCategories();
        retCall.enqueue(new Callback<List<CategoriesModel>>() {
            @Override

            public void onResponse(Call<List<CategoriesModel>> call, Response<List<CategoriesModel>> response) {
                RecycleAdapterCategories recycleAdapter = new RecycleAdapterCategories(HomePageActivity.this, response.body());
                recyclerViewhomecategories.setAdapter(recycleAdapter);
            }

            @Override
            public void onFailure(Call<List<CategoriesModel>> call, Throwable t) {

            }
        });
    }


    @Override
    public void showShop(List<ShopModel> shopModelList, CategoriesModel selectedCategory) {
        this.selectedCategory = selectedCategory;
        imagecatogersbutton.setVisibility(View.VISIBLE);
        textCat.setText(selectedCategory.getName() + " Collections");

        RecycleAdapterShop recycleAdapter = new RecycleAdapterShop(HomePageActivity.this, shopModelList);
        recyclerViewhomeclothescollections.setAdapter(recycleAdapter);

    }
}



