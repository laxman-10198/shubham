package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.utils.CommonMethod;

public class SplashActivity extends AppCompatActivity {
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        /*if (Util.checkConnection(this)) {
            if (Util.checkRequestPermission(getApplicationContext(), SplashActivity.this)) {
                doPermissionGranted();
            }
        } else {
            Util.getShowToast(this, getResources().getString(R.string.check_internet));
        }
    }*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (CommonMethod.getPreference(SplashActivity.this, "isLogin") != null) {
                    if (CommonMethod.getPreference(SplashActivity.this, "isLogin").equals("true")) {
                        Intent intent = new Intent(SplashActivity.this, SubscriptionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Intent intent = new Intent(SplashActivity.this, InfoActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

            }
        }, 3000);
    }
}