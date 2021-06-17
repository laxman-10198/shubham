package com.example.onlinedirectoryprovider.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.newpassword.ChangePasswordResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPasswordRepository {


    private   String TAG= NewPasswordRepository.class.getSimpleName();
    public NewPasswordRepository(){

    }

    public LiveData<ChangePasswordResponse> changePasswordResponseLiveData(String Authorization,String currentpassword,String password, String cnfpassword){
final MutableLiveData<ChangePasswordResponse> mutableLiveData=new MutableLiveData<>();
        ApiInterface apiInterface= RetrofitConnection.getInstance().createService();
        Call<ChangePasswordResponse> mCall=apiInterface.ChangePassword(Authorization,currentpassword,password,cnfpassword);
        mCall.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
             if (response.isSuccessful()){
               mutableLiveData.setValue(response.body());
             }
             else {
                 mutableLiveData.setValue(response.body());
             }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
