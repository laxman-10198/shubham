package com.example.onlinedirectoryprovider.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.selecttheme.SelectThemeResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectThemeRepository {


    private   String TAG= SelectThemeRepository.class.getSimpleName();
    public SelectThemeRepository(){

    }

    public LiveData<SelectThemeResponse> selectThemeResponseLiveData(String Authorization){
        final MutableLiveData<SelectThemeResponse> mutableLiveData=new MutableLiveData<>();
        ApiInterface apiInterface= RetrofitConnection.getInstance().createService();
        Call<SelectThemeResponse> mCall=apiInterface.selecttheme(Authorization);
        mCall.enqueue(new Callback<SelectThemeResponse>() {
            @Override
            public void onResponse(Call<SelectThemeResponse> call, Response<SelectThemeResponse> response) {
             if (response.isSuccessful()){
               mutableLiveData.setValue(response.body());
             }
             else {
                 mutableLiveData.setValue(response.body());
             }
            }

            @Override
            public void onFailure(Call<SelectThemeResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
