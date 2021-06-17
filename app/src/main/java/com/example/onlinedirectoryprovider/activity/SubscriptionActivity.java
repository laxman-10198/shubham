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
import com.example.onlinedirectoryprovider.databinding.ActivitySubscriptionBinding;

public class SubscriptionActivity extends AppCompatActivity implements View.OnClickListener{

    ActivitySubscriptionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subscription);
        binding.btntrial.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==binding.btntrial){
            startActivity(new Intent(SubscriptionActivity.this, AddDomain.class));
        }

    }

    @Override
    public void onBackPressed() {
       showDialog();
    }
    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to Quit ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                finishAffinity();

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
}