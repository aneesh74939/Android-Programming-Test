package com.department.assessment.utils;

import android.content.SharedPreferences;

import com.department.assessment.application.App;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceHelper {
    public static final String PREFERENCE_NAME = "shared_preference";
    public static final String NO_DATA = "";

    public static void setPreferenceBoolean(String key, boolean value) {

        SharedPreferences spns = App.getInstance().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor e = spns.edit();
        e.putBoolean(key, value);
        e.apply();

    }

    public static boolean getPreferenceBoolean(String key) {
        boolean aBoolean = false;
        try {
            SharedPreferences spns = App.getInstance().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
            aBoolean = spns.getBoolean(key, false);
        } catch (Exception e) {

        }
        return aBoolean;
    }

    public static void setPreference(String key, String value) {
        SharedPreferences spns = App.getInstance().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor e = spns.edit();
        e.putString(key, value);
        e.apply();

    }

    public static String getPreference(String key) {
        SharedPreferences spns = App.getInstance().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        return spns.getString(key, NO_DATA);
    }

    public static void clearAll() {
        SharedPreferences spns = App.getInstance().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor e = spns.edit();
        e.clear();
        e.apply();

    }

    public static void removeKey(String key){
        SharedPreferences spns = App.getInstance().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor e = spns.edit();
        e.remove(key);
        e.apply();
    }
}
