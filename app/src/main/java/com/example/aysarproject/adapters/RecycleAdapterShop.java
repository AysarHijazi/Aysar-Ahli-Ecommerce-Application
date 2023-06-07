package com.example.aysarproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aysarproject.R;
import com.example.aysarproject.model.ShopModel;

import java.util.List;

public class RecycleAdapterShop extends RecyclerView.Adapter<RecycleAdapterShop.viewItem> {


    Context c;
    List<ShopModel> items;

    public RecycleAdapterShop(Context c, List<ShopModel> item) {
        items = item;
        this.c = c;
    }

    class viewItem extends RecyclerView.ViewHolder {
        ImageView image;
        TextView texttitleclothescollections;

        public viewItem(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageclothescollections);
            texttitleclothescollections = itemView.findViewById(R.id.texttitleclothescollections);


        }


    }

    @Override
    public viewItem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_collections, parent, false);
        return new viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(viewItem holder, final int position) {
        Glide.with(c).load(items.get(position).getImageURL()).into(holder.image);
        holder.texttitleclothescollections.setText(items.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return items.size();


    }


}




