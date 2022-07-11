package com.example.bloomi.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.bloomi.uses_manage;
import com.google.gson.Gson;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "dataLogIn";
//    private static final String KEY_phoneOrEmail = "keyusername";
//    private static final String KEY_ACTIVEFLAG="activeFlag";
//    private static final String KEY_DELETEFLAG="deleteFlag";
//    private static final String KEY_CREATEDATE="createDate";
//    private static final String KEY_PHONE="phone";
//    private static final String KEY_EMAIL="email";
    private static final String KEY_jwt = "jwt";
//    private static final String KEY_ID = "id";
//    private static final String KEY_FIRSTNAME="firstName";
//    private static final String KEY_LASTNAME="lastName";
//    private static final String KEY_GENDER="gender";
//    private static final String KEY_BIRTHDAY="birthday";
//    private static final String KEY_AVATAURL="avatarUrl";
//    private static final String KEY_COVERIMAGEURL="coverImageUrl";
//    private static final String KEY_ADDRESS="address";
//    private static final String KEY_AUTHPROVIDER="authProvider";
    private static final String KEY_ACCOUNT="account";
    private static SharedPrefManager mInstance;
    private static Context ctx;

    private SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(User_login user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        uses_manage user_manage=user.Accout;
        Gson gson=new Gson();
        String json=gson.toJson(user_manage);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_jwt,user.getJwt());
        editor.putString(KEY_ACCOUNT,json);
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_jwt, null) != null;
    }

    //this method will give the logged in user
    public User_login getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString(KEY_ACCOUNT,"");
        uses_manage user_manage=gson.fromJson(json,uses_manage.class);
        return new User_login(
                sharedPreferences.getString(KEY_jwt, null),
                user_manage);
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LogIn.class));
    }
}
