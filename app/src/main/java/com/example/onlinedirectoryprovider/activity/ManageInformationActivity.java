package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityManageInformationBinding;
import com.example.onlinedirectoryprovider.utils.CommonMethod;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.ManageInfoViewModel;

public class ManageInformationActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityManageInformationBinding binding;
    ManageInfoViewModel manageInfoViewModel;
    String Authorization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_information);
        manageInfoViewModel = new ViewModelProvider(this).get(ManageInfoViewModel.class);
        binding.manageInfoHeader.txtToolBar.setText("Manage Information");
        binding.btnsubmit.setOnClickListener(this);
        binding.file1.setOnClickListener(this);
        binding.messageText.setOnClickListener(this);
        binding.manageInfoHeader.imgback.setOnClickListener(this);
        Authorization = CommonMethod.getPreference(ManageInformationActivity.this, "token");

    }

    @Override
    public void onClick(View v) {
        if (v == binding.manageInfoHeader.imgback) {
            finish();
        }
         if (v == binding.file1) {

        }


        if (v == binding.btnsubmit) {
            if (Util.checkConnection(this)) {
                if (isValidation()) {
                    getManageInfo();
                }
            } else {
                Toast.makeText(this, "Oops check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void getManageInfo() {

        ProgressBarUtils.showProgressDialog(this);
        manageInfoViewModel.manageInfo(Authorization,
                binding.etWebsite.getText().toString(),
                binding.etDetails.getText().toString(),
                binding.etOpening.getText().toString(),
                binding.etClosing.getText().toString(),
                binding.etAverageCharges.getText().toString(),
                "dfvs.png")
                .observe(this,
                        manageInfoResponse -> {
                            if (manageInfoResponse != null && !manageInfoResponse.getError()) {
                                ProgressBarUtils.hideProgressDialog();
                                Toast.makeText(this, manageInfoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ManageInformationActivity.this, ProfileActivity.class);
                                startActivity(intent);

                            } else {
                                ProgressBarUtils.hideProgressDialog();
                                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    private boolean isValidation() {
        if (binding.etWebsite.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter website link", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.etDetails.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter details", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.etOpening.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter opening time", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.etClosing.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter closing time", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.etAverageCharges.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter average charges", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}