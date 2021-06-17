package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.getprofile.GetProfileResponse;
import com.example.onlinedirectoryprovider.repositories.GetProfileRepository;

public class GetProfileViewModel extends AndroidViewModel {
    GetProfileRepository getProfileRepository;
    public GetProfileViewModel(@NonNull Application application) {
        super(application);
        this.getProfileRepository = new GetProfileRepository();
    }
    public LiveData<GetProfileResponse> getProfile(String Authorization) {
        return getProfileRepository.getProfileResponseLiveData(Authorization);
    }
}
