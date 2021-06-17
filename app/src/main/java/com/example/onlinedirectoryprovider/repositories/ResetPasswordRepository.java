package com.example.onlinedirectoryprovider.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.resetpassword.ResetPasswordResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordRepository {


    private   String TAG= ResetPasswordRepository.class.getSimpleName();
    public ResetPasswordRepository(){

    }

    public LiveData<ResetPasswordResponse> resetPasswordResponseLiveData(String Authorization,String email,String password, String cnfpassword){
final MutableLiveData<ResetPasswordResponse> mutableLiveData=new MutableLiveData<>();
        ApiInterface apiInterface= RetrofitConnection.getInstance().createService();
        Call<ResetPasswordResponse> mCall=apiInterface.ResetPassword(Authorization,email,password,cnfpassword);
        mCall.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
             if (response.isSuccessful()){
               mutableLiveData.setValue(response.body());
             }
             else {
                 mutableLiveData.setValue(response.body());
             }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
