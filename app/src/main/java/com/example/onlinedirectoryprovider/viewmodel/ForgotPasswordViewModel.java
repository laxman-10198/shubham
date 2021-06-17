package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.forgotpassword.ForgotPasswordResponse;
import com.example.onlinedirectoryprovider.repositories.ForgotPasswordRepository;

public class ForgotPasswordViewModel extends AndroidViewModel {
    ForgotPasswordRepository forgotPasswordRepository;
    public ForgotPasswordViewModel(@NonNull Application application) {
        super(application);
        this.forgotPasswordRepository=new ForgotPasswordRepository();
    }
     public LiveData<ForgotPasswordResponse> forgotPassword(String email,String role){
        return  forgotPasswordRepository.forgotPasswordResponseLiveData(email,role);
}
}
