package com.example.aysarproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.asksira.bsimagepicker.BSImagePicker;
import com.asksira.bsimagepicker.Utils;
import com.bumptech.glide.Glide;
import com.example.aysarproject.R;
import com.example.aysarproject.model.UploadResponse;
import com.example.aysarproject.retrofit.Api;
import com.example.aysarproject.retrofit.RetrofitClint;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileActivity extends AppCompatActivity implements BSImagePicker.OnSingleImageSelectedListener,
        BSImagePicker.ImageLoaderDelegate {
    ImageView profileImageView;
    Button buttonAddPhoto;
    ImageView finalImage;
    Button uploadPhoto;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImageView = findViewById(R.id.profileImageView);
        buttonAddPhoto = findViewById(R.id.buttonAddPhoto);
        finalImage = findViewById(R.id.finalImage);
        uploadPhoto = findViewById(R.id.uploadPhoto);

        buttonAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BSImagePicker singleSelectionPicker = new BSImagePicker.Builder("com.example.aysarproject.fileprovider")
                        .setMaximumDisplayingImages(24)
                        .setSpanCount(3)
                        .setGridSpacing(Utils.dp2px(2))
                        .setPeekHeight(Utils.dp2px(360))

                        .hideGalleryTile()
                        .setTag("A request ID")
                        .build();
                singleSelectionPicker.show(getSupportFragmentManager(), "picker");
            }
        });

        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
    }

    @Override
    public void loadImage(Uri imageUri, ImageView ivImage) {
        Glide.with(this).load(imageUri).into(ivImage);
    }

    @Override
    public void onSingleImageSelected(Uri uri, String tag) {
        Glide.with(this).load(uri).into(profileImageView);
        imagePath = uri.getPath();
    }

    public void uploadImage() {
        Api service = RetrofitClint.getApiService();
        File file = new File(imagePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("fileToUpload", file.getName(), requestFile);
        Call<UploadResponse> resultCall = service.testImageUpload(body);

        resultCall.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                if (response.isSuccessful()) {
                    // Process the response
                    String imageUrl = response.body().getImageUrl();
                    Glide.with(ProfileActivity.this).load(imageUrl).into(finalImage);
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }
}