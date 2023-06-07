package com.example.aysarproject.retrofit;

import com.example.aysarproject.model.BannerImagesModel;
import com.example.aysarproject.model.CategoriesModel;
import com.example.aysarproject.model.ShopItemModel;
import com.example.aysarproject.model.ShopModel;
import com.example.aysarproject.model.UploadResponse;
import com.example.aysarproject.model.UserModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    @Multipart
    @POST("login.php")
    Call<UserModel> login(@Part("Phone") RequestBody phone,
                          @Part("Password") RequestBody password,
                          @Part("ConCode") RequestBody conCode
    );

    @Multipart
    @POST("SignUp.php")
    Call<UserModel> SignUp(@Part("Name") RequestBody Name,
                           @Part("Phone") RequestBody Phone,
                           @Part("Password") RequestBody Password,
                           @Part("ConCode") RequestBody ConCode,
                           @Part("Email") RequestBody Email);


    @GET("getBannerImages.php")
    Call<List<BannerImagesModel>> getImage();


    @GET("GetCategories.php")
    Call<List<CategoriesModel>> getCategories();



    @Multipart
    @POST("getShops.php")
    Call<List<ShopModel>> getShop(@Part("Id_category") RequestBody Id_category);

    @Multipart
    @POST("getItems.php")
    Call<List<ShopItemModel>> getItems(@Part("Id_shop") RequestBody Id_shop);

    @Multipart
    @POST("test.php")
    Call<UploadResponse> testImageUpload(@Part MultipartBody.Part file);


}

