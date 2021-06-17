package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.resetpassword.ResetPasswordResponse;
import com.example.onlinedirectoryprovider.repositories.ResetPasswordRepository;

public class ResetPasswordViewModel extends AndroidViewModel {
    ResetPasswordRepository resetPasswordRepository;
    public ResetPasswordViewModel(@NonNull Application application) {
        super(application);
        this.resetPasswordRepository = new ResetPasswordRepository();
    }
    public LiveData<ResetPasswordResponse> resetpassword(String Authorization,String email,String password, String cnfpassword) {
        return resetPasswordRepository.resetPasswordResponseLiveData(Authorization,email,password,cnfpassword);
    }
}
