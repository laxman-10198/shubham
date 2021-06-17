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
import com.example.onlinedirectoryprovider.databinding.ActivityOtpBinding;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.ForgotPasswordViewModel;
import com.example.onlinedirectoryprovider.viewmodel.OtpViewModel;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {

    OtpViewModel otpViewModel;
    String findActivity = "0";
    ActivityOtpBinding binding;
    ForgotPasswordViewModel forgotPasswordViewModel;
    String email1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_otp);
        otpViewModel = new ViewModelProvider(this).get(OtpViewModel.class);
        forgotPasswordViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);



        binding.btnsubmit.setOnClickListener(this);
        binding.txtResend.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent != null) {
            email1 = intent.getStringExtra("email1");


        }

    }


    @Override
    public void onClick(View v) {
        if(v==binding.btnsubmit){
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
        if(v==binding.txtResend){
            if (Util.checkConnection(this)) {
                getResend();
            }
        }

    }

    private void getResend() {
        ProgressBarUtils.showProgressDialog(OtpActivity.this);
        forgotPasswordViewModel.forgotPassword(email1,"2").observe(OtpActivity.this,
                forgotPasswordResponse -> {
                    ProgressBarUtils.hideProgressDialog();
                    if (forgotPasswordResponse != null && !forgotPasswordResponse.getError()) {
                        Toast.makeText(OtpActivity.this, "We have resent otp on your email", Toast.LENGTH_SHORT).show();
                    } else {
                        ProgressBarUtils.hideProgressDialog();
                        Toast.makeText(OtpActivity.this, forgotPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void verifyOtp () {
        ProgressBarUtils.showProgressDialog(this);
        otpViewModel.verifyOtp(email1,binding.otp.getOTP(),"1").observe(this, verifyOtp -> {
            if (verifyOtp != null) {
                ProgressBarUtils.hideProgressDialog();
                String code = verifyOtp.getCode();
                try {
                    String v="200";
                    if(code.equals(v))
                    {
                        String tokennew = "Bearer " +verifyOtp.getResult().getToken();
                        String error = verifyOtp.getError();
                        String message = verifyOtp.getMessage();
                        String id = verifyOtp.getResult().getId();
                     //   String name =verifyOtp.getResult().getName();
                        String email =verifyOtp.getResult().getEmail();
                      //  String mobile = verifyOtp.getResult().getMobile();
                        String   verified = verifyOtp.getResult().getVerified();
                      //  String status = verifyOtp.getResult().getStatus();
                        Log.i("tokennew", tokennew);
                        Log.i("code", code);
                        Log.i("error", error);
                        Log.i("message", message);
                        Log.i("id", id);
                    //    Log.i("name", name);
                        Log.i("email", email);
                     //   Log.i("mobile", mobile);
                        Log.i("verified", verified);
                     //   Log.i("status", status);

                        Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid Otp",Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){

                    e.printStackTrace();
                }


            }else{
                ProgressBarUtils.hideProgressDialog();
                Toast.makeText(this, verifyOtp.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
       startActivity(new Intent(OtpActivity.this,LoginActivity.class));
    }
}
