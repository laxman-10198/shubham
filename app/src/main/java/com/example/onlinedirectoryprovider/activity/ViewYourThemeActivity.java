package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityViewYourThemeBinding;

public class ViewYourThemeActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityViewYourThemeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_your_theme);
        binding.btnupload.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==binding.btnupload){
            startActivity(new Intent(ViewYourThemeActivity.this,ChangeThemeActivity.class));
        }


    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewYourThemeActivity.this,SelectThemeActivity.class));
    }
}