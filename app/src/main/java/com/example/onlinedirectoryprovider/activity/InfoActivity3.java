package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityInfo3Binding;

public class InfoActivity3 extends AppCompatActivity implements View.OnClickListener {
ActivityInfo3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_info3);
        binding.btngetstart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==binding.btngetstart){
            startActivity(new Intent(InfoActivity3.this,LoginActivity.class));
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(InfoActivity3.this,InfoActivity2.class));    }
}