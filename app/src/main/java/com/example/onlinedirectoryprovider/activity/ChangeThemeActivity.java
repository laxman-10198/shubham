package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityChangeThemeBinding;

public class ChangeThemeActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityChangeThemeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_change_theme);
        binding.btnchangetheme.setOnClickListener(this);
        binding.imgprofile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==binding.btnchangetheme){
            startActivity(new Intent(ChangeThemeActivity.this,SelectThemeActivity.class));
        }
        if(v==binding.imgprofile){
            startActivity(new Intent(ChangeThemeActivity.this,ProfileActivity.class));
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ChangeThemeActivity.this,ViewYourThemeActivity.class));
    }
}