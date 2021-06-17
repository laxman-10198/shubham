package com.example.onlinedirectoryprovider.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.getprofile.GetProfileResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProfileRepository {


    private   String TAG= GetProfileRepository.class.getSimpleName();
    public GetProfileRepository(){

    }

    public LiveData<GetProfileResponse> getProfileResponseLiveData(String Authorization){
        final MutableLiveData<GetProfileResponse> mutableLiveData=new MutableLiveData<>();
        ApiInterface apiInterface= RetrofitConnection.getInstance().createService();
        Call<GetProfileResponse> mCall=apiInterface.getProfile(Authorization);
        mCall.enqueue(new Callback<GetProfileResponse>() {
            @Override
            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
             if (response.isSuccessful()){
               mutableLiveData.setValue(response.body());
             }
             else {
                 mutableLiveData.setValue(response.body());
             }
            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
