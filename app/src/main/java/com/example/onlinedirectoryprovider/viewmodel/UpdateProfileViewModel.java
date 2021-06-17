package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.updateprofile.UpdateProfileResponse;
import com.example.onlinedirectoryprovider.repositories.UpdateProfileRepository;

public class UpdateProfileViewModel extends AndroidViewModel {
    UpdateProfileRepository updateProfileRepository;
    public UpdateProfileViewModel(@NonNull Application application) {
        super(application);
        this.updateProfileRepository = new UpdateProfileRepository();
    }
    public  LiveData<UpdateProfileResponse> updateProfile(String token,String firstname, String lastname, String email, String mobile, String role, String location, String zipcode) {
        return updateProfileRepository.updateProfileResponseLiveData(token,firstname,lastname,email,mobile,role,location,zipcode);
    }
}
