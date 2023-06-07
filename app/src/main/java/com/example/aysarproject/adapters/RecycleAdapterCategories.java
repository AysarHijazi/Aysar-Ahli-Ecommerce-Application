package com.example.aysarproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aysarproject.R;
import com.example.aysarproject.activities.HomePageActivity;
import com.example.aysarproject.iterfaces.ShopInt;
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

public class RecycleAdapterCategories extends RecyclerView.Adapter<RecycleAdapterCategories.viewItem> {
    Context c;
    List<CategoriesModel> items;

    public RecycleAdapterCategories(Context c, List<CategoriesModel> items) {
        this.items = items;
        this.c = c;
    }

    class viewItem extends RecyclerView.ViewHolder {
        Button buttoncatgories;

        public viewItem(View itemView) {
            super(itemView);
            buttoncatgories = itemView.findViewById(R.id.buttoncatgories);
        }
    }


    @Override
    public viewItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_categories, parent, false);
        return new viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(viewItem holder, final int position) {
        holder.buttoncatgories.setText(items.get(position).getName());

        holder.buttoncatgories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getShop(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void getShop(CategoriesModel categoriesModel) {

        Api service = RetrofitClint.getApiService();

        RequestBody IdeRequestBody = RequestBody.create(MediaType.parse("text/plain"), categoriesModel.getId());

        Call<List<ShopModel>> retCall = service.getShop(IdeRequestBody);

        retCall.enqueue(new Callback<List<ShopModel>>() {
            @Override

            public void onResponse(Call<List<ShopModel>> call, Response<List<ShopModel>> response) {

                ShopInt shopInt = (HomePageActivity) c;
                shopInt.showShop(response.body(), categoriesModel);


            }

            @Override
            public void onFailure(Call<List<ShopModel>> call, Throwable t) {

            }
        });
    }


}