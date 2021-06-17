package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.tandc.TermsAndConditionsResponse;
import com.example.onlinedirectoryprovider.repositories.TermsAndConditionsRepository;

public class TermsAndConditionsViewModel extends AndroidViewModel {
    TermsAndConditionsRepository termsAndConditionsRepository;

    public TermsAndConditionsViewModel(@NonNull Application application) {
        super(application);
        this.termsAndConditionsRepository= new TermsAndConditionsRepository();
    }
    public LiveData<TermsAndConditionsResponse> tandc(String Authorization) {
        return termsAndConditionsRepository.termsAndConditionsResponseLiveData(Authorization);
    }
}
