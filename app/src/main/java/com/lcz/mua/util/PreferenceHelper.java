package com.lcz.mua.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.lcz.mua.base.App;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class PreferenceHelper {
    private static final String PREFERENCE_NAME_CIRCLE = "sp_tua";

    private static SharedPreferences getSharedPreference() {
        Context context = App.getInstance().getApplicationContext();
        return context.getSharedPreferences(PREFERENCE_NAME_CIRCLE, Context.MODE_PRIVATE);
    }

    public static boolean putString(String tag, String value) {


        SharedPreferences prefs = getSharedPreference();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(tag, value);
        return editor.commit();
    }

    public static String getString(String tag) {
        return getString(tag, "");
    }

    public static String getString(String tag, String defaultValue) {
        SharedPreferences prefs = getSharedPreference();
        return prefs.getString(tag, defaultValue);
    }

    public static boolean putBoolean(String tag, boolean value) {
        SharedPreferences prefs = getSharedPreference();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(tag, value);
        return editor.commit();
    }

    public static boolean getBoolean(String tag) {
        return getBoolean(tag, false);
    }

    public static boolean getBoolean(String tag, boolean defaultValue) {
        SharedPreferences prefs = getSharedPreference();
        return prefs.getBoolean(tag, defaultValue);
    }


    public static int getInt(String tag, int defaultValue) {
        SharedPreferences prefs = getSharedPreference();
        return prefs.getInt(tag, defaultValue);
    }

    public static boolean putInt(String tag, int value) {
        SharedPreferences prefs = getSharedPreference();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(tag, value);
        return editor.commit();
    }
}
