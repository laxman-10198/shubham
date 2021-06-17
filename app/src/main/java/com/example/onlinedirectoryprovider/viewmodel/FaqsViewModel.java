package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.faqs.FaqsResponse;
import com.example.onlinedirectoryprovider.repositories.FaqsRepository;

public class FaqsViewModel extends AndroidViewModel {
    FaqsRepository faqsRepository;
    public FaqsViewModel(@NonNull Application application) {
        super(application);
        this.faqsRepository = new FaqsRepository();
    }
    public LiveData<FaqsResponse> faqs(String Authorization) {
        return faqsRepository.faqsResponseLiveData(Authorization);
    }
}
