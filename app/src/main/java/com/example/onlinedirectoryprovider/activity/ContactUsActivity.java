package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityContactUsBinding;
import com.example.onlinedirectoryprovider.utils.CommonMethod;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.ContactUsViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityContactUsBinding binding;
    ContactUsViewModel contactUsViewModel;
    String Authorization;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_contact_us);
        contactUsViewModel = new ViewModelProvider(this).get(ContactUsViewModel.class);
        binding.contactHeader.imgback.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
        binding.contactHeader.txtToolBar.setText("Contact Us");

        Authorization  = CommonMethod.getPreference(this,"token");
    }

    @Override
    public void onClick(View v) {
        if(v==binding.contactHeader.imgback){
            finish();
        }
        if(v==binding.btnSend){
            if (Util.checkConnection(this)) {
                if (isValidation()) {
                    getContactUs();
                }
            } else {
                Toast.makeText(this, "Oops check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void getContactUs() {

        ProgressBarUtils.showProgressDialog(this);
        contactUsViewModel.ContactUs(Authorization,
                binding.etName.getText().toString(),
                binding.etEmail.getText().toString(),
                binding.etSubject.getText().toString(),
                binding.etMessage.getText().toString())
                .observe(this,
                        contactUsResponse -> {
                            if (contactUsResponse != null && !contactUsResponse.getError()) {
                                ProgressBarUtils.hideProgressDialog();
                                Toast.makeText(this, "Thanks Your message has been sent Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ContactUsActivity.this, ProfileActivity.class);
                                startActivity(intent);

                            } else {
                                ProgressBarUtils.hideProgressDialog();
                                Toast.makeText(this,"failed", Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    private boolean isValidation() {
        if (binding.etName.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(binding.etEmail.getText().toString())) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isValidEmail(binding.etEmail.getText().toString())){
            Toast.makeText(this, "Enter correct Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.etSubject.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter subject", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.etMessage.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter message", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean isValidEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}


