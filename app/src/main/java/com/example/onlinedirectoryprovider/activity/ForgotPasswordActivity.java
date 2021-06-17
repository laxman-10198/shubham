package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityForgotPasswordBinding;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.ForgotPasswordViewModel;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {


    ForgotPasswordViewModel forgotPasswordViewModel;
    ActivityForgotPasswordBinding binding;
    String writeemail="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);
        forgotPasswordViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
        binding.sendotp.setOnClickListener(this);


        getInitUi();
    }
    private void getInitUi() {
        binding.sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidation())
                    getForgotPassword();
            }


            private boolean isValidation() {

                if (TextUtils.isEmpty(binding.email.getText().toString())) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (!Util.isEmailValid(binding.email.getText().toString())) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter valid Email", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;


            }


            private void getForgotPassword() {
                String emailnew = binding.email.getText().toString();
                ProgressBarUtils.showProgressDialog(ForgotPasswordActivity.this);
                forgotPasswordViewModel.forgotPassword(emailnew,"2").observe(ForgotPasswordActivity.this,
                        forgotPasswordResponse -> {
                            ProgressBarUtils.hideProgressDialog();
                            if (forgotPasswordResponse != null && !forgotPasswordResponse.getError()) {
                                Toast.makeText(ForgotPasswordActivity.this, forgotPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgotPasswordActivity.this, VerifyOtpActivity.class);
                                intent.putExtra("findActivity", "1");
                                intent.putExtra("emailnew", emailnew);
                                Log.i("sendsuccess","success");
                                startActivity(intent);
                            } else {
                                ProgressBarUtils.hideProgressDialog();
                                Toast.makeText(ForgotPasswordActivity.this, forgotPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
    }

    @Override
    public void onClick(View v) {

    }
}