package com.example.onlinedirectoryprovider.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.updateprofile.UpdateProfileResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileRepository {


    private   String TAG= UpdateProfileRepository.class.getSimpleName();
    public UpdateProfileRepository(){

    }

    public LiveData<UpdateProfileResponse> updateProfileResponseLiveData(String token,String firstname,String lastname,String email,String mobile,String role,String location,String zipcode ){
        final MutableLiveData<UpdateProfileResponse> mutableLiveData=new MutableLiveData<>();
        ApiInterface apiInterface= RetrofitConnection.getInstance().createService();
        Call<UpdateProfileResponse> mCall=apiInterface.updateProfile(token,firstname,lastname,email,mobile,role,location,zipcode);
        mCall.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
             if (response.isSuccessful()){
               mutableLiveData.setValue(response.body());
             }
             else {
                 mutableLiveData.setValue(response.body());
             }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
