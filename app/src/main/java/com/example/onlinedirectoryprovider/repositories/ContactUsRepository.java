package com.example.onlinedirectoryprovider.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.contactus.ContactUsResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsRepository {


    private   String TAG= ContactUsRepository.class.getSimpleName();
    public ContactUsRepository(){

    }

    public LiveData<ContactUsResponse> contactUsResponseLiveData(String Authorization, String name, String email, String subject, String message){
        final MutableLiveData<ContactUsResponse> mutableLiveData=new MutableLiveData<>();
        ApiInterface apiInterface= RetrofitConnection.getInstance().createService();
        Call<ContactUsResponse> mCall=apiInterface.ContactUs(Authorization,name,email,subject,message);
        mCall.enqueue(new Callback<ContactUsResponse>() {

            @Override
            public void onResponse(Call<ContactUsResponse> call, Response<ContactUsResponse> response) {
                if (response.isSuccessful()){
                    mutableLiveData.setValue(response.body());
                }
                else {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ContactUsResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
