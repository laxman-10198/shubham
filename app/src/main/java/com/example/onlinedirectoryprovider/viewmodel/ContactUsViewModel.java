package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.contactus.ContactUsResponse;
import com.example.onlinedirectoryprovider.repositories.ContactUsRepository;

public class ContactUsViewModel extends AndroidViewModel {
    ContactUsRepository contactUsRepository;
    public ContactUsViewModel(@NonNull Application application) {
        super(application);
        this.contactUsRepository = new ContactUsRepository();
    }
    public LiveData<ContactUsResponse> ContactUs(String Authorization,String name, String email, String subject, String message) {
        return contactUsRepository.contactUsResponseLiveData(Authorization,name,email,subject,message);
    }
}
