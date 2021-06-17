package com.example.onlinedirectoryprovider.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.verifyotp.VerifyOtpResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VerifyOtpRepository {
    private  String TAG = com.example.onlinedirectoryprovider.repositories.VerifyOtpRepository.class.getSimpleName();

    public VerifyOtpRepository(){

    }
    public LiveData<VerifyOtpResponse> OtpResponseLiveData(String email, String otp,String type){
        final MutableLiveData<VerifyOtpResponse> mutableLiveData= new MutableLiveData<>();
        ApiInterface apiInterface = RetrofitConnection.getInstance().createService();
        Call<VerifyOtpResponse> mCall= apiInterface.getVerifyOtp(email,otp,type);
        mCall.enqueue(new Callback<VerifyOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
         if (response.isSuccessful()){
             mutableLiveData.setValue(response.body());
         }
         else {
               mutableLiveData.setValue(response.body());
         }
            }

            @Override
            public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
