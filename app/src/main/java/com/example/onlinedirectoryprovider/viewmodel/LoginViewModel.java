package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.login.LoginResponse;
import com.example.onlinedirectoryprovider.repositories.LoginRepository;

public class LoginViewModel extends AndroidViewModel {
   LoginRepository loginRepository;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.loginRepository= new LoginRepository();

    }
    public LiveData<LoginResponse> getLogin(String role,String email, String password){
        return  loginRepository.loginResponseLiveData(role,email, password);
    }



}
