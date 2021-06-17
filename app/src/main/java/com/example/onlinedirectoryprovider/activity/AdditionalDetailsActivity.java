package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityAdditionalDetailsBinding;

public class AdditionalDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityAdditionalDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_additional_details);
        binding.time1.setOnClickListener(this);
        binding.time2.setOnClickListener(this);
        binding.time3.setOnClickListener(this);
        binding.time4.setOnClickListener(this);
        binding.time5.setOnClickListener(this);
        binding.time6.setOnClickListener(this);
        binding.btnsave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==binding.time1){
            binding.time1.setBackgroundResource(R.drawable.timebtn);
            binding.time2.setBackgroundResource(R.drawable.graybtn);
            binding.time3.setBackgroundResource(R.drawable.graybtn);
        }
        if(v==binding.time2){
            binding.time2.setBackgroundResource(R.drawable.timebtn);
            binding.time1.setBackgroundResource(R.drawable.graybtn);
            binding.time3.setBackgroundResource(R.drawable.graybtn);

        }
        if(v==binding.time3){

            binding.time3.setBackgroundResource(R.drawable.timebtn);
            binding.time1.setBackgroundResource(R.drawable.graybtn);
            binding.time2.setBackgroundResource(R.drawable.graybtn);
        }
        if(v==binding.time4){
            binding.time4.setBackgroundResource(R.drawable.timebtn);
            binding.time5.setBackgroundResource(R.drawable.graybtn);
            binding.time6.setBackgroundResource(R.drawable.graybtn);

        }
        if(v==binding.time5){
            binding.time5.setBackgroundResource(R.drawable.timebtn);
            binding.time4.setBackgroundResource(R.drawable.graybtn);
            binding.time6.setBackgroundResource(R.drawable.graybtn);
        }
        if(v==binding.time6){
            binding.time6.setBackgroundResource(R.drawable.timebtn);
            binding.time4.setBackgroundResource(R.drawable.graybtn);
            binding.time5.setBackgroundResource(R.drawable.graybtn);
        }
        if(v==binding.btnsave){
           startActivity(new Intent(AdditionalDetailsActivity.this,ChangeThemeActivity.class));
        }
    }
}