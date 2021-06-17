package com.example.onlinedirectoryprovider.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.forgotpassword.ForgotPasswordResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordRepository {
    private  String TAG =  com.example.onlinedirectoryprovider.repositories.ForgotPasswordRepository.class.getSimpleName();

    public ForgotPasswordRepository(){

    }
    public LiveData<ForgotPasswordResponse> forgotPasswordResponseLiveData(String email,String role){

        final MutableLiveData<ForgotPasswordResponse> mutableLiveData=new MutableLiveData<>();
        ApiInterface apiInterface= RetrofitConnection.getInstance().createService();
        Call<ForgotPasswordResponse> mCall= apiInterface.forgotPassword(email,role);
        mCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                } else {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;

    }
}
