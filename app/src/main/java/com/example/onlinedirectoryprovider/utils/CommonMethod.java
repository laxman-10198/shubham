package com.example.onlinedirectoryprovider.utils;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class CommonMethod {
    private static final String MY_PREFS_NAME = "Online Directory Provider";
    private static final String My = "BeforeLogout";
    public static final String KEY_token ="token1";


    public static void setPreference(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static void setPreference1(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(My, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPreference(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(key, null);
    }
    public static String getPreference1(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(My, MODE_PRIVATE);
        return prefs.getString(key, null);
    }

    public static void clearSharedPreference(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }
    public static void clearSharedPreference1(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(My, MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

}