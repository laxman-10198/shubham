package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.selecttheme.SelectThemeResponse;
import com.example.onlinedirectoryprovider.repositories.SelectThemeRepository;

public class SelectThemeViewModel extends AndroidViewModel {
    SelectThemeRepository selectThemeRepository;
    public SelectThemeViewModel(@NonNull Application application) {
        super(application);
        this.selectThemeRepository = new SelectThemeRepository();
    }
    public LiveData<SelectThemeResponse> selecttheme(String Authorization) {
        return selectThemeRepository.selectThemeResponseLiveData(Authorization);
    }
}
