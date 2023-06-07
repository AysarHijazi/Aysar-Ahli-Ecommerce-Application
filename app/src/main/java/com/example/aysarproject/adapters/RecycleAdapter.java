package com.example.aysarproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aysarproject.R;
import com.example.aysarproject.model.BannerImagesModel;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.viewItem> {

    Context c;
    List<BannerImagesModel> items;

    public RecycleAdapter(Context c, List<BannerImagesModel> item) {
        items = item;
        this.c = c;
    }

    class viewItem extends RecyclerView.ViewHolder {
        ImageView image;

        public viewItem(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemImage);

        }
    }


    @Override
    public viewItem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_banner, parent, false);
        return new viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(viewItem holder, final int position) {
        Glide.with(c).load(items.get(position).getImageURL()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}