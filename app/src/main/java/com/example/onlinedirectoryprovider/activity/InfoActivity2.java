package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityInfo2Binding;

public class InfoActivity2 extends AppCompatActivity implements View.OnClickListener  {
ActivityInfo2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_info2);
        binding.btnnext.setOnClickListener(this);
        binding.txtskip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==binding.btnnext){
            startActivity(new Intent(InfoActivity2.this,InfoActivity3.class));
        }
        if(v==binding.txtskip){
            startActivity(new Intent(InfoActivity2.this,LoginActivity.class));
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(InfoActivity2.this,InfoActivity.class));    }
    }
