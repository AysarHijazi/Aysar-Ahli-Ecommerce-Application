package com.example.aysarproject.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aysarproject.R;
import com.example.aysarproject.model.UserModel;
import com.example.aysarproject.retrofit.Api;
import com.example.aysarproject.retrofit.RetrofitClint;
import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    EditText edittext_phone_number;
    EditText edittext_pass;
    Button login_button;
    TextView Register_now;

    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login_button = findViewById(R.id.login_button);
        edittext_pass = findViewById(R.id.edittext_pass);
        edittext_phone_number = findViewById(R.id.edittext_phone_number);

        countryCodePicker = findViewById(R.id.country_code_picker);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });


        TextInputEditText passwordEditText = findViewById(R.id.edittext_pass);
        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_remove_red_eye_24, 0);

        passwordEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (passwordEditText.getRight() - passwordEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if (passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_remove_red_eye_24, 0);
                        } else {
                            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0);
                        }
                        return true;
                    }
                }

                return false;
            }
        });


        Register_now = findViewById(R.id.Register_now);

        Register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }

    public void login() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.show();
        Api service = RetrofitClint.getApiService();

        RequestBody phoneRequestBody = RequestBody.create(MediaType.parse("text/plain"), edittext_phone_number.getText().toString());
        RequestBody passwordRequestBody = RequestBody.create(MediaType.parse("text/plain"), edittext_pass.getText().toString());
        RequestBody conCodeRequestBody = RequestBody.create(MediaType.parse("text/plain"), countryCodePicker.getSelectedCountryCode());
        Call<UserModel> retCall = service.login(phoneRequestBody, passwordRequestBody, conCodeRequestBody);
        retCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                pd.dismiss();
                if (response.body().isResult()) {
                    //  SharedPreferences
                    SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    // editor.putString("userId", response.body().getUserId());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}