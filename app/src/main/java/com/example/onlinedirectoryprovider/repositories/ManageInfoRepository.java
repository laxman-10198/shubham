package com.example.onlinedirectoryprovider.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.manageinfo.ManageInfoResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageInfoRepository {
    private String TAG= ManageInfoRepository.class.getSimpleName();

    public ManageInfoRepository() {

    }

    public LiveData<ManageInfoResponse>manageInfoResponseLiveData(String Authorization, String website_url,String details,
                                                                 String opening_time,String closing_time,String average_charges,
                                                                 String website_logo) {
        final MutableLiveData<ManageInfoResponse> mutableLiveData = new MutableLiveData<>();
        ApiInterface apiService = RetrofitConnection.getInstance().createService();
        Call<ManageInfoResponse> call = apiService.manageInfo(Authorization,website_url,details,opening_time,closing_time,average_charges,website_logo);
        call.enqueue(new Callback<ManageInfoResponse>() {



            @Override
            public void onResponse(Call<ManageInfoResponse> call, Response<ManageInfoResponse> response) {
                if (response.isSuccessful()){
                    mutableLiveData.setValue(response.body());
                }
                else {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ManageInfoResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }


}
