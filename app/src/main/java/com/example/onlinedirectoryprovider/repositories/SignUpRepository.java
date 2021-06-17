package com.example.onlinedirectoryprovider.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.register.RegisterResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRepository {
    private String TAG= SignUpRepository.class.getSimpleName();
    public SignUpRepository() {

    }

    public LiveData<RegisterResponse>getMutableLiveData(String firstName,String lastname, String userEmail, String userPassword, String userCnfpassword, String userMobile, String userBusinessname, String  role, String  category_id,
                                                        String  userAddress, String  userZipcode, String  latitude, String  longitude, int  device_type, String  device_token) {
        final MutableLiveData<RegisterResponse> mutableLiveData = new MutableLiveData<>();
        ApiInterface apiService = RetrofitConnection.getInstance().createService();

        Call<RegisterResponse> call = apiService.getRegister(firstName,lastname,userEmail,userPassword,userCnfpassword,userMobile,userBusinessname,role, category_id,
                userAddress,userZipcode,latitude,longitude,device_type,device_token);
        call.enqueue(new Callback<RegisterResponse>() {


            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                } else {
                    RegisterResponse LoginResponse = new RegisterResponse();
                    mutableLiveData.setValue(LoginResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                RegisterResponse signInResponse = new RegisterResponse();
                mutableLiveData.setValue(signInResponse);
            }
        });
        return mutableLiveData;
    }


}