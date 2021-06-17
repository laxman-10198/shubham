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
import com.example.onlinedirectoryprovider.databinding.ActivityPersonaldataBinding;
import com.example.onlinedirectoryprovider.utils.CommonMethod;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.GetProfileViewModel;

public class PersonalDataActivity extends AppCompatActivity implements  View.OnClickListener {

    ActivityPersonaldataBinding binding;
    GetProfileViewModel getProfileViewModel;
    String Authorization;
    String fname,lname,email,mobile,address,zipcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_personaldata);
        binding.txtEditProfile.setOnClickListener(this);
        binding.imgback.setOnClickListener(this);
        getProfileViewModel = new ViewModelProvider(this).get(GetProfileViewModel.class);


        Authorization  = CommonMethod.getPreference(PersonalDataActivity.this, "token");


        getProfile();



    }
    private void getProfile() {
        if (Util.checkConnection(this)) {
             ProgressBarUtils.showProgressDialog(this);
             getProfileViewModel.getProfile(Authorization)
                    .observe(this,
                            getProfileResponse -> {
                                 Log.i("shubham","getprofilesuccess");
                                 ProgressBarUtils.hideProgressDialog();
                                if (getProfileResponse != null && !getProfileResponse.getError()) {
                                    binding.txtFirstName.setText(getProfileResponse.getResult().getFirstName());
                                    binding.txtLastName.setText(getProfileResponse.getResult().getLastName());
                                    binding.txtEmail.setText(getProfileResponse.getResult().getEmail());
                                    binding.txtMobile.setText(getProfileResponse.getResult().getMobile());
                                    binding.txtAddress.setText(getProfileResponse.getResult().getLocation());
                                    binding.txtZipCode.setText(getProfileResponse.getResult().getZipCode());

                                    fname=getProfileResponse.getResult().getFirstName();
                                    lname=getProfileResponse.getResult().getLastName();
                                    email=getProfileResponse.getResult().getEmail();
                                    mobile=getProfileResponse.getResult().getMobile();
                                    address=getProfileResponse.getResult().getLocation();
                                    zipcode=getProfileResponse.getResult().getZipCode();

                                } else {
                                    Log.i("shubham","failure");
                                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
                                }
                            });
        } else {
            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v==binding.txtEditProfile){
            Bundle bundle=new Bundle();
            bundle.putString("fname",fname);
            bundle.putString("lname",lname);
            bundle.putString("email",email);
            bundle.putString("mobile",mobile);
            bundle.putString("address",address);
            bundle.putString("zipcode",zipcode);
            Intent intent=new Intent(PersonalDataActivity.this,EditprofileActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }
        if (v==binding.imgback){
           startActivity(new Intent(PersonalDataActivity.this,ProfileActivity.class));
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(PersonalDataActivity.this,ProfileActivity.class));
    }

}