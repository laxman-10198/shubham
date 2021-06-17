package com.example.onlinedirectoryprovider.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.faqs.FaqsResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqsRepository {


    private   String TAG= FaqsRepository.class.getSimpleName();
    public FaqsRepository(){

    }

    public LiveData<FaqsResponse> faqsResponseLiveData(String Authorization){
        final MutableLiveData<FaqsResponse> mutableLiveData=new MutableLiveData<>();
        ApiInterface apiInterface= RetrofitConnection.getInstance().createService();
        Call<FaqsResponse> mCall=apiInterface.faqs(Authorization);
        mCall.enqueue(new Callback<FaqsResponse>() {
            @Override
            public void onResponse(Call<FaqsResponse> call, Response<FaqsResponse> response) {
             if (response.isSuccessful()){
               mutableLiveData.setValue(response.body());
             }
             else {
                 mutableLiveData.setValue(response.body());
             }
            }

            @Override
            public void onFailure(Call<FaqsResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
