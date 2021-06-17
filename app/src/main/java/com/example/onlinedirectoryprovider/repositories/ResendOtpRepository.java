package com.example.onlinedirectoryprovider.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.resendotp.ResendOtpResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResendOtpRepository {
    private String TAG= com.example.onlinedirectoryprovider.repositories.ResendOtpRepository.class.getSimpleName();

    public ResendOtpRepository() {
    }

    public LiveData<ResendOtpResponse> resendOTPLivedata(String email, String type) {
        final MutableLiveData<ResendOtpResponse> mutableLiveData = new MutableLiveData<>();
        ApiInterface apiService = RetrofitConnection.getInstance().createService();
        Call<ResendOtpResponse> call = apiService.resendOTP(email,type);
        call.enqueue(new Callback<ResendOtpResponse>() {
            @Override
            public void onResponse(@NonNull Call<ResendOtpResponse> call, @NonNull Response<ResendOtpResponse> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                } else {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResendOtpResponse> call, @NonNull Throwable t) {
            }
        });
        return mutableLiveData;
    }

}
