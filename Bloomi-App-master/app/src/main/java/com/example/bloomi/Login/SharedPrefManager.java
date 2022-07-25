package com.example.bloomi.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.bloomi.post_Bloom.OnePost;
import com.example.bloomi.uses_manage;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "dataLogIn";

    private static final String KEY_jwt = "jwt";

    private static final String KEY_list_local="List";
    private static final String KEY_ACCOUNT="account";
    private static SharedPrefManager mInstance;
    private static Context ctx;
    public static List<OnePost> list_post_local=new ArrayList<>();
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
