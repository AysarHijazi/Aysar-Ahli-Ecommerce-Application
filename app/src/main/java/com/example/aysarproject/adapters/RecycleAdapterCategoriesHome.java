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

public class RecycleAdapterCategoriesHome extends RecyclerView.Adapter<RecycleAdapterCategoriesHome.viewItem> {
    Context c;
    List<CategoriesModel> items;

    public RecycleAdapterCategoriesHome(Context c, List<CategoriesModel> items) {
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
                .inflate(R.layout.item_layout_categories2, parent, false);
        return new viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(viewItem holder, final int position) {
        holder.buttoncatgories.setText(items.get(position).getName());

        holder.buttoncatgories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}