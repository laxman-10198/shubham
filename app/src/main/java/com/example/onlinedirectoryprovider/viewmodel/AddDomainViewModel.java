package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.adddomain.AddDomainResponse;
import com.example.onlinedirectoryprovider.repositories.AddDomainRepository;

public class AddDomainViewModel extends AndroidViewModel {
    AddDomainRepository addDomainRepository;
    public AddDomainViewModel(@NonNull Application application) {
        super(application);
        this.addDomainRepository = new AddDomainRepository();
    }
    public LiveData<AddDomainResponse> addDomain(String Authorization, String domainname) {
        return addDomainRepository.addDomainResponseLiveData(Authorization,domainname);
    }
}
