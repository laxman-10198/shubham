package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.register.RegisterResponse;
import com.example.onlinedirectoryprovider.repositories.SignUpRepository;

public class SignUpViewModel extends AndroidViewModel {
    private SignUpRepository signUpRepository;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        signUpRepository = new SignUpRepository();

    }

    public LiveData<RegisterResponse> getRegister(String firstName,String lastname, String userEmail, String userPassword, String userCnfpassword, String userMobile, String userBusinessname, String  role, String  category_id,
                                                  String  userAddress, String  userZipcode, String  latitude, String  longitude, int  device_type, String  device_token){

        return signUpRepository.getMutableLiveData(firstName,lastname,userEmail,userPassword,userCnfpassword,userMobile,userBusinessname,role, category_id,
                userAddress,userZipcode,latitude,longitude,device_type,device_token);
    }



}


