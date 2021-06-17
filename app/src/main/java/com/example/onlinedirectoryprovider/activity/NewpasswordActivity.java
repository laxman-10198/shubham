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
import com.example.onlinedirectoryprovider.databinding.ActivityNewpasswordBinding;
import com.example.onlinedirectoryprovider.utils.CommonMethod;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.NewPasswordViewModel;

public class NewpasswordActivity extends AppCompatActivity implements View.OnClickListener {
    NewPasswordViewModel newPasswordViewModel;
    String Authorization;
    ActivityNewpasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_newpassword);
        newPasswordViewModel = new ViewModelProvider(NewpasswordActivity.this).get(NewPasswordViewModel.class);
        binding.btnchange.setOnClickListener(this);
        binding.newPasswordHeader.txtToolBar.setText("Change Password");

        Authorization  = CommonMethod.getPreference(NewpasswordActivity.this, "token");
        binding.newPasswordHeader.imgback.setOnClickListener(this);
        Log.i("TOKENNNNNNN",Authorization);
    }


    @Override
    public void onClick(View v) {
        if (v == binding.newPasswordHeader.imgback) {
            finish();
        }
        if (v == binding.btnchange) {
            if (Util.checkConnection(NewpasswordActivity.this)) {
                if (isValidation()) {
                    getChangePassword();
                }
            }
        }
    }
    private boolean isValidation () {
        if (TextUtils.isEmpty(binding.currentpassword.getText().toString())) {
            Toast.makeText(NewpasswordActivity.this, "Please enter current password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(binding.password.getText().toString())) {
            Toast.makeText(NewpasswordActivity.this, "Please enter new password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.password.length() < 8 || binding.password.length() > 16) {
            Toast.makeText(this, "Password must be between 8 to 16 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(binding.cnfpassword.getText().toString())) {
            Toast.makeText(NewpasswordActivity.this, "Please enter Confirm Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!(binding.password.getText().toString().equals(binding.cnfpassword.getText().toString()))){
            Toast.makeText(NewpasswordActivity.this,"Password and Confirm Password do not match",Toast.LENGTH_LONG).show();
            return false;

        }
        return true;
    }

    private void getChangePassword() {

        ProgressBarUtils.showProgressDialog(this);
        newPasswordViewModel.changepassword(Authorization,binding.currentpassword.getText().toString(),
                binding.password.getText().toString(), binding.cnfpassword.getText().toString())
                .observe(NewpasswordActivity.this,
                        changePasswordResponse -> {
                            ProgressBarUtils.hideProgressDialog();
                            if (changePasswordResponse != null && !changePasswordResponse.getError()) {
                                Toast.makeText(NewpasswordActivity.this, changePasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(NewpasswordActivity.this, ProfileActivity.class));
                            } else {
                                 ProgressBarUtils.hideProgressDialog();
                                Toast.makeText(NewpasswordActivity.this, changePasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

    }

    @Override
    public void onBackPressed () {
        finish();
    }
}

