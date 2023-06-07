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
import com.example.aysarproject.model.ShopItemModel;
import com.example.aysarproject.model.ShopModel;

import java.util.List;

public class RecycleAdapterShopItem extends RecyclerView.Adapter<RecycleAdapterShopItem.viewItem> {

    private Context context;
    private List<ShopItemModel> items;

    public RecycleAdapterShopItem(Context context, List<ShopItemModel> items) {
        this.context = context;
        this.items = items;
    }

    static class viewItem extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text1shopitem;

        public viewItem(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageshopitem);
            text1shopitem = itemView.findViewById(R.id.text1shopitem);

        }





    }

    @NonNull
    @Override
    public viewItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new viewItem(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull viewItem holder, final int position) {
        Glide.with(context).load(items.get(position).getImageURL()).into(holder.image);
        holder.text1shopitem.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

