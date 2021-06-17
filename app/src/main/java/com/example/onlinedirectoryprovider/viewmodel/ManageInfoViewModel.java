package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.manageinfo.ManageInfoResponse;
import com.example.onlinedirectoryprovider.repositories.ManageInfoRepository;

public class ManageInfoViewModel extends AndroidViewModel {
    ManageInfoRepository manageInfoRepository;
    public ManageInfoViewModel(@NonNull Application application) {
        super(application);
        this.manageInfoRepository = new ManageInfoRepository();
    }
    public LiveData<ManageInfoResponse> manageInfo(String Authorization, String website_url,String details,
                                                   String opening_time,String closing_time,String average_charges,
                                                   String website_logo) {
        return manageInfoRepository.manageInfoResponseLiveData(Authorization,website_url,details,
                opening_time,closing_time,average_charges,
                website_logo);
    }
}
