package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityVerifyOtpBinding;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.ForgotPasswordViewModel;
import com.example.onlinedirectoryprovider.viewmodel.OtpViewModel;

public class VerifyOtpActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityVerifyOtpBinding binding;
    OtpViewModel otpViewModel;
    ForgotPasswordViewModel forgotPasswordViewModel;
    String findActivity = "";
    String email = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify_otp);
        otpViewModel = new ViewModelProvider(this).get(OtpViewModel.class);
        forgotPasswordViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);

        binding.btnsubmit.setOnClickListener(this);
        binding.txtResend.setOnClickListener(this);



        Intent intent = getIntent();
        if (intent != null) {
            email = intent.getStringExtra("emailnew");
            findActivity = getIntent().getStringExtra("findActivity");
        }

        binding.txtEmail.setText(email);

    }




    @Override
    public void onClick(View v) {
        if (v == binding.btnsubmit) {
            if (Util.checkConnection(this)) {
                if (binding.otp.getOTP().length() == 0) {
                    Toast.makeText(this, "Please enter Otp", Toast.LENGTH_SHORT).show();
                } else if (binding.otp.getOTP().length() < 4) {
                    Toast.makeText(this, " Please enter 4 digit Otp", Toast.LENGTH_SHORT).show();
                } else {
                    verifyOtp();
                }
            } else {
                Toast.makeText(this, "Oops check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }


        if (v == binding.txtResend) {
            if (Util.checkConnection(this)) {
                getResend();
            }
        }
    }
    private void getResend() {

        ProgressBarUtils.showProgressDialog(VerifyOtpActivity.this);
        forgotPasswordViewModel.forgotPassword(email,"2").observe(VerifyOtpActivity.this,
                forgotPasswordResponse -> {
                    ProgressBarUtils.hideProgressDialog();
                    if (forgotPasswordResponse != null && !forgotPasswordResponse.getError()) {
                        Toast.makeText(VerifyOtpActivity.this, "We have resent otp on your email", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(VerifyOtpActivity.this, ResetPassword.class);
                        intent.putExtra("findActivity", "1");
                        intent.putExtra("emailnew", email);
                        //startActivity(intent);
                    } else {
                        ProgressBarUtils.hideProgressDialog();
                        Toast.makeText(VerifyOtpActivity.this, forgotPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void verifyOtp () {
        ProgressBarUtils.showProgressDialog(this);
        otpViewModel.verifyOtp(email,binding.otp.getOTP(), findActivity).observe(this, verifyOtp -> {
            if (verifyOtp != null) {
                ProgressBarUtils.hideProgressDialog();
                Toast.makeText(this, verifyOtp.getMessage(), Toast.LENGTH_SHORT).show();
                if (findActivity != null) {
                    String Code1 = verifyOtp.getCode();
                    String b ="200";
                    String c= "400";
                    if(Code1.equals(b)){
                        String tokennew = "Bearer " + verifyOtp.getResult().getToken();
                        Log.i("token", tokennew);
                        Intent intent = new Intent(VerifyOtpActivity.this, ResetPassword.class);
                        intent.putExtra("emailnew", email);
                        intent.putExtra("tokennew", tokennew);
                        intent.putExtra("findActivity", findActivity);
                        Log.i("SendEmail", email);
                        startActivity(intent);

                    }
                    if (Code1.equals(c)){
                       Toast.makeText(getApplicationContext(),"Invalid Otp",Toast.LENGTH_SHORT).show();
                    }
                    /*String tokennew = "Bearer " + verifyOtp.getResult().getToken();
                    Log.i("token", tokennew);
                    Intent intent = new Intent(VerifyOtpActivity.this, ResetPassword.class);
                    intent.putExtra("emailnew", email);
                    intent.putExtra("tokennew", tokennew);
                    intent.putExtra("findActivity", findActivity);
                    Log.i("SendEmail", email);
                    startActivity(intent);*/
                }
                else
                    {
                    startActivity(new Intent(VerifyOtpActivity.this, LoginActivity.class));
                    finish();
                }
            }
           else{
                ProgressBarUtils.hideProgressDialog();
                Toast.makeText(this, verifyOtp.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(VerifyOtpActivity.this,ForgotPasswordActivity.class));
    }
}

