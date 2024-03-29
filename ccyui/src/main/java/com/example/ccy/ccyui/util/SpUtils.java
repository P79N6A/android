package com.example.ccy.ccyui.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class SpUtils {

    private SharedPreferences sp;
    private static SpUtils instance;

    private SpUtils(Context context) {
        sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static synchronized SpUtils getInstance(Context context) {
        if (instance == null) {
            instance = new SpUtils(context.getApplicationContext());
        }
        return instance;
    }

    public SpUtils putInt(String key, int value) {
        sp.edit().putInt(key, value).commit();
        return this;
    }

    public int getInt(String key, int dValue) {
        return sp.getInt(key, dValue);
    }

    public SpUtils putLong(String key, long value) {
        sp.edit().putLong(key, value).commit();
        return this;
    }

    public long getLong(String key, Long dValue) {
        return sp.getLong(key, dValue);
    }

    public SpUtils putFloat(String key, float value) {
        sp.edit().putFloat(key, value).commit();
        return this;
    }

    public Float getFloat(String key, Float dValue) {
        return sp.getFloat(key, dValue);
    }

    public SpUtils putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
        return this;
    }

    public Boolean getBoolean(String key, boolean dValue) {
        return sp.getBoolean(key, dValue);
    }

    public SpUtils putString(String key, String value) {
        sp.edit().putString(key, value).commit();
        return this;
    }

    public String getString(String key, String dValue) {
        return sp.getString(key, dValue);
    }


    public void remove(String key) {
        if (isExist(key)) {
            SharedPreferences.Editor editor = sp.edit();
            editor.remove(key);
            editor.commit();
        }
    }

    public boolean isExist(String key) {
        return sp.contains(key);
    }
}