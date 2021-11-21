package com.ghskhawajabagh.lms.Common.UTIL;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.ghskhawajabagh.lms.Common.Models.User;
import com.google.gson.Gson;

public  class ApplicationPrefrences {

    Context context;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor sharedPreferencesEditor;

    public ApplicationPrefrences(Context context) {
        this.context = context;
         sharedPreferences = context.getSharedPreferences("mPref", 0);
         sharedPreferencesEditor = sharedPreferences.edit();
    }

    public  void storeUser(User user)
    {

        final Gson gson = new Gson();
        String serializedObject = gson.toJson(user);
        sharedPreferencesEditor.putString("user", serializedObject);
        sharedPreferencesEditor.apply();

    }

    public User getStoredUser() {
        User user;

        if (sharedPreferences.contains("user")) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString("user", ""),User.class);
        }

        return null;
    }






    public  void clearUserData()
    {

        sharedPreferencesEditor.clear();
        sharedPreferencesEditor.apply();

    }




}
