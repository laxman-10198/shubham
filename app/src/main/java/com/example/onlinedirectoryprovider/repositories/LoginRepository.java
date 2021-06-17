package com.example.onlinedirectoryprovider.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.login.LoginResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private String TAG=  com.example.onlinedirectoryprovider.repositories.LoginRepository.class.getSimpleName();
    public LoginRepository(){

    }
    public LiveData<LoginResponse> loginResponseLiveData(String role,String userEmail, String userPassword){
        final MutableLiveData<LoginResponse> mutableLiveData= new MutableLiveData<>();
        ApiInterface apiInterface= RetrofitConnection.getInstance().createService();
        Call<LoginResponse> mCall= apiInterface.getLogin(role,userEmail,userPassword);
        mCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    mutableLiveData.setValue(response.body());
                }
                else {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
