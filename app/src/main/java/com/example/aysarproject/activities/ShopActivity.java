package com.example.aysarproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aysarproject.R;
import com.example.aysarproject.adapters.RecycleAdapterShop2;
import com.example.aysarproject.adapters.RecycleAdapterShopItem;
import com.example.aysarproject.model.CategoriesModel;
import com.example.aysarproject.model.ShopItemModel;
import com.example.aysarproject.model.ShopModel;
import com.example.aysarproject.retrofit.Api;
import com.example.aysarproject.retrofit.RetrofitClint;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity {
    RecyclerView recyclerViewshop;
    private CategoriesModel cat_select;
    String selectedShopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        recyclerViewshop=findViewById(R.id.recyclerViewshop);
        selectedShopId = getIntent().getStringExtra("selectedShopId");



    }


    public void showItems(List<ShopItemModel> shopItemModelList, CategoriesModel cat_select) {
        this.cat_select = cat_select;

        RecycleAdapterShopItem recycleAdapter = new RecycleAdapterShopItem(ShopActivity.this, shopItemModelList);
        recyclerViewshop.setAdapter(recycleAdapter);


    }

}
