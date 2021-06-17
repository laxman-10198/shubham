package com.example.onlinedirectoryprovider.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinedirectoryprovider.model.adddomain.AddDomainResponse;
import com.example.onlinedirectoryprovider.retrofit.ApiInterface;
import com.example.onlinedirectoryprovider.retrofit.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDomainRepository {
    private String TAG= AddDomainRepository.class.getSimpleName();

    public AddDomainRepository() {

    }

    public LiveData<AddDomainResponse>addDomainResponseLiveData(String Authorization, String domainname ) {
        final MutableLiveData<AddDomainResponse> mutableLiveData = new MutableLiveData<>();
        ApiInterface apiService = RetrofitConnection.getInstance().createService();
        Call<AddDomainResponse> call = apiService.addDomain(Authorization,domainname);
        call.enqueue(new Callback<AddDomainResponse>() {



            @Override
            public void onResponse(Call<AddDomainResponse> call, Response<AddDomainResponse> response) {
                if (response.isSuccessful()){
                    mutableLiveData.setValue(response.body());
                }
                else {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AddDomainResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }


}
