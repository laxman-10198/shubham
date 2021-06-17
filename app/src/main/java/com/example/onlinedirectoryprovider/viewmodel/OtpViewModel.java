package com.example.onlinedirectoryprovider.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinedirectoryprovider.model.resendotp.ResendOtpResponse;
import com.example.onlinedirectoryprovider.model.verifyotp.VerifyOtpResponse;
import com.example.onlinedirectoryprovider.repositories.ResendOtpRepository;
import com.example.onlinedirectoryprovider.repositories.VerifyOtpRepository;

public class OtpViewModel extends AndroidViewModel {

    VerifyOtpRepository verifyOtpRepository;
    ResendOtpRepository resendOtpRepository;
    public OtpViewModel(@NonNull Application application) {
        super(application);
        this.verifyOtpRepository=new VerifyOtpRepository();
       this.resendOtpRepository= new ResendOtpRepository();
    }
    public LiveData<VerifyOtpResponse>verifyOtp(String email,String otp,String type) {

        return verifyOtpRepository.OtpResponseLiveData(email,otp,type);
    }

    public LiveData<ResendOtpResponse> resendOtp(String email, String type) {

        return resendOtpRepository.resendOTPLivedata(email,type);
    }


}
