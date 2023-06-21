package com.example.healthyme.Sessions;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class LoginSessionManagement {
    private static String TAG = LoginSessionManagement.class.getSimpleName();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "login_session";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    public LoginSessionManagement(Context context){
        this._context = context;
        sharedPreferences =_context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(boolean isLoggedIn){
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }
    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }






}
