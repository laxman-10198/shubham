package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityLoginBinding;
import com.example.onlinedirectoryprovider.utils.CommonMethod;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.LoginViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    String email=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.forgotpassword.setOnClickListener(this);
        binding.createaccount.setOnClickListener(this);
        binding.login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v==binding.forgotpassword){
            startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
        }
        if(v==binding.createaccount){
            startActivity(new Intent(LoginActivity.this,SignupActivity.class));
        }
        if(v==binding.login) {
            if (Util.checkConnection(this)) {
                if (isValidation()) {
                    getLogin();
                }
            } else {
                Toast.makeText(this, "Oops check your internet Connection", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void getLogin() {
        ProgressBarUtils.showProgressDialog(this);
        loginViewModel.getLogin("2",binding.email.getText().toString().trim(), binding.password.getText().toString()).observe(this,
                loginResponse -> {
                    if (loginResponse != null) {
                        try {
                            String code=loginResponse.getCode();
                            String v="200";
                            String u="401";
                            String m="202";
                            if(code.equals(v)){
                              // String error=loginResponse.getError();
                               // String message=loginResponse.getMessage();
                                String tokenFinal = "Bearer " + loginResponse.getResult().getToken();
                                //String id=loginResponse.getResult().getId();
                                //String name=loginResponse.getResult().getName();
                                //String firstname=loginResponse.getResult().getFirstName();
                               // String lastname=loginResponse.getResult().getLastName();
                                email=loginResponse.getResult().getEmail();
                               // String mobile=loginResponse.getResult().getMobile();
                                //String roleid=loginResponse.getResult().getRolesId();
                                //String templateid=loginResponse.getResult().getTemplateId();
                               // String otp=loginResponse.getResult().getOtp();
                               // String requestotp=loginResponse.getResult().getRequestOtp();
                                String verified=loginResponse.getResult().getVerified();
                              /*  String emailv=loginResponse.getResult().getEmailVerifiedAt();
                                String profileimage=loginResponse.getResult().getProfileImage();
                                String businessname=loginResponse.getResult().getBusinessName();
                                String slug=loginResponse.getResult().getSlug();
                                String smsgateway=loginResponse.getResult().getSmsGateway();
                                String address=loginResponse.getResult().getAddress();
                                String country=loginResponse.getResult().getCountry();
                                String state=loginResponse.getResult().getState();
                                String city=loginResponse.getResult().getCity();
                                String zipcode=loginResponse.getResult().getZipCode();
                                String location=loginResponse.getResult().getLocation();
                                String latitude=loginResponse.getResult().getLatitude();
                                String longitude=loginResponse.getResult().getLongitude();
                                String catid=loginResponse.getResult().getCategoryId();
                                String devicetype=loginResponse.getResult().getDeviceType();
                                String devicetoken=loginResponse.getResult().getToken();
                                String status=loginResponse.getResult().getStatus();
                                String create=loginResponse.getResult().getCreatedAt();
                                String update=loginResponse.getResult().getUpdatedAt();*/
                                String n="1";
                               // Toast.makeText(getApplicationContext(),"verified"+verified,Toast.LENGTH_LONG).show();
                                if(verified.equals(n)){
                                    Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG).show();
                                    CommonMethod.setPreference(LoginActivity.this, "token", tokenFinal);
                                    CommonMethod.setPreference(LoginActivity.this, "isLogin", "true");
                                    CommonMethod.setPreference(LoginActivity.this, CommonMethod.KEY_token,loginResponse.getResult().getDeviceToken());
                                    Intent intent = new Intent(LoginActivity.this, SubscriptionActivity.class);
                                    startActivity(intent);
                                }

                            }

                            else{
                                Toast.makeText(this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                ProgressBarUtils.hideProgressDialog();
                            }

                            if(code.equals(u)){
                                Toast.makeText(this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                  ProgressBarUtils.hideProgressDialog();
                            }
                            if(code.equals(m)){
                                String verified=loginResponse.getVerified();
                                String otp="0";
                                if(verified.equals(otp)){
                                    Toast.makeText(getApplicationContext(),"Please verify otp",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(LoginActivity.this,OtpActivity.class);
                                    intent.putExtra("email1",binding.email.getText().toString());
                                    startActivity(intent);
                                }
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    } else {
                        ProgressBarUtils.hideProgressDialog();
                        Toast.makeText(this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



    private boolean isValidation() {
        if (TextUtils.isEmpty(binding.email.getText().toString())) {
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isValidEmail(binding.email.getText().toString())) {
           Toast.makeText(this, "Please enter valid Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(binding.password.getText().toString())) {
           Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        startActivity(new Intent(LoginActivity.this,InfoActivity3.class));
    }




}