package com.example.aysarproject.iterfaces;

import com.example.aysarproject.model.CategoriesModel;
import com.example.aysarproject.model.ShopItemModel;
import com.example.aysarproject.model.ShopModel;

import java.util.List;

public interface ShopInt {
    void showShop(List<ShopModel> shopModelList, CategoriesModel selectedCategory);
}
