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
import com.example.onlinedirectoryprovider.databinding.ActivityResetPasswordBinding;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.ResetPasswordViewModel;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {
    ResetPasswordViewModel resetPasswordViewModel;
    ActivityResetPasswordBinding binding;
    String Authorization;
    String email = "";
    String findActivity="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        resetPasswordViewModel = new ViewModelProvider(ResetPassword.this).get(ResetPasswordViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        binding.btnsave.setOnClickListener(this);
        //  Authorization =  CommonMethod.getPreference(ResetPassword.this, "token");


        Intent intent = getIntent();
        if (intent != null) {
            email = intent.getStringExtra("emailnew");
            Authorization = intent.getStringExtra("tokennew");
            findActivity= intent.getStringExtra("findActivity");
            Log.i("gotemail", email);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnsave){
            if (Util.checkConnection(ResetPassword.this)) {
                if (isValidation()) {
                    getResetPassword();
                }
            }
            else {
                Toast.makeText(this, "Oops check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValidation() {
        if (TextUtils.isEmpty(binding.password.getText().toString())) {
            Toast.makeText(ResetPassword.this, "Please enter new password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.password.length() < 8 || binding.password.length() > 16) {
            Toast.makeText(this, "Password must be between 8 to 16 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(binding.cnfpassword.getText().toString())) {
            Toast.makeText(ResetPassword.this, "Please enter Confirm Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!(binding.password.getText().toString().equals(binding.cnfpassword.getText().toString())))
        {
            Toast.makeText(ResetPassword.this,"Password and Confirm Password do not match",Toast.LENGTH_LONG).show();
            return false;

        }
        return true;
    }

    private void getResetPassword() {
        ProgressBarUtils.showProgressDialog(this);
        resetPasswordViewModel.resetpassword(Authorization,email,binding.password.getText().toString(),binding.cnfpassword.getText().toString())
                .observe(ResetPassword.this,
                        resetPasswordResponse -> {
                            Log.i("success","responce2");
                            ProgressBarUtils.hideProgressDialog();
                            if (resetPasswordResponse != null && !resetPasswordResponse.getError()) {
                                Log.i("success","responce3");
                                Toast.makeText(ResetPassword.this, resetPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(this,LoginActivity.class);
                                startActivity(i);
                                finish();
                            } else {

                                //  ProgressBarUtils.hideProgressDialog();
                                Log.i("fauled","fail");
                                Toast.makeText(ResetPassword.this, resetPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResetPassword.this, ForgotPasswordActivity.class));
        finish();
    }
}
