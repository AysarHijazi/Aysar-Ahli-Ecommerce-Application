package com.example.aysarproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aysarproject.R;
import com.example.aysarproject.model.ShopModel;

import java.util.List;

public class RecycleAdapterShop2 extends RecyclerView.Adapter<RecycleAdapterShop2.viewItem> {

    private Context context;
    private List<ShopModel> items;

    public RecycleAdapterShop2(Context context, List<ShopModel> items) {
        this.context = context;
        this.items = items;
    }

    static class viewItem extends RecyclerView.ViewHolder {
        ImageView image;
        TextView texttitleclothescollections;

        public viewItem(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageclothescollections);
            texttitleclothescollections = itemView.findViewById(R.id.texttitleclothescollections);

        }





    }

    @NonNull
    @Override
    public viewItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_collections2, parent, false);
        return new viewItem(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull viewItem holder, final int position) {
        Glide.with(context).load(items.get(position).getImageURL()).into(holder.image);
        holder.texttitleclothescollections.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

