package com.example.aysarproject.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClint {

    private static Retrofit getRetroClient() {
        return new Retrofit.Builder()
                .baseUrl("https://alshalbiapps.com/androidahli/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }




    public static Api getApiService() {
        return getRetroClient().create(Api.class);
    }
}
