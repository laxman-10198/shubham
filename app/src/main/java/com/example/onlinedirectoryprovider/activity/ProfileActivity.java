package com.example.onlinedirectoryprovider.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityProfileBinding;
import com.example.onlinedirectoryprovider.utils.CommonMethod;

public class ProfileActivity extends AppCompatActivity implements  View.OnClickListener {
    ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.conEditProfile.setOnClickListener(this);
        binding.conChangePassword.setOnClickListener(this);
        binding.conTermsCondition.setOnClickListener(this);
        binding.conManageInformation.setOnClickListener(this);
        binding.conQuestion.setOnClickListener(this);
        binding.conAdddomain.setOnClickListener(this);
        binding.toolbar.txtToolBar.setText("Profile");
        binding.toolbar.imgback.setOnClickListener(this);
        binding.contactus.setOnClickListener(this);
        binding.btnlogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == binding.conEditProfile) {
            startActivity(new Intent(ProfileActivity.this, PersonalDataActivity.class));
        }
        if (v == binding.conChangePassword) {
            startActivity(new Intent(ProfileActivity.this, NewpasswordActivity.class));
        }
        if (v == binding.conTermsCondition) {
            startActivity(new Intent(ProfileActivity.this, TandCActivity.class));
        }
        if (v == binding.conManageInformation) {
            startActivity(new Intent(ProfileActivity.this, ManageInformationActivity.class));
        }
        if (v == binding.conQuestion) {
            startActivity(new Intent(ProfileActivity.this, FAQActivity.class));
        }
        if (v == binding.conAdddomain) {
            startActivity(new Intent(ProfileActivity.this, AddDomain.class));
        }
        if (v == binding.toolbar.imgback) {
            startActivity(new Intent(ProfileActivity.this,ChangeThemeActivity.class));

        }
        if (v == binding.contactus) {
            startActivity(new Intent(ProfileActivity.this, ContactUsActivity.class));
        }
        if (v == binding.btnlogout) {
            showDialog();
        }
    }
        private void showDialog() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure you want to Logout ? ");
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(DialogInterface arg0, int arg1) {

                    CommonMethod.clearSharedPreference(ProfileActivity.this);
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                  //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finishAffinity();
                   // CommonMethod.setPreference(ProfileActivity.this, KEY_loginStatus, "false");
                }
            });
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProfileActivity.this,ChangeThemeActivity.class));
    }
}