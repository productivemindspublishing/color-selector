package com.productiveminds.colorselector.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class UserPreferencesManager {

    private static SharedPreferences sharedPreferences;

    private static void setUpPrefernces(Context context) {
        sharedPreferences = context.getSharedPreferences(ProjectConstants.USER_PREFERENCES_OBJ, Context.MODE_PRIVATE);
    }

    private static void setDownPreferences() {
        sharedPreferences = null;
    }

    public static void setStringPrefValue(Context context, String key, String value) {
        setUpPrefernces(context);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putString(key, value);
        prefEditor.commit();
        prefEditor.apply();
        setDownPreferences();
    }
    public static String getStringPrefValue(Context context, String key, String value) {
        setUpPrefernces(context);
        value = sharedPreferences.getString(key, value);
        setDownPreferences();
        return value;
    }

    public static void setIntPrefValue(Context context, String key, int value) {
        setUpPrefernces(context);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putInt(key, value);
        prefEditor.commit();
        prefEditor.apply();
        setDownPreferences();
    }
    public static int getIntPrefValue(Context context, String key, int value) {
        setUpPrefernces(context);
        value = sharedPreferences.getInt(key, value);
        setDownPreferences();
        return value;
    }

    public static void setFloatPrefValue(Context context, String key, float value) {
        setUpPrefernces(context);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putFloat(key, value);
        prefEditor.commit();
        prefEditor.apply();
        setDownPreferences();
    }
    public static float getFloatPrefValue(Context context, String key, float value) {
        setUpPrefernces(context);
        value = sharedPreferences.getFloat(key, value);
        setDownPreferences();
        return value;
    }

    public static void setBooleanPrefValue(Context context, String key, boolean value) {
        setUpPrefernces(context);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putBoolean(key, value);
        prefEditor.commit();
        prefEditor.apply();
        setDownPreferences();
    }
    public static Boolean getBooleanPrefValue(Context context, String key, boolean value) {
        setUpPrefernces(context);
        value = sharedPreferences.getBoolean(key, value);
        setDownPreferences();
        return value;
    }

    public static void setStringSetPrefValue(Context context, String key, Set<String> value) {
        setUpPrefernces(context);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putStringSet(key, value);
        prefEditor.commit();
        prefEditor.apply();
        setDownPreferences();
    }
    public static Set<String> getStringSetPrefValue(Context context, String key, Set<String> value) {
        setUpPrefernces(context);
        value = sharedPreferences.getStringSet(key, value);
        setDownPreferences();
        return value;
    }


}
