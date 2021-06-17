package com.example.onlinedirectoryprovider.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class LoginPre {
    private static final String SHARED_PREF_NAME = Constant.PREFS_NAME;
    @SuppressLint("StaticFieldLeak")
    private static com.example.onlinedirectoryprovider.utils.LoginPre preferences = null;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor editor;
 //
 //   private Context context;
    private String isLoggedIn = "isLoggedIn";
    private String otp = "otp";
    private String access_token = "access_token";
    private String device_token= "device_token";
    private String name= "name";
    private String email= "email";
    private String mobile= "mobile";
    private String password= "password";
    private String location= "location";
    private String zipcode= "zipcode";
    private String loginType="userlogintype";

    private LoginPre(Context context) {
        mPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getPreferences() {
        return mPreferences;
    }

    public void setmPreferences(SharedPreferences mPreferences) {
        this.mPreferences = mPreferences;
    }

    public static com.example.onlinedirectoryprovider.utils.LoginPre getActiveInstance(Context context) {
        if (preferences == null) {
            preferences = new com.example.onlinedirectoryprovider.utils.LoginPre(context);
        }
        return preferences;
    }

    public void setLoginType(String loginType) {
        editor = mPreferences.edit();
        editor.putString(this.loginType, loginType);
        editor.apply();
    }

    public String getLoginType() {
        return mPreferences.getString(this.loginType, "");
    }

    public boolean getIsLoggedIn() {
        return mPreferences.getBoolean(this.isLoggedIn, false);
    }

    public void setIsLoggedIn(boolean isLoggedin) {
        editor = mPreferences.edit();
        editor.putBoolean(this.isLoggedIn, isLoggedin);
        editor.apply();
    }

    public String getOtp() {
        return mPreferences.getString(this.otp, "");
    }

    public void setOtp(String otp) {
        editor = mPreferences.edit();
        editor.putString(this.otp, otp);
        editor.apply();
    }


    public String getDevice_token() {
        return mPreferences.getString(this.device_token, "");
    }

    public void setDevice_token(String device_token) {
        editor = mPreferences.edit();
        editor.putString(this.device_token, device_token);
        editor.apply();
    }

    public String getAccess_token() {
        return mPreferences.getString(this.access_token, "");
    }

    public void setAccess_token(String access_token) {
        editor = mPreferences.edit();
        editor.putString(this.access_token, access_token);
        editor.apply();
    }

    public String getName() {
        return mPreferences.getString(this.name, "");
    }

    public void setName(String name) {
        editor = mPreferences.edit();
        editor.putString(this.name, name);
        editor.apply();
    }

}
