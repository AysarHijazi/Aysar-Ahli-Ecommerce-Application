package com.example.aysarproject.activities;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextView login_text ;
    Button button_create_account;
    EditText edit_username ;
    EditText edittext_phone_number ;
    EditText email_edittext ;
    EditText edittext_pass ;

    EditText edittext_pass_retype;
    CountryCodePicker countryCodePicker;

    Button button_gotit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button_create_account=findViewById(R.id.button_create_account);
        edittext_phone_number=findViewById(R.id.edittext_phone_number);
        email_edittext=findViewById(R.id.email_edittext);
        edit_username=findViewById(R.id.edit_username);
        edittext_pass=findViewById(R.id.edittext_pass);
        countryCodePicker=findViewById(R.id.country_code_picker);
        edittext_pass_retype=findViewById(R.id.edittext_pass_retype);


        countryCodePicker=findViewById(R.id.country_code_picker);

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






        TextInputEditText passwordEditText2 = findViewById(R.id.edittext_pass_retype);
        passwordEditText2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_remove_red_eye_24, 0);

        passwordEditText2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (passwordEditText2.getRight() - passwordEditText2.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if (passwordEditText2.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                            passwordEditText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            passwordEditText2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_remove_red_eye_24, 0);
                        } else {
                            passwordEditText2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            passwordEditText2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0);
                        }
                        return true;
                    }
                }
                return false;
            }
        });



        login_text =findViewById(R.id.login_text);

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        button_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
                showBottomSheetDialog();

            }



        });


    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet);






        button_gotit =bottomSheetDialog.findViewById(R.id.button_gotit);

        button_gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        bottomSheetDialog.show();

    }


    public void SignUp() {

        ProgressDialog pd = new ProgressDialog(this);
        pd.show();
        Api service = RetrofitClint.getApiService();

        RequestBody phoneRequestBody = RequestBody.create(MediaType.parse("text/plain"), edittext_phone_number.getText().toString());

        RequestBody passwordRequestBody = RequestBody.create(MediaType.parse("text/plain"), edittext_pass.getText().toString());

        RequestBody conCodeRequestBody = RequestBody.create(MediaType.parse("text/plain"), countryCodePicker.getSelectedCountryCode());


        RequestBody usernameRequestBody = RequestBody.create(MediaType.parse("text/plain"), edit_username.getText().toString());
        RequestBody emailRequestBody = RequestBody.create(MediaType.parse("text/plain"), email_edittext.getText().toString());


        Call<UserModel> retCall = service.SignUp(usernameRequestBody,phoneRequestBody, passwordRequestBody, conCodeRequestBody,emailRequestBody);
        retCall.enqueue(new Callback<UserModel>() {
            @Override

            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                pd.dismiss();
                if (response.body().isResult()) {
                    Toast.makeText(RegisterActivity.this, "correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(RegisterActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                pd.dismiss();
            }
        });

    }

}