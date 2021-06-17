package com.example.onlinedirectoryprovider.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String SHARED_PREF_NAME = Constant.PREFS_NAME;
    private static final String KEY_USER_TYPE = "usertype";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_ZIPCODE = "zipcode";
    private static final String KEY_DEVICE_TYPE = "device_type";
    private static final String KEY_DEVICE_TOKEN = "device_token";
    private static final String KEY_USERID = "userId";
    private static final String KEY_TOKEN = "token";
    public static String socialType = "Normal";

    @SuppressLint("StaticFieldLeak")
    private static PrefManager mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context mCtx;
    private SharedPreferences prefs;
    // public static final String FIREBASE_CLOUD_MESSAGING = "fcm";
    private static final String SET_NOTIFY = "set_notify";
    private SharedPreferences pref;
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private SharedPreferences.Editor editor;
    private String KEY_IS_LOGGEDIN = "isLoggedIn";


    public PrefManager(Context context) {
        mCtx = context;
        pref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized PrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PrefManager(context);
        }
        return mInstance;
    }

    public void setLogin(boolean isLoggedIn) {
        editor = pref.edit();
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    /*public void userLogin(UserData userData) {
        editor = pref.edit();
        //  editor.putString(KEY_USER_TYPE,userData.getType());
        editor.putString(KEY_NAME, userData.getName());
        editor.putString(KEY_EMAIL, userData.getEmail());
        editor.putString(KEY_MOBILE, userData.getMibile());
        editor.putString(KEY_LOCATION, userData.getLocation());
        editor.putString(KEY_ZIPCODE, userData.getZipcode());
        editor.putString(KEY_DEVICE_TYPE, userData.getDevice_type());
        editor.putString(KEY_DEVICE_TOKEN, userData.getDevice_token());
        editor.putString(KEY_USERID, String.valueOf(userData.getId()));
        editor.apply();
    }


    public UserData getUserData() {
        return new UserData(
                pref.getString(KEY_USER_TYPE, null),
                pref.getString(KEY_NAME,null),
                pref.getString(KEY_EMAIL,null),
                pref.getString(KEY_MOBILE,null),
                pref.getString(KEY_LOCATION,null),
                pref.getString(KEY_LOCATION,null),
                pref.getString(KEY_DEVICE_TYPE,null),
                pref.getString(KEY_DEVICE_TOKEN,null),
                pref.getString(KEY_USERID,null)
        );

    }

*/    public void setToken(String token) {
        editor=pref.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public String getToken(){
        return pref.getString(KEY_TOKEN, null);
    }
       public void logout() {
        editor=pref.edit();
        editor.clear();
        editor.apply();
    }

    public void setName(String name) {
        editor=pref.edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public String getName(){
        return pref.getString(KEY_NAME, null);
    }



}
