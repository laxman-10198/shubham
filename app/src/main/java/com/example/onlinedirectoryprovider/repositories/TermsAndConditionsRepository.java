package com.example.onlinedirectoryprovider.repositories;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.tandc.TermsAndConditionsResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermsAndConditionsRepository {


    private   String TAG= TermsAndConditionsRepository.class.getSimpleName();
    public TermsAndConditionsRepository(){

    }

    public LiveData<TermsAndConditionsResponse>termsAndConditionsResponseLiveData(String Authorization){
        final MutableLiveData<TermsAndConditionsResponse> mutableLiveData=new MutableLiveData<>();
        ApiInterface apiInterface= RetrofitConnection.getInstance().createService();
        Call<TermsAndConditionsResponse> mCall=apiInterface.tandc(Authorization);
        mCall.enqueue(new Callback<TermsAndConditionsResponse>() {
            @Override
            public void onResponse(Call<TermsAndConditionsResponse> call, Response<TermsAndConditionsResponse> response) {
             if (response.isSuccessful()){
               mutableLiveData.setValue(response.body());
             }
             else {
                 mutableLiveData.setValue(response.body());
             }
            }

            @Override
            public void onFailure(Call<TermsAndConditionsResponse> call, Throwable t) {

          Log.e("error",t.toString());
            }
        });
        return mutableLiveData;
    }
}
