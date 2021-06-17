package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivitySignupBinding;
import com.example.onlinedirectoryprovider.utils.CommonMethod;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.SignUpViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySignupBinding binding;
    SignUpViewModel signupViewModel;
    String email1="";
    String temp="";
    String category="";

    String[] categoryid = {"Beautician", "BPO", "Cashier","Delivery Jobs","Driver"," IT Hardware","IT Software","Life Insurance","Nurse","Sales","Teacher/lecturer"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        signupViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding.btnsignup.setOnClickListener(this);
        binding.login.setOnClickListener(this);
        CommonMethod.setPreference(this,"verify","0");

        Spinner spinner=findViewById(R.id.categoryid);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryid);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=arrayAdapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v==binding.login){
            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
        }

        if(v==binding.btnsignup){
            if (Util.checkConnection(this)) {
                if (isValidation()) {
                    getRegister();
                }
            } else {
                Toast.makeText(this, "Oops check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void getRegister() {
        String deviceId1 = null;

      try{
          String deviceId = Settings.Secure.getString(
                  this.getContentResolver(),
                  Settings.Secure.ANDROID_ID);
          Log.i("deviceid", deviceId);
          deviceId1=deviceId;
      }
      catch (Exception e){
          e.printStackTrace();

      }
      ProgressBarUtils.showProgressDialog(this);
      email1=binding.email.getText().toString();
      temp=binding.countryCodeHolder.getSelectedCountryCode()+binding.mobilenumber.getText().toString();
      signupViewModel.getRegister(
                binding.firstname.getText().toString(),
                binding.lastname.getText().toString(),
                email1,
                binding.password.getText().toString(),
                binding.cnfpassword.getText().toString(),
                temp,
                binding.bussinessname.getText().toString(),
                "2",
                category,
                binding.address.getText().toString(),
                binding.zipcode.getText()
                .toString(),"28.4594965",
                "77.0266383",2,deviceId1)
                .observe(this,
                        signupResult -> {
                            if (signupResult != null) {

                                try {
                                    String code =signupResult.getCode();
                                    String v="200";
                                    String ar="400";

                                    if(code.equals(v)){
                                        ProgressBarUtils.hideProgressDialog();
                                        String sOtp = signupResult.getOtp();
                                        String error = signupResult.getError();
                                        String message = signupResult.getMessage();
                                        Log.i("Otp",sOtp);
                                        Log.i("Error",error);
                                        Log.i("Message",message);

                                        Toast.makeText(this, "User registered successfully and OTP has been sent on your mobile number", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignupActivity.this, OtpActivity.class);
                                        intent.putExtra("otp", sOtp);
                                        intent.putExtra("email1",email1);
                                        startActivity(intent);

                                    }
                                    if(code.equals(ar)){
                                        ProgressBarUtils.hideProgressDialog();
                                        Toast.makeText(this, signupResult.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                              else {
                                ProgressBarUtils.hideProgressDialog();
                                Toast.makeText(this, signupResult.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    private boolean isValidation() {
        if (binding.firstname.getText().toString().trim().isEmpty()){
           Toast.makeText(this, "Please enter first name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.lastname.getText().toString().trim().isEmpty()){
           Toast.makeText(this, "Please enter last name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(binding.email.getText().toString())) {
           Toast.makeText(this, "Please enter email id", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isValidEmail(binding.email.getText().toString())){
            Toast.makeText(this, "Enter correct Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.password.getText().toString().trim().isEmpty()) {
           Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.password.length() < 8 || binding.password.length() > 16) {
            Toast.makeText(this, "Password must be between 8 to 16 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.cnfpassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!(binding.password.getText().toString().equals(binding.cnfpassword.getText().toString()))){
            Toast.makeText(SignupActivity.this,"Password and confirm password do not match",Toast.LENGTH_LONG).show();
            return false;
        }
        if (binding.mobilenumber.getText().toString().trim().isEmpty() && binding.countryCodeHolder.getTextView_selectedCountry().isTextSelectable()) {
           Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.mobilenumber.getText().length() < 6 || binding.mobilenumber.getText().length() > 15) {
            Toast.makeText(this, "Mobile number must be between 6 to 15 digits.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.bussinessname.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Enter business name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.address.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter address", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.zipcode.getText().toString().isEmpty()) {
           Toast.makeText(this, "Please enter zip code", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.zipcode.length() < 6 || binding.zipcode.length() > 10) {
            Toast.makeText(this, "Zip code must be of 6 digits", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.categoryid.toString().isEmpty()) {
           Toast.makeText(this, "Please select category", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
    }
}

