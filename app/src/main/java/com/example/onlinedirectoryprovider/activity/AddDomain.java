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
import com.example.onlinedirectoryprovider.databinding.ActivityAddDomainBinding;
import com.example.onlinedirectoryprovider.utils.CommonMethod;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.AddDomainViewModel;

public class AddDomain extends AppCompatActivity implements View.OnClickListener {

    ActivityAddDomainBinding binding;
    AddDomainViewModel addDomainViewModel;
    String Authorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_domain);
        addDomainViewModel = new ViewModelProvider(this).get(AddDomainViewModel.class);
        binding.btnsave.setOnClickListener(this);
        binding.btnskip.setOnClickListener(this);

        Authorization  = CommonMethod.getPreference(this,"token");
    }


    @Override
    public void onClick(View v) {
        if (v == binding.btnsave) {
                if (Util.checkConnection(this)) {
                    if (isValidation()) {
                        getAddDomain();
                    }
                } else {
                    Toast.makeText(this, "Oops check your internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        if (v == binding.btnskip) {
            startActivity(new Intent(AddDomain.this,SelectThemeActivity.class));
        }
        }


    private void getAddDomain() {
        ProgressBarUtils.showProgressDialog(this);
        addDomainViewModel.addDomain(Authorization,
                binding.domainname.getText().toString())
                .observe(this,
                        addDomainResponse -> {
                            if (addDomainResponse != null && !addDomainResponse.getError()) {
                                Log.i("sendsuccess","success");
                                ProgressBarUtils.hideProgressDialog();
                                Toast.makeText(AddDomain.this, addDomainResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddDomain.this, SelectThemeActivity.class);
                                startActivity(intent);

                            } else {
                                ProgressBarUtils.hideProgressDialog();
                                Toast.makeText(this,"failed", Toast.LENGTH_SHORT).show();
                            }
                        });

    }


    private boolean isValidation() {
        if (binding.domainname.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter domain name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
        public void onBackPressed() {
            startActivity(new Intent(AddDomain.this,SubscriptionActivity.class));

    }
}