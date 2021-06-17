package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.newpassword.ChangePasswordResponse;
import com.example.onlinedirectoryprovider.repositories.NewPasswordRepository;

public class NewPasswordViewModel extends AndroidViewModel {
    NewPasswordRepository newPasswordRepository;
    public NewPasswordViewModel(@NonNull Application application) {
        super(application);
        this.newPasswordRepository = new NewPasswordRepository();
    }
    public LiveData<ChangePasswordResponse> changepassword(String Authorization,String currentpassword,String password,String cnfpassword) {
        return  newPasswordRepository.changePasswordResponseLiveData(Authorization,currentpassword,password,cnfpassword);
    }
}
