package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityEditprofileBinding;
import com.example.onlinedirectoryprovider.utils.CommonMethod;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.UpdateProfileViewModel;

public class EditprofileActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityEditprofileBinding binding;
    UpdateProfileViewModel updateProfileViewModel;
    String fname,lname,email,mobile,address,zipcode;
    String Authorization;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_editprofile);
        binding.editProfileHeader.imgback.setOnClickListener(this);
        binding.btnupdate.setOnClickListener(this);
        binding.editProfileHeader.txtToolBar.setText("Edit Profile");

        updateProfileViewModel = new ViewModelProvider(this).get(UpdateProfileViewModel.class);
        Authorization  = CommonMethod.getPreference(EditprofileActivity.this, "token");
       setData();

    }

    private void setData() {
        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            fname=bundle.getString("fname");
            lname=bundle.getString("lname");
            email=bundle.getString("email");
            mobile=bundle.getString("mobile");
            address=bundle.getString("address");
            zipcode=bundle.getString("zipcode");

            ShowData();

        }
    }

    private void ShowData() {
        binding.txtFirstName.setText(fname);
        binding.txtLastName.setText(lname);
        binding.txtEmail.setText(email);
        binding.txtMobile.setText(mobile);
        binding.txtAddress.setText(address);
        binding.txtZipCode.setText(zipcode);

    }


    @Override
    public void onClick(View v) {
        if (v == binding.editProfileHeader.imgback) {
            startActivity(new Intent(EditprofileActivity.this,PersonalDataActivity.class));
        }
        if (v == binding.btnupdate) {
            if (Util.checkConnection(this)) {
                if (isValidation()) {
                    getUpdateProfile();
                }
            } else {
                Toast.makeText(this, "Oops check your internet Connection", Toast.LENGTH_SHORT).show();
            }

        }

    }



        private void getUpdateProfile() {
            updateProfileViewModel.updateProfile(Authorization,binding.txtFirstName.getText().toString(),
                    binding.txtLastName.getText().toString(),email,binding.txtMobile.getText().toString(),
                    "2",binding.txtAddress.getText().toString(),binding.txtZipCode.getText().toString())
                    .observe(EditprofileActivity.this,
                            profileData -> {
                                if (profileData != null && !profileData.getError()) {
                                    Toast.makeText(this,"Profile Updated successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(this, PersonalDataActivity.class));
                                } else {
                                    Toast.makeText(this, "Failed Updated", Toast.LENGTH_SHORT).show();
                                }
                            });

        }
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public boolean isValidation() {
        if (binding.txtFirstName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "please enter firstname", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.txtLastName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "please enter lastname", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.txtMobile.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "please enter mobile no,", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.txtMobile.length() < 6 || binding.txtMobile.length() > 15) {
            Toast.makeText(this, "mobile no must be between 6 to 15 digit", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.txtAddress.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "please enter address", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.txtZipCode.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "please enter zipcode", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.txtZipCode.length() < 6 || binding.txtZipCode.length() > 10) {
            Toast.makeText(this, "zipcode must be 6 digit", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(EditprofileActivity.this,PersonalDataActivity.class));
    }


}




